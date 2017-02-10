package com.davidcryer.trumpquotes.platformindependent.presenter.presenters.factories;

import com.davidcryer.trumpquotes.platformindependent.model.quotes.QuoteStoreHandler;
import com.davidcryer.trumpquotes.platformindependent.model.quotes.network.QuoteRequester;
import com.davidcryer.trumpquotes.platformindependent.presenter.presenters.Presenter;
import com.davidcryer.trumpquotes.platformindependent.presenter.presenters.QuotesPresenter;
import com.davidcryer.trumpquotes.platformindependent.view.QuotesView;
import com.davidcryer.trumpquotes.platformindependent.view.viewmodels.models.ViewQuote;
import com.davidcryer.trumpquotes.platformindependent.view.viewmodels.models.factories.ViewQuoteFactory;

public class QuotePresenterFactoryImpl<ViewQuoteType extends ViewQuote> implements QuotePresenterFactory<ViewQuoteType> {
    private final QuoteRequester quoteRequester;
    private final QuoteStoreHandler quoteStoreHandler;
    private final ViewQuoteFactory<ViewQuoteType> viewQuoteFactory;

    public QuotePresenterFactoryImpl(
            final QuoteRequester quoteRequester,
            final QuoteStoreHandler quoteStoreHandler,
            final ViewQuoteFactory<ViewQuoteType> viewQuoteFactory
    ) {
        this.quoteRequester = quoteRequester;
        this.quoteStoreHandler = quoteStoreHandler;
        this.viewQuoteFactory = viewQuoteFactory;
    }

    @Override
    public Presenter<QuotesView.EventsListener> create(final QuotesView<ViewQuoteType> viewWrapper) {
        return new QuotesPresenter<>(viewWrapper, quoteRequester, quoteStoreHandler, viewQuoteFactory);
    }
}
