package com.davidcryer.trumpquotes.android.presenter.presenters.factories;

import com.davidcryer.trumpquotes.android.view.viewmodels.models.AndroidViewQuote;
import com.davidcryer.trumpquotes.platformindependent.model.quotes.network.requesters.factories.QuoteRequesterFactory;
import com.davidcryer.trumpquotes.platformindependent.model.quotes.store.factories.QuoteStoreHandlerFactory;
import com.davidcryer.trumpquotes.platformindependent.presenter.presenters.factories.QuotePresenterFactory;
import com.davidcryer.trumpquotes.platformindependent.presenter.presenters.factories.QuotePresenterFactoryImpl;
import com.davidcryer.trumpquotes.platformindependent.view.viewmodels.models.factories.ViewQuoteFactory;

public class PresenterFactoryFactoryImpl implements PresenterFactoryFactory {
    private final QuoteRequesterFactory quoteRequesterFactory;
    private final QuoteStoreHandlerFactory quoteStoreHandlerFactory;
    private final ViewQuoteFactory<AndroidViewQuote> viewQuoteFactory;

    public PresenterFactoryFactoryImpl(
            final QuoteRequesterFactory quoteRequesterFactory,
            final QuoteStoreHandlerFactory quoteStoreHandlerFactory,
            final ViewQuoteFactory<AndroidViewQuote> viewQuoteFactory
    ) {
        this.quoteRequesterFactory = quoteRequesterFactory;
        this.quoteStoreHandlerFactory = quoteStoreHandlerFactory;
        this.viewQuoteFactory = viewQuoteFactory;
    }

    @Override
    public QuotePresenterFactory<AndroidViewQuote> createQuotesPresenterFactory() {
        return new QuotePresenterFactoryImpl<>(quoteRequesterFactory, quoteStoreHandlerFactory.create(), viewQuoteFactory);
    }
}
