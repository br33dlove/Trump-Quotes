package com.davidcryer.trumpquotes.platformindependent.presenter.presenters.implementations.swipequote;

import com.davidcryer.trumpquotes.platformindependent.model.quotes.Quote;
import com.davidcryer.trumpquotes.platformindependent.model.network.quotes.requesters.RandomQuoteRequester;
import com.davidcryer.trumpquotes.platformindependent.model.repository.quotes.QuoteRepositoryHandler;
import com.davidcryer.trumpquotes.platformindependent.model.network.quotes.QuoteRequestCallback;
import com.davidcryer.trumpquotes.platformindependent.model.quotes.QuoteHelper;
import com.davidcryer.trumpquotes.platformindependent.presenter.presenters.Presenter;
import com.davidcryer.trumpquotes.platformindependent.view.SwipeQuoteView;
import com.davidcryer.trumpquotes.platformindependent.view.viewmodels.models.ViewQuote;
import com.davidcryer.trumpquotes.platformindependent.view.viewmodels.models.ViewQuoteFactory;

import java.util.List;

class SwipeQuotePresenter<ViewQuoteType extends ViewQuote> extends Presenter<SwipeQuoteView.EventsListener> {
    private final SwipeQuoteView<ViewQuoteType> viewWrapper;
    private final RandomQuoteRequester randomQuoteRequester;
    private final QuoteRepositoryHandler quoteRepositoryHandler;
    private final ViewQuoteFactory<ViewQuoteType> viewQuoteFactory;

    SwipeQuotePresenter(
            final SwipeQuoteView<ViewQuoteType> viewWrapper,
            final RandomQuoteRequester randomQuoteRequester,
            final QuoteRepositoryHandler quoteRepositoryHandler,
            final ViewQuoteFactory<ViewQuoteType> viewQuoteFactory
    ) {
        this.viewWrapper = viewWrapper;
        this.randomQuoteRequester = randomQuoteRequester;
        this.quoteRepositoryHandler = quoteRepositoryHandler;
        this.viewQuoteFactory = viewQuoteFactory;
    }

    @Override
    public SwipeQuoteView.EventsListener eventsListener() {
        return new SwipeQuoteView.EventsListener() {

            @Override
            public void onRequestQuotes() {
                showLoadingQuote();
                fetchUnJudgedQuoteFromStoreOrRequestQuoteAndDisplay();
            }

            @Override
            public void onRetryQuotesRequest() {
                showLoadingQuote();
                requestQuoteAndDisplay(false);
            }

            @Override
            public void onQuoteSwipedLeft() {
                showLoadingQuote();
                updateQuoteAsJudgedInStore();
                requestQuoteAndDisplay(false);
            }

            @Override
            public void onQuoteSwipedRight() {
                showLoadingQuote();
                updateQuoteAsJudgedInStore();
                requestQuoteAndDisplay(false);
            }

            @Override
            public void onReleaseResources(final boolean isFinishing) {
                deregisterFromOngoingQuoteRequests(isFinishing);
            }
        };
    }

    private void fetchUnJudgedQuoteFromStoreOrRequestQuoteAndDisplay() {
        quoteRepositoryHandler.retrieveUnJudgedQuotes(new QuoteRepositoryHandler.RetrieveCallback() {
            @Override
            public void onReturn(List<Quote> quotes) {
                final Quote mostRecentQuote = QuoteHelper.removeMostRecent(quotes);
                if (mostRecentQuote == null) {
                    requestQuoteAndDisplay(true);
                } else {
                    viewWrapper.showQuoteState(viewQuoteFactory.create(mostRecentQuote));
                    quoteRepositoryHandler.clear(QuoteHelper.ids(quotes));
                }
            }
        });
    }

    private void showLoadingQuote() {
        viewWrapper.showLoadingQuotesState();
    }

    private void requestQuoteAndDisplay(final boolean preferLastReceivedQuote) {
        randomQuoteRequester.requestRandomQuote(quoteCallback, preferLastReceivedQuote);
    }

    private void updateQuoteAsJudgedInStore() {
        final ViewQuoteType viewQuote = viewWrapper.viewModel().quote();
        quoteRepositoryHandler.updateQuoteAsJudged(viewQuote.id());
    }

    private void deregisterFromOngoingQuoteRequests(final boolean shouldCancelRequests) {
        randomQuoteRequester.remove(quoteCallback, shouldCancelRequests);
    }

    private final QuoteRequestCallback quoteCallback = new QuoteRequestCallback() {

        @Override
        public void success(Quote quote) {
            viewWrapper.showQuoteState(viewQuoteFactory.create(quote));
            quoteRepositoryHandler.store(quote);
        }

        @Override
        public void failure() {
            viewWrapper.showFailureToGetQuotesState();
        }
    };
}
