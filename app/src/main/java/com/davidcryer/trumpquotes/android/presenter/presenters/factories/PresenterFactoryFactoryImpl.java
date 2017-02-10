package com.davidcryer.trumpquotes.android.presenter.presenters.factories;

import com.davidcryer.trumpquotes.android.view.viewmodels.models.AndroidViewQuote;
import com.davidcryer.trumpquotes.platformindependent.model.quotes.factories.QuoteRequesterFactory;
import com.davidcryer.trumpquotes.platformindependent.model.quotes.factories.QuoteResponseHandlerFactory;
import com.davidcryer.trumpquotes.platformindependent.model.quotes.factories.QuoteStoreFactory;
import com.davidcryer.trumpquotes.platformindependent.presenter.presenters.factories.QuotePresenterFactory;
import com.davidcryer.trumpquotes.platformindependent.presenter.presenters.factories.QuotePresenterFactoryImpl;
import com.davidcryer.trumpquotes.platformindependent.view.viewmodels.models.factories.ViewQuoteFactory;

public class PresenterFactoryFactoryImpl implements PresenterFactoryFactory {
    private final QuoteRequesterFactory quoteRequesterFactory;
    private final QuoteStoreFactory quoteStoreFactory;
    private final ViewQuoteFactory<AndroidViewQuote> viewQuoteFactory;

    public PresenterFactoryFactoryImpl(
            final QuoteRequesterFactory quoteRequesterFactory,
            final QuoteStoreFactory quoteStoreFactory,
            final ViewQuoteFactory<AndroidViewQuote> viewQuoteFactory
    ) {
        this.quoteRequesterFactory = quoteRequesterFactory;
        this.quoteStoreFactory = quoteStoreFactory;
        this.viewQuoteFactory = viewQuoteFactory;
    }

    @Override
    public QuotePresenterFactory<AndroidViewQuote> createQuotesPresenterFactory() {
        return new QuotePresenterFactoryImpl<>(quoteRequesterFactory.create(), quoteStoreFactory.create(), viewQuoteFactory);
    }
}
