package com.davidcryer.trumpquotes.android.presenter.presenters;

import com.davidcryer.trumpquotes.android.view.viewmodels.models.AndroidViewQuestion;
import com.davidcryer.trumpquotes.platformindependent.model.network.quotes.requesters.QuoteRequesterFactory;
import com.davidcryer.trumpquotes.platformindependent.model.repository.quotes.QuoteStoreHandlerFactory;
import com.davidcryer.trumpquotes.platformindependent.presenter.presenters.SwipeQuotePresenterFactory;
import com.davidcryer.trumpquotes.platformindependent.presenter.presenters.implementations.swipequote.SwipeQuotePresenterFactoryImpl;
import com.davidcryer.trumpquotes.platformindependent.view.viewmodels.models.ViewQuestionFactory;

public class PresenterFactoryFactoryImpl implements PresenterFactoryFactory {
    private final QuoteRequesterFactory quoteRequesterFactory;
    private final QuoteStoreHandlerFactory quoteStoreHandlerFactory;
    private final ViewQuestionFactory<AndroidViewQuestion> viewQuestionFactory;

    public PresenterFactoryFactoryImpl(
            final QuoteRequesterFactory quoteRequesterFactory,
            final QuoteStoreHandlerFactory quoteStoreHandlerFactory,
            final ViewQuestionFactory<AndroidViewQuestion> viewQuestionFactory
    ) {
        this.quoteRequesterFactory = quoteRequesterFactory;
        this.quoteStoreHandlerFactory = quoteStoreHandlerFactory;
        this.viewQuestionFactory = viewQuestionFactory;
    }

    @Override
    public SwipeQuotePresenterFactory<AndroidViewQuestion> createSwipeQuotePresenterFactory() {
        return new SwipeQuotePresenterFactoryImpl<>(quoteRequesterFactory, quoteStoreHandlerFactory.create(), viewQuestionFactory);
    }
}
