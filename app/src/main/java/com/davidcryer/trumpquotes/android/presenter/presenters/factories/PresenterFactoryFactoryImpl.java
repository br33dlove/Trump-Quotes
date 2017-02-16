package com.davidcryer.trumpquotes.android.presenter.presenters.factories;

import com.davidcryer.trumpquotes.android.view.viewmodels.models.AndroidViewQuote;
import com.davidcryer.trumpquotes.platformindependent.model.quotes.network.requesters.factories.QuoteRequesterFactory;
import com.davidcryer.trumpquotes.platformindependent.model.quotes.store.factories.QuoteStoreHandlerFactory;
import com.davidcryer.trumpquotes.platformindependent.presenter.presenters.factories.SwipeQuotePresenterFactory;
import com.davidcryer.trumpquotes.platformindependent.presenter.presenters.factories.SwipeQuotePresenterFactoryImpl;
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
    public SwipeQuotePresenterFactory<AndroidViewQuote> createSwipeQuotePresenterFactory() {
        return new SwipeQuotePresenterFactoryImpl<>(quoteRequesterFactory, quoteStoreHandlerFactory.create(), viewQuoteFactory);
    }
}
