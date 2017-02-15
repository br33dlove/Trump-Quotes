package com.davidcryer.trumpquotes.platformindependent.presenter.presenters.factories;

import com.davidcryer.trumpquotes.platformindependent.model.quotes.network.requesters.factories.QuoteRequesterFactory;
import com.davidcryer.trumpquotes.platformindependent.model.quotes.store.QuoteStoreHandler;
import com.davidcryer.trumpquotes.platformindependent.presenter.presenters.Presenter;
import com.davidcryer.trumpquotes.platformindependent.presenter.presenters.QuotesPresenter;
import com.davidcryer.trumpquotes.platformindependent.view.QuotesView;
import com.davidcryer.trumpquotes.platformindependent.view.viewmodels.models.ViewQuote;
import com.davidcryer.trumpquotes.platformindependent.view.viewmodels.models.factories.ViewQuoteFactory;

public class QuotePresenterFactoryImpl<ViewQuoteType extends ViewQuote> implements QuotePresenterFactory<ViewQuoteType> {
    private final QuoteRequesterFactory quoteRequesterFactory;
    private final QuoteStoreHandler quoteStoreHandler;
    private final ViewQuoteFactory<ViewQuoteType> viewQuoteFactory;

    public QuotePresenterFactoryImpl(
            final QuoteRequesterFactory quoteRequesterFactory,
            final QuoteStoreHandler quoteStoreHandler,
            final ViewQuoteFactory<ViewQuoteType> viewQuoteFactory
    ) {
        this.quoteRequesterFactory = quoteRequesterFactory;
        this.quoteStoreHandler = quoteStoreHandler;
        this.viewQuoteFactory = viewQuoteFactory;
    }

    @Override
    public Presenter<QuotesView.EventsListener> create(final QuotesView<ViewQuoteType> viewWrapper) {
        return new QuotesPresenter<>(viewWrapper, quoteRequesterFactory.createRandomQuoteRequester(), quoteRequesterFactory.createPersonalisedQuoteRequester(), quoteStoreHandler, viewQuoteFactory);
    }
}
