package com.davidcryer.trumpquotes.platformindependent.presenter.presenters.factories;

import com.davidcryer.trumpquotes.platformindependent.model.quotes.network.requesters.factories.QuoteRequesterFactory;
import com.davidcryer.trumpquotes.platformindependent.model.quotes.store.QuoteRepositoryHandler;
import com.davidcryer.trumpquotes.platformindependent.presenter.presenters.Presenter;
import com.davidcryer.trumpquotes.platformindependent.presenter.presenters.SwipeQuotePresenter;
import com.davidcryer.trumpquotes.platformindependent.view.SwipeQuoteView;
import com.davidcryer.trumpquotes.platformindependent.view.viewmodels.models.ViewQuote;
import com.davidcryer.trumpquotes.platformindependent.view.viewmodels.models.factories.ViewQuoteFactory;

public class SwipeQuotePresenterFactoryImpl<ViewQuoteType extends ViewQuote> implements SwipeQuotePresenterFactory<ViewQuoteType> {
    private final QuoteRequesterFactory quoteRequesterFactory;
    private final QuoteRepositoryHandler quoteRepositoryHandler;
    private final ViewQuoteFactory<ViewQuoteType> viewQuoteFactory;

    public SwipeQuotePresenterFactoryImpl(
            final QuoteRequesterFactory quoteRequesterFactory,
            final QuoteRepositoryHandler quoteRepositoryHandler,
            final ViewQuoteFactory<ViewQuoteType> viewQuoteFactory
    ) {
        this.quoteRequesterFactory = quoteRequesterFactory;
        this.quoteRepositoryHandler = quoteRepositoryHandler;
        this.viewQuoteFactory = viewQuoteFactory;
    }

    @Override
    public Presenter<SwipeQuoteView.EventsListener> create(final SwipeQuoteView<ViewQuoteType> viewWrapper) {
        return new SwipeQuotePresenter<>(viewWrapper, quoteRequesterFactory.createRandomQuoteRequester(), quoteRepositoryHandler, viewQuoteFactory);
    }
}
