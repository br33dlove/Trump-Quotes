package com.davidcryer.trumpquotes.platformindependent.presenter.presenters;

import com.davidcryer.trumpquotes.platformindependent.model.quotes.Quote;
import com.davidcryer.trumpquotes.platformindependent.model.quotes.store.QuoteRepositoryHandler;
import com.davidcryer.trumpquotes.platformindependent.view.QuoteHistoryView;
import com.davidcryer.trumpquotes.platformindependent.view.viewmodels.models.ViewQuote;
import com.davidcryer.trumpquotes.platformindependent.view.viewmodels.models.ViewQuoteHelper;
import com.davidcryer.trumpquotes.platformindependent.view.viewmodels.models.factories.ViewQuoteFactory;

import java.util.List;

public class QuoteHistoryPresenter<ViewQuoteType extends ViewQuote> extends Presenter<QuoteHistoryView.EventsListener> {
    private final QuoteHistoryView<ViewQuoteType> viewWrapper;
    private final QuoteRepositoryHandler quoteRepositoryHandler;
    private final ViewQuoteFactory<ViewQuoteType> viewQuoteFactory;

    public QuoteHistoryPresenter(
            QuoteHistoryView<ViewQuoteType> viewWrapper,
            QuoteRepositoryHandler quoteRepositoryHandler,
            ViewQuoteFactory<ViewQuoteType> viewQuoteFactory
    ) {
        this.viewWrapper = viewWrapper;
        this.quoteRepositoryHandler = quoteRepositoryHandler;
        this.viewQuoteFactory = viewQuoteFactory;
    }

    @Override
    public QuoteHistoryView.EventsListener eventsListener() {
        return new QuoteHistoryView.EventsListener() {
            @Override
            public void onRequestQuoteHistory() {
                getQuoteHistoryFromStoreAndDisplay();
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
            public void onReleaseResources(boolean isFinishing) {

            }
        };
    }

    private void getQuoteHistoryFromStoreAndDisplay() {
        quoteRepositoryHandler.retrieveJudgedQuotes(new QuoteRepositoryHandler.RetrieveCallback() {
            @Override
            public void onReturn(List<Quote> quotes) {
                final List<ViewQuoteType> viewQuotes = viewQuoteFactory.create(quotes);
                viewWrapper.showQuoteHistory(viewQuotes);
            }
        });
    }

    private void removeQuoteFromStoreAndHistory(final int index) {
        final ViewQuoteType viewQuote = viewWrapper.viewModel().quoteHistory()[index];
        quoteRepositoryHandler.clear(viewQuote.id());
        viewWrapper.removeQuoteInHistory(viewQuote);
    }

    private void removeAllJudgedQuotesFromStoreAndAllQuotesFromHistory() {
        final ViewQuote[] viewQuotesInHistory = viewWrapper.viewModel().quoteHistory();
        final String[] quoteIds = ViewQuoteHelper.ids(viewQuotesInHistory);
        quoteRepositoryHandler.clear(quoteIds);
        viewWrapper.removeAllQuotesInHistory();
    }
}
