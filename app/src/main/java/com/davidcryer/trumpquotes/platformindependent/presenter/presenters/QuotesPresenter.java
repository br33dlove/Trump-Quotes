package com.davidcryer.trumpquotes.platformindependent.presenter.presenters;

import com.davidcryer.trumpquotes.platformindependent.model.quotes.Quote;
import com.davidcryer.trumpquotes.platformindependent.model.quotes.network.requesters.RandomQuoteRequester;
import com.davidcryer.trumpquotes.platformindependent.model.quotes.store.QuoteStoreHandler;
import com.davidcryer.trumpquotes.platformindependent.model.quotes.network.QuoteRequestCallback;
import com.davidcryer.trumpquotes.platformindependent.model.quotes.network.requesters.PersonalisedQuoteRequester;
import com.davidcryer.trumpquotes.platformindependent.model.quotes.QuoteHelper;
import com.davidcryer.trumpquotes.platformindependent.view.viewmodels.models.ViewQuoteHelper;
import com.davidcryer.trumpquotes.platformindependent.view.QuotesView;
import com.davidcryer.trumpquotes.platformindependent.view.viewmodels.models.ViewQuote;
import com.davidcryer.trumpquotes.platformindependent.view.viewmodels.models.factories.ViewQuoteFactory;

import java.util.List;

public class QuotesPresenter<ViewQuoteType extends ViewQuote> extends Presenter<QuotesView.EventsListener> {
    private final QuotesView<ViewQuoteType> viewWrapper;
    private final RandomQuoteRequester randomQuoteRequester;
    private final PersonalisedQuoteRequester personalisedQuoteRequester;//TODO
    private final QuoteStoreHandler quoteStoreHandler;
    private final ViewQuoteFactory<ViewQuoteType> viewQuoteFactory;

    public QuotesPresenter(
            final QuotesView<ViewQuoteType> viewWrapper,
            final RandomQuoteRequester randomQuoteRequester,
            final PersonalisedQuoteRequester personalisedQuoteRequester,
            final QuoteStoreHandler quoteStoreHandler,
            final ViewQuoteFactory<ViewQuoteType> viewQuoteFactory
    ) {
        this.viewWrapper = viewWrapper;
        this.randomQuoteRequester = randomQuoteRequester;
        this.personalisedQuoteRequester = personalisedQuoteRequester;
        this.quoteStoreHandler = quoteStoreHandler;
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
                requestNewQuoteAndDisplay(false);
            }

            @Override
            public void onRequestQuoteHistory() {
                getQuoteHistoryFromStoreAndDisplay();
            }

            @Override
            public void onNewQuoteSwipedLeft() {
                showLoadingNewQuote();
                updateNewQuoteAsJudgedInStoreAndAddToHistory();
                requestNewQuoteAndDisplay(false);
            }

            @Override
            public void onNewQuoteSwipedRight() {
                showLoadingNewQuote();
                removeNewQuoteFromStore();
                requestNewQuoteAndDisplay(false);
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
            public void onReleaseResources(final boolean isFinishing) {
                deregisterFromOngoingQuoteRequests(isFinishing);
            }
        };
    }

    private void getUnJudgedQuoteFromStoreOrRequestNewQuoteAndDisplay() {
        quoteStoreHandler.retrieveUnJudgedQuotes(new QuoteStoreHandler.RetrieveCallback() {
            @Override
            public void onReturn(List<Quote> quotes) {
                final Quote mostRecentQuote = QuoteHelper.removeMostRecent(quotes);
                if (mostRecentQuote == null) {
                    requestNewQuoteAndDisplay(true);
                } else {
                    viewWrapper.hideLoadingNewQuote();
                    viewWrapper.showNewQuote(viewQuoteFactory.create(mostRecentQuote));
                    quoteStoreHandler.clear(QuoteHelper.ids(quotes));
                }
            }
        });
    }

    private void showLoadingNewQuote() {
        viewWrapper.showLoadingNewQuote();
    }

    private void requestNewQuoteAndDisplay(final boolean preferLastReceivedQuote) {
        randomQuoteRequester.requestRandomQuote(quoteCallback, preferLastReceivedQuote);
    }

    private void getQuoteHistoryFromStoreAndDisplay() {
        quoteStoreHandler.retrieveJudgedQuotes(new QuoteStoreHandler.RetrieveCallback() {
            @Override
            public void onReturn(List<Quote> quotes) {
                final List<ViewQuoteType> viewQuotes = viewQuoteFactory.create(quotes);
                viewWrapper.showQuoteHistory(viewQuotes);
            }
        });
    }

    private void updateNewQuoteAsJudgedInStoreAndAddToHistory() {
        final ViewQuoteType viewQuote = viewWrapper.viewModel().newQuote();
        quoteStoreHandler.updateQuoteAsJudged(viewQuote.id());
        viewWrapper.addNewQuoteToHistory(viewQuote);
    }

    private void removeNewQuoteFromStore() {
        final ViewQuote viewQuote = viewWrapper.viewModel().newQuote();
        quoteStoreHandler.clear(viewQuote.id());
    }

    private void removeQuoteFromStoreAndHistory(final int index) {
        final ViewQuoteType viewQuote = viewWrapper.viewModel().quoteHistory()[index];
        quoteStoreHandler.clear(viewQuote.id());
        viewWrapper.removeQuoteInHistory(viewQuote);
    }

    private void removeAllJudgedQuotesFromStoreAndAllQuotesFromHistory() {
        final ViewQuote[] viewQuotesInHistory = viewWrapper.viewModel().quoteHistory();
        final String[] quoteIds = ViewQuoteHelper.ids(viewQuotesInHistory);
        quoteStoreHandler.clear(quoteIds);
        viewWrapper.removeAllQuotesInHistory();
    }

    private void deregisterFromOngoingQuoteRequests(final boolean shouldCancelRequests) {
        randomQuoteRequester.remove(quoteCallback, shouldCancelRequests);
    }

    private final QuoteRequestCallback quoteCallback = new QuoteRequestCallback() {

        @Override
        public void success(Quote quote) {
            viewWrapper.hideLoadingNewQuote();
            viewWrapper.showNewQuote(viewQuoteFactory.create(quote));
            quoteStoreHandler.store(quote);
        }

        @Override
        public void failure() {
            viewWrapper.hideLoadingNewQuote();
            viewWrapper.showFailureToGetNewQuote();
        }
    };
}
