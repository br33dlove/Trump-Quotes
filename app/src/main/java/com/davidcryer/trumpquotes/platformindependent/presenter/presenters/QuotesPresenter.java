package com.davidcryer.trumpquotes.platformindependent.presenter.presenters;

import com.davidcryer.trumpquotes.platformindependent.model.quotes.Quote;
import com.davidcryer.trumpquotes.platformindependent.model.quotes.QuoteRequester;
import com.davidcryer.trumpquotes.platformindependent.model.quotes.QuoteResponseHandler;
import com.davidcryer.trumpquotes.platformindependent.model.quotes.QuoteStore;
import com.davidcryer.trumpquotes.platformindependent.model.quotes.factories.QuoteResponseHandlerFactory;
import com.davidcryer.trumpquotes.platformindependent.view.QuotesView;
import com.davidcryer.trumpquotes.platformindependent.view.viewmodels.models.ViewQuote;
import com.davidcryer.trumpquotes.platformindependent.view.viewmodels.models.factories.ViewQuoteFactory;

import java.util.Arrays;

public class QuotesPresenter<ViewQuoteType extends ViewQuote> extends Presenter<QuotesView.EventsListener> {
    private final QuotesView<ViewQuoteType> viewWrapper;
    private final QuoteResponseHandlerFactory quoteResponseHandlerFactory;
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
        this.quoteResponseHandlerFactory = quoteResponseHandlerFactory;
        this.quoteRequester = quoteRequester;
        this.quoteStore = quoteStore;
        this.viewQuoteFactory = viewQuoteFactory;
    }

    @Override
    public QuotesView.EventsListener eventsListener() {
        return new QuotesView.EventsListener() {//TODO tidy up

            @Override
            public void onRequestNewQuote() {
                viewWrapper.showLoadingNewQuote();
                //TODO check store first
                quoteRequester.requestQuote(quoteResponseHandlerFactory.create(quoteCallback));
            }

            @Override
            public void onRequestQuoteHistory() {
                final Quote[] quotes = quoteStore.retrieve();//TODO do in worker thread
                final ViewQuoteType[] viewQuotes = viewQuoteFactory.createArray(quotes);
                viewWrapper.showQuoteHistory(Arrays.asList(viewQuotes));
            }

            @Override
            public void onNewQuoteSwipedLeft() {
                viewWrapper.showLoadingNewQuote();
                final ViewQuoteType viewQuote = viewWrapper.viewModel().newQuote();
                viewWrapper.addNewQuoteToHistory(viewQuote);
                quoteRequester.requestQuote(quoteResponseHandlerFactory.create(quoteCallback));
            }

            @Override
            public void onNewQuoteSwipedRight() {
                viewWrapper.showLoadingNewQuote();
                final ViewQuote viewQuote = viewWrapper.viewModel().newQuote();
                quoteStore.clear(viewQuote.id());
                quoteRequester.requestQuote(quoteResponseHandlerFactory.create(quoteCallback));
            }

            @Override
            public void onDeleteQuoteInHistoryClicked(int index) {
                final ViewQuoteType viewQuote = viewWrapper.viewModel().quoteHistory()[index];
                quoteStore.clear(viewQuote.id());
                viewWrapper.removeQuoteInHistory(viewQuote);
            }

            @Override
            public void onDeleteAllQuotesClicked() {
                final ViewQuote[] viewQuotes = viewWrapper.viewModel().quoteHistory();
                final String[] quoteIds = new String[viewQuotes.length];
                for (int i = 0; i < viewQuotes.length; i++) {
                    quoteIds[i] = viewQuotes[i].id();
                }
                quoteStore.clear(quoteIds);
                viewWrapper.removeAllQuotesInHistory();
            }

            @Override
            public void onReleaseResources() {
                //TODO cancel request if ongoing
            }
        };
    }

    private final QuoteResponseHandler.Callback quoteCallback = new QuoteResponseHandler.Callback() {

        @Override
        public void success(Quote quote) {
            viewWrapper.hideLoadingNewQuote();
            viewWrapper.showNewQuote(viewQuoteFactory.create(quote));
            quoteStore.store(quote);//TODO do in worker thread
        }

        @Override
        public void failure() {
            viewWrapper.hideLoadingNewQuote();
            viewWrapper.showFailureToGetNewQuote();
        }
    };
}
