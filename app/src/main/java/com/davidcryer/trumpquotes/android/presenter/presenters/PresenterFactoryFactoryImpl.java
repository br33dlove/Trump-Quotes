package com.davidcryer.trumpquotes.android.presenter.presenters;

import com.davidcryer.trumpquotes.android.view.viewmodels.models.AndroidViewQuote;
import com.davidcryer.trumpquotes.platformindependent.model.network.quotes.requesters.QuoteRequesterFactory;
import com.davidcryer.trumpquotes.platformindependent.model.repository.quotes.QuoteStoreHandlerFactory;
import com.davidcryer.trumpquotes.platformindependent.presenter.presenters.SwipeQuotePresenterFactory;
import com.davidcryer.trumpquotes.platformindependent.presenter.presenters.implementations.swipequote.SwipeQuotePresenterFactoryImpl;
import com.davidcryer.trumpquotes.platformindependent.view.viewmodels.models.ViewQuoteFactory;

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
