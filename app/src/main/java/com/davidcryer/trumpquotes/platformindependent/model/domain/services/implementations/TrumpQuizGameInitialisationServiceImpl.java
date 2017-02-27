package com.davidcryer.trumpquotes.platformindependent.model.domain.services.implementations;

import com.davidcryer.trumpquotes.platformindependent.javahelpers.CoinFlipper;
import com.davidcryer.trumpquotes.platformindependent.model.domain.entities.IsTrumpAnswer;
import com.davidcryer.trumpquotes.platformindependent.model.domain.entities.NotTrumpAnswer;
import com.davidcryer.trumpquotes.platformindependent.model.domain.entities.TrumpQuizAnswer;
import com.davidcryer.trumpquotes.platformindependent.model.domain.entities.TrumpQuizGameImpl;
import com.davidcryer.trumpquotes.platformindependent.model.domain.entities.TrumpQuizQuestion;
import com.davidcryer.trumpquotes.platformindependent.model.domain.entities.TrumpQuizQuestionImpl;
import com.davidcryer.trumpquotes.platformindependent.model.domain.services.QuoteService;
import com.davidcryer.trumpquotes.platformindependent.model.domain.services.TrumpQuizGameInitialisationService;
import com.davidcryer.trumpquotes.platformindependent.model.domain.services.errors.InitialisationError;
import com.davidcryer.trumpquotes.platformindependent.model.framework.Cancelable;
import com.davidcryer.trumpquotes.platformindependent.model.network.quotes.Quote;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public final class TrumpQuizGameInitialisationServiceImpl implements TrumpQuizGameInitialisationService {
    private final QuoteService trumpQuoteService;
    private final QuoteService gumpQuoteService;

    public TrumpQuizGameInitialisationServiceImpl(QuoteService trumpQuoteService, QuoteService gumpQuoteService) {
        this.trumpQuoteService = trumpQuoteService;
        this.gumpQuoteService = gumpQuoteService;
    }

    @Override
    public void initialiseNewGame(int questionCount, Callback callback) {
        initialiseRandomQuotes(questionCount, callback);
    }

    private void initialiseRandomQuotes(final int questionCount, final Callback callback) {
        new TrumpQuestionsInitialiser(trumpQuoteService, gumpQuoteService, questionCount, new TrumpQuestionsInitialiser.Callback() {
            @Override
            public void onSuccess(TrumpQuizQuestionImpl[] questions) {
                initialiseGame(questions, callback);
            }

            @Override
            public void onFailure(InitialisationError error) {
                callback.onFailure(error);
            }
        }).randomQuotes();
    }

    private void initialiseGame(final TrumpQuizQuestionImpl[] questions, final Callback callback) {
        callback.onSuccess(GameInitialiser.game(questions));
    }

    private final static class TrumpQuestionsInitialiser {
        private enum QuoteSource{TRUMP, GUMP}
        private final QuoteService trumpQuoteService;
        private final QuoteService gumpQuoteService;
        private final int totalQuestionsCount;
        private final Set<Cancelable> randomQuoteFetchers = new HashSet<>();
        private final List<TrumpQuizQuestion> questions;
        private final Callback callback;
        private boolean stopRandomQuoteTasks = false;

        TrumpQuestionsInitialiser(
                QuoteService trumpQuoteService,
                QuoteService gumpQuoteService,
                int totalQuestionsCount,
                Callback callback
        ) {
            this.trumpQuoteService = trumpQuoteService;
            this.gumpQuoteService = gumpQuoteService;
            this.totalQuestionsCount = totalQuestionsCount;
            questions = new ArrayList<>(totalQuestionsCount);
            this.callback = callback;
        }

        private void randomQuotes() {
            final QuoteSource[] sources = randomQuoteTypes();
            for (final QuoteSource source : sources) {
                if (!stopRandomQuoteTasks) {
                    switch (source) {
                        case GUMP: {
                            randomQuoteFetchers.add(
                                    trumpQuoteService.randomQuote(quoteServiceCallback(new IsTrumpAnswer()))
                            );
                            break;
                        }
                        case TRUMP: {
                            randomQuoteFetchers.add(
                                    gumpQuoteService.randomQuote(quoteServiceCallback(new NotTrumpAnswer()))
                            );
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

        private QuoteService.Callback quoteServiceCallback(final TrumpQuizAnswer answer) {
            return new QuoteService.Callback() {
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

        private static TrumpQuizQuestionImpl question(final Quote quote, final TrumpQuizAnswer answer) {
            return new TrumpQuizQuestionImpl(quote.quote(), answer);
        }

        private void onQuestionCreated(final TrumpQuizQuestionImpl question) {
            questions.add(question);
            if (questions.size() == totalQuestionsCount) {
                onFinish(questions);
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

        private void onFinish(final List<TrumpQuizQuestion> questions) {
            Collections.shuffle(questions);
            callback.onSuccess(questions.toArray(new TrumpQuizQuestionImpl[questions.size()]));
        }

        private interface Callback {
            void onSuccess(final TrumpQuizQuestionImpl[] questions);
            void onFailure(final InitialisationError error);
        }
    }

    private final static class GameInitialiser {

        private static TrumpQuizGameImpl game(TrumpQuizQuestionImpl[] questions) {
            return TrumpQuizGameImpl.newGame(questions);
        }
    }
}
