package com.davidcryer.trumpquotes.platformindependent.model.domain.services.implementations;

import com.davidcryer.trumpquotes.platformindependent.javahelpers.CoinFlipper;
import com.davidcryer.trumpquotes.platformindependent.model.domain.entities.QuizAnswer;
import com.davidcryer.trumpquotes.platformindependent.model.domain.entities.TrumpQuizGameImpl;
import com.davidcryer.trumpquotes.platformindependent.model.domain.entities.QuizQuestionImpl;
import com.davidcryer.trumpquotes.platformindependent.model.domain.services.QuoteFileService;
import com.davidcryer.trumpquotes.platformindependent.model.domain.services.QuoteNetworkService;
import com.davidcryer.trumpquotes.platformindependent.model.domain.services.TrumpQuizGameInitialisationService;
import com.davidcryer.trumpquotes.platformindependent.model.domain.services.errors.InitialisationError;
import com.davidcryer.trumpquotes.platformindependent.model.framework.Cancelable;
import com.davidcryer.trumpquotes.platformindependent.model.network.quotes.Quote;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public final class TrumpQuizGameInitialisationServiceImpl implements TrumpQuizGameInitialisationService {
    private final static String OPTION_A = "D. Trump";
    private final static String OPTION_B = "F. Gump";
    private final static QuizAnswer ANSWER_TRUMP = QuizAnswer.newInstanceA();
    private final static QuizAnswer ANSWER_GUMP = QuizAnswer.newInstanceB();
    private final QuoteNetworkService trumpQuoteNetworkService;
    private final QuoteFileService gumpQuoteFileService;

    public TrumpQuizGameInitialisationServiceImpl(QuoteNetworkService trumpQuoteNetworkService, QuoteFileService gumpQuoteFileService) {
        this.trumpQuoteNetworkService = trumpQuoteNetworkService;
        this.gumpQuoteFileService = gumpQuoteFileService;
    }

    @Override
    public void initialiseNewGame(int questionCount, Callback callback) {
        initialiseRandomQuotes(questionCount, callback);
    }

    private void initialiseRandomQuotes(final int questionCount, final Callback callback) {
        new TrumpQuestionsInitialiser(trumpQuoteNetworkService, gumpQuoteFileService, questionCount, new TrumpQuestionsInitialiser.Callback() {
            @Override
            public void onSuccess(QuizQuestionImpl[] questions) {
                initialiseGame(questions, callback);
            }

            @Override
            public void onFailure(InitialisationError error) {
                callback.onFailure(error);
            }
        }).initialiseRandomQuotes();
    }

    private void initialiseGame(final QuizQuestionImpl[] questions, final Callback callback) {
        callback.onSuccess(GameInitialiser.game(questions));
    }

    private final static class TrumpQuestionsInitialiser {
        private enum QuoteSource{TRUMP, GUMP}
        private final QuoteNetworkService trumpQuoteNetworkService;
        private final QuoteFileService gumpQuoteFileService;
        private final int totalQuestionsCount;
        private final List<Cancelable> randomQuoteFetchers;
        private final List<QuizQuestionImpl> questions;
        private final Callback callback;
        private boolean stopRandomQuoteTasks = false;

        TrumpQuestionsInitialiser(
                QuoteNetworkService trumpQuoteNetworkService,
                QuoteFileService gumpQuoteFileService,
                int totalQuestionsCount,
                Callback callback
        ) {
            this.trumpQuoteNetworkService = trumpQuoteNetworkService;
            this.gumpQuoteFileService = gumpQuoteFileService;
            this.totalQuestionsCount = totalQuestionsCount;
            randomQuoteFetchers = new ArrayList<>(totalQuestionsCount);
            questions = new ArrayList<>(totalQuestionsCount);
            this.callback = callback;
        }

        private void initialiseRandomQuotes() {
            final QuoteSource[] sources = randomQuoteTypes();
            for (final QuoteSource source : sources) {
                if (!stopRandomQuoteTasks) {
                    switch (source) {
                        case GUMP: {
                            getQuoteFromFile(gumpQuoteFileService, ANSWER_GUMP);
                            break;
                        }
                        case TRUMP: {
                            sendQuoteNetworkRequest(trumpQuoteNetworkService, ANSWER_TRUMP);
                            break;
                        }
                    }
                }
            }
        }

        private void getQuoteFromFile(final QuoteFileService fileService, final QuizAnswer answer) {
            try {
                onQuestionCreated(question(fileService.randomQuote(), answer));
            } catch (IOException ioe) {
                onError();
            }
        }

        private void sendQuoteNetworkRequest(final QuoteNetworkService networkService, final QuizAnswer answer) {
            randomQuoteFetchers.add(networkService.randomQuote(quoteServiceCallback(answer)));
        }

        private QuoteSource[] randomQuoteTypes() {
            //TODO debug trump quotes not coming through
            final CoinFlipper coinFlipper = new CoinFlipper();
            final QuoteSource[] quoteSources = new QuoteSource[totalQuestionsCount];
            for (int i = 0; i < totalQuestionsCount; i++) {
                quoteSources[i] = QuoteSource.GUMP;
//                quoteSources[i] = coinFlipper.flip() == CoinFlipper.Result.HEADS ? QuoteSource.GUMP : QuoteSource.TRUMP;
            }
            return quoteSources;
        }

        private QuoteNetworkService.Callback quoteServiceCallback(final QuizAnswer answer) {
            return new QuoteNetworkService.Callback() {
                @Override
                public void onSuccess(Quote quote) {
                    onQuestionCreated(question(quote, answer));
                }

                @Override
                public void onError() {
                    TrumpQuestionsInitialiser.this.onError();
                }
            };
        }

        private static QuizQuestionImpl question(final Quote quote, final QuizAnswer answer) {
            return new QuizQuestionImpl(quote.quote(), OPTION_A, OPTION_B, answer);
        }

        private void onQuestionCreated(final QuizQuestionImpl question) {
            if (!stopRandomQuoteTasks) {
                questions.add(question);
                if (questions.size() == totalQuestionsCount) {
                    onFinish(questions);
                }
            }
        }

        private void onError() {
            stopRandomQuoteTasks = true;
            for (final Cancelable fetcher : randomQuoteFetchers) {
                if (!fetcher.isCancelled()) {
                    fetcher.cancel();
                }
            }
            callback.onFailure(new InitialisationError());//TODO get specific error
        }

        private void onFinish(final List<QuizQuestionImpl> questions) {
            cleanUp();
            Collections.shuffle(questions);
            callback.onSuccess(questions.toArray(new QuizQuestionImpl[questions.size()]));
        }

        private void cleanUp() {
            gumpQuoteFileService.clearCaches();
        }

        private interface Callback {
            void onSuccess(final QuizQuestionImpl[] questions);
            void onFailure(final InitialisationError error);
        }
    }

    private final static class GameInitialiser {

        private static TrumpQuizGameImpl game(QuizQuestionImpl[] questions) {
            return TrumpQuizGameImpl.newGame(questions);
        }
    }
}
