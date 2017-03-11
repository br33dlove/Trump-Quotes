package com.davidcryer.trumpquotes.android.presenter.presenters;

import com.davidcryer.trumpquotes.android.view.viewmodels.models.AndroidViewQuestion;
import com.davidcryer.trumpquotes.platformindependent.model.network.quotes.requesters.QuoteRequesterFactory;
import com.davidcryer.trumpquotes.platformindependent.model.repository.quotes.QuoteStoreHandlerFactory;
import com.davidcryer.trumpquotes.platformindependent.presenter.presenters.SwipeQuotePresenterFactory;
import com.davidcryer.trumpquotes.platformindependent.presenter.presenters.implementations.swipequote.SwipeQuotePresenterFactoryImpl;
import com.davidcryer.trumpquotes.platformindependent.view.viewmodels.models.ViewQuoteFactory;

public class PresenterFactoryFactoryImpl implements PresenterFactoryFactory {
    private final QuoteRequesterFactory quoteRequesterFactory;
    private final QuoteStoreHandlerFactory quoteStoreHandlerFactory;
    private final ViewQuoteFactory<AndroidViewQuestion> viewQuoteFactory;

    public PresenterFactoryFactoryImpl(
            final QuoteRequesterFactory quoteRequesterFactory,
            final QuoteStoreHandlerFactory quoteStoreHandlerFactory,
            final ViewQuoteFactory<AndroidViewQuestion> viewQuoteFactory
    ) {
        this.quoteRequesterFactory = quoteRequesterFactory;
        this.quoteStoreHandlerFactory = quoteStoreHandlerFactory;
        this.viewQuoteFactory = viewQuoteFactory;
    }

    @Override
    public SwipeQuotePresenterFactory<AndroidViewQuestion> createSwipeQuotePresenterFactory() {
        return new SwipeQuotePresenterFactoryImpl<>(quoteRequesterFactory, quoteStoreHandlerFactory.create(), viewQuoteFactory);
    }
}
