package com.davidcryer.trumpquotes.android.model.services.implementations;

import com.davidcryer.trumpquotes.platformindependent.javahelpers.CoinFlipper;
import com.davidcryer.trumpquotes.android.model.domainentities.QuizAnswer;
import com.davidcryer.trumpquotes.android.model.domainentities.TrumpQuizGameImpl;
import com.davidcryer.trumpquotes.android.model.domainentities.QuizQuestionImpl;
import com.davidcryer.trumpquotes.android.model.services.QuoteFileService;
import com.davidcryer.trumpquotes.android.model.services.QuoteNetworkService;
import com.davidcryer.trumpquotes.android.model.services.TrumpQuizGameInitialisationService;
import com.davidcryer.trumpquotes.android.model.services.errors.InitialisationError;
import com.davidcryer.trumpquotes.android.model.framework.store.Cancelable;
import com.davidcryer.trumpquotes.android.model.framework.network.quotes.Quote;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

final class TrumpQuizGameInitialisationServiceImpl implements TrumpQuizGameInitialisationService {
    private final static String OPTION_A = "D. Trump";
    private final static String OPTION_B = "F. Gump";
    private final static QuizAnswer ANSWER_TRUMP = QuizAnswer.newInstanceA();
    private final static QuizAnswer ANSWER_GUMP = QuizAnswer.newInstanceB();
    private final QuoteNetworkService trumpQuoteNetworkService;
    private final QuoteFileService gumpQuoteFileService;

    TrumpQuizGameInitialisationServiceImpl(QuoteNetworkService trumpQuoteNetworkService, QuoteFileService gumpQuoteFileService) {
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

        private QuoteSource[] randomQuoteTypes() {
            final CoinFlipper coinFlipper = new CoinFlipper();
            final QuoteSource[] quoteSources = new QuoteSource[totalQuestionsCount];
            for (int i = 0; i < totalQuestionsCount; i++) {
                quoteSources[i] = coinFlipper.flip() == CoinFlipper.Result.HEADS ? QuoteSource.GUMP : QuoteSource.TRUMP;
            }
            return quoteSources;
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
