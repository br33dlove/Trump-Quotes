package com.davidcryer.trumpquotes.platformindependent.presenter.presenters;

import com.davidcryer.trumpquotes.platformindependent.model.quotes.Quote;
import com.davidcryer.trumpquotes.platformindependent.model.quotes.QuoteRequester;
import com.davidcryer.trumpquotes.platformindependent.model.quotes.QuoteResponseHandler;
import com.davidcryer.trumpquotes.platformindependent.model.quotes.QuoteStore;
import com.davidcryer.trumpquotes.platformindependent.model.quotes.factories.QuoteResponseHandlerFactory;
import com.davidcryer.trumpquotes.platformindependent.model.quotes.QuoteHelper;
import com.davidcryer.trumpquotes.platformindependent.view.viewmodels.models.ViewQuoteHelper;
import com.davidcryer.trumpquotes.platformindependent.view.QuotesView;
import com.davidcryer.trumpquotes.platformindependent.view.viewmodels.models.ViewQuote;
import com.davidcryer.trumpquotes.platformindependent.view.viewmodels.models.factories.ViewQuoteFactory;

import java.util.List;

public class QuotesPresenter<ViewQuoteType extends ViewQuote> extends Presenter<QuotesView.EventsListener> {
    private final QuotesView<ViewQuoteType> viewWrapper;
    private final QuoteResponseHandler quoteResponseHandler;
    private final QuoteRequester quoteRequester;
    private final QuoteStore quoteStore;
    private final ViewQuoteFactory<ViewQuoteType> viewQuoteFactory;

    public QuotesPresenter(
            final QuotesView<ViewQuoteType> viewWrapper,
            final QuoteResponseHandlerFactory quoteResponseHandlerFactory,
            final QuoteRequester quoteRequester,
            final QuoteStore quoteStore,
            final ViewQuoteFactory<ViewQuoteType> viewQuoteFactory
    ) {
        this.viewWrapper = viewWrapper;
        this.quoteResponseHandler = quoteResponseHandlerFactory.create(quoteCallback);
        this.quoteRequester = quoteRequester;
        this.quoteStore = quoteStore;
        this.viewQuoteFactory = viewQuoteFactory;
    }

    @Override
    public QuotesView.EventsListener eventsListener() {
        return new QuotesView.EventsListener() {

            @Override
            public void onRequestFirstNewQuote() {
                showLoadingNewQuote();
                getUnJudgedQuoteFromStoreOrRequestNewQuoteAndDisplay();
            }

            @Override
            public void onRetryNewQuoteRequestClicked() {
                showLoadingNewQuote();
                requestNewQuoteAndDisplay();
            }

            @Override
            public void onRequestQuoteHistory() {
                getQuoteHistoryFromStoreAndDisplay();
            }

            @Override
            public void onNewQuoteSwipedLeft() {
                showLoadingNewQuote();
                updateNewQuoteAsJudgedInStoreAndAddToHistory();
                requestNewQuoteAndDisplay();
            }

            @Override
            public void onNewQuoteSwipedRight() {
                showLoadingNewQuote();
                removeNewQuoteFromStore();
                requestNewQuoteAndDisplay();
            }

            @Override
            public void onDeleteQuoteInHistoryClicked(int index) {
                removeQuoteFromStoreAndHistory(index);
            }

            @Override
            public void onDeleteAllQuotesClicked() {
                removeAllJudgedQuotesFromStoreAndAllQuotesFromHistory();
            }

            @Override
            public void onReleaseResources() {
                cancelOngoingQuoteRequests();
            }
        };
    }

    private void getUnJudgedQuoteFromStoreOrRequestNewQuoteAndDisplay() {
        quoteStore.retrieveUnJudgedQuotes(new QuoteStore.RetrieveCallback() {
            @Override
            public void onReturn(List<Quote> quotes) {
                final Quote mostRecentQuote = QuoteHelper.removeMostRecent(quotes);
                if (mostRecentQuote == null) {
                    requestNewQuoteAndDisplay();
                } else {
                    viewWrapper.hideLoadingNewQuote();
                    viewWrapper.showNewQuote(viewQuoteFactory.create(mostRecentQuote));
                    quoteStore.clear(QuoteHelper.ids(quotes));
                }
            }
        });
    }

    private void showLoadingNewQuote() {
        viewWrapper.showLoadingNewQuote();
    }

    private void requestNewQuoteAndDisplay() {
        quoteRequester.requestQuote(quoteResponseHandler);
    }

    private void getQuoteHistoryFromStoreAndDisplay() {
        quoteStore.retrieveJudgedQuotes(new QuoteStore.RetrieveCallback() {
            @Override
            public void onReturn(List<Quote> quotes) {
                final List<ViewQuoteType> viewQuotes = viewQuoteFactory.create(quotes);
                viewWrapper.showQuoteHistory(viewQuotes);
            }
        });
    }

    private void updateNewQuoteAsJudgedInStoreAndAddToHistory() {
        final ViewQuoteType viewQuote = viewWrapper.viewModel().newQuote();
        quoteStore.updateQuoteAsJudged(viewQuote.id());
        viewWrapper.addNewQuoteToHistory(viewQuote);
    }

    private void removeNewQuoteFromStore() {
        final ViewQuote viewQuote = viewWrapper.viewModel().newQuote();
        quoteStore.clear(viewQuote.id());
    }

    private void removeQuoteFromStoreAndHistory(final int index) {
        final ViewQuoteType viewQuote = viewWrapper.viewModel().quoteHistory()[index];
        quoteStore.clear(viewQuote.id());
        viewWrapper.removeQuoteInHistory(viewQuote);
    }

    private void removeAllJudgedQuotesFromStoreAndAllQuotesFromHistory() {
        final ViewQuote[] viewQuotesInHistory = viewWrapper.viewModel().quoteHistory();
        final String[] quoteIds = ViewQuoteHelper.ids(viewQuotesInHistory);
        quoteStore.clear(quoteIds);
        viewWrapper.removeAllQuotesInHistory();
    }

    private void cancelOngoingQuoteRequests() {
        quoteRequester.cancelRequest(quoteResponseHandler);
    }

    private final QuoteResponseHandler.Callback quoteCallback = new QuoteResponseHandler.Callback() {

        @Override
        public void success(Quote quote) {
            viewWrapper.hideLoadingNewQuote();
            viewWrapper.showNewQuote(viewQuoteFactory.create(quote));
            quoteStore.store(quote);
        }

        @Override
        public void failure() {
            viewWrapper.hideLoadingNewQuote();
            viewWrapper.showFailureToGetNewQuote();
        }
    };
}
