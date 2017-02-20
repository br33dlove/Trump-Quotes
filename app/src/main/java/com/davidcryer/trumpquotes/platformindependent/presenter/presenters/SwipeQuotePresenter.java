package com.davidcryer.trumpquotes.platformindependent.presenter.presenters;

import com.davidcryer.trumpquotes.platformindependent.model.quotes.Quote;
import com.davidcryer.trumpquotes.platformindependent.model.quotes.network.requesters.RandomQuoteRequester;
import com.davidcryer.trumpquotes.platformindependent.model.quotes.store.QuoteStoreHandler;
import com.davidcryer.trumpquotes.platformindependent.model.quotes.network.QuoteRequestCallback;
import com.davidcryer.trumpquotes.platformindependent.model.quotes.QuoteHelper;
import com.davidcryer.trumpquotes.platformindependent.view.SwipeQuoteView;
import com.davidcryer.trumpquotes.platformindependent.view.viewmodels.models.ViewQuote;
import com.davidcryer.trumpquotes.platformindependent.view.viewmodels.models.factories.ViewQuoteFactory;

import java.util.List;

public class SwipeQuotePresenter<ViewQuoteType extends ViewQuote> extends Presenter<SwipeQuoteView.EventsListener> {
    private final SwipeQuoteView<ViewQuoteType> viewWrapper;
    private final RandomQuoteRequester randomQuoteRequester;
    private final QuoteStoreHandler quoteStoreHandler;
    private final ViewQuoteFactory<ViewQuoteType> viewQuoteFactory;

    public SwipeQuotePresenter(
            final SwipeQuoteView<ViewQuoteType> viewWrapper,
            final RandomQuoteRequester randomQuoteRequester,
            final QuoteStoreHandler quoteStoreHandler,
            final ViewQuoteFactory<ViewQuoteType> viewQuoteFactory
    ) {
        this.viewWrapper = viewWrapper;
        this.randomQuoteRequester = randomQuoteRequester;
        this.quoteStoreHandler = quoteStoreHandler;
        this.viewQuoteFactory = viewQuoteFactory;
    }

    @Override
    public SwipeQuoteView.EventsListener eventsListener() {
        return new SwipeQuoteView.EventsListener() {

            @Override
            public void onRequestFirstQuote() {
                showLoadingQuote();
                getUnJudgedQuoteFromStoreOrRequestQuoteAndDisplay();
            }

            @Override
            public void onRetryQuoteRequest() {
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
                removeQuoteFromStore();
                requestQuoteAndDisplay(false);
            }

            @Override
            public void onReleaseResources(final boolean isFinishing) {
                deregisterFromOngoingQuoteRequests(isFinishing);
            }
        };
    }

    private void getUnJudgedQuoteFromStoreOrRequestQuoteAndDisplay() {
        quoteStoreHandler.retrieveUnJudgedQuotes(new QuoteStoreHandler.RetrieveCallback() {
            @Override
            public void onReturn(List<Quote> quotes) {
                final Quote mostRecentQuote = QuoteHelper.removeMostRecent(quotes);
                if (mostRecentQuote == null) {
                    requestQuoteAndDisplay(true);
                } else {
                    viewWrapper.showQuoteState(viewQuoteFactory.create(mostRecentQuote));
                    quoteStoreHandler.clear(QuoteHelper.ids(quotes));
                }
            }
        });
    }

    private void showLoadingQuote() {
        viewWrapper.showLoadingQuoteState();
    }

    private void requestQuoteAndDisplay(final boolean preferLastReceivedQuote) {
        randomQuoteRequester.requestRandomQuote(quoteCallback, preferLastReceivedQuote);
    }

    private void updateQuoteAsJudgedInStore() {
        final ViewQuoteType viewQuote = viewWrapper.viewModel().newQuote();
        quoteStoreHandler.updateQuoteAsJudged(viewQuote.id());
    }

    private void removeQuoteFromStore() {
        final ViewQuote viewQuote = viewWrapper.viewModel().newQuote();
        quoteStoreHandler.clear(viewQuote.id());
    }

    private void deregisterFromOngoingQuoteRequests(final boolean shouldCancelRequests) {
        randomQuoteRequester.remove(quoteCallback, shouldCancelRequests);
    }

    private final QuoteRequestCallback quoteCallback = new QuoteRequestCallback() {

        @Override
        public void success(Quote quote) {
            viewWrapper.showQuoteState(viewQuoteFactory.create(quote));
            quoteStoreHandler.store(quote);
        }

        @Override
        public void failure() {
            viewWrapper.showFailureToGetQuoteState();
        }
    };
}
