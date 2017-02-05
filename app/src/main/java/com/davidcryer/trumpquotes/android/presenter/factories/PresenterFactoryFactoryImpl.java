package com.davidcryer.trumpquotes.android.presenter.factories;

import com.davidcryer.trumpquotes.android.view.viewmodels.models.AndroidViewQuote;
import com.davidcryer.trumpquotes.platformindependent.model.quotes.QuoteRequester;
import com.davidcryer.trumpquotes.platformindependent.model.quotes.QuoteRequesterFactory;
import com.davidcryer.trumpquotes.platformindependent.model.quotes.QuoteStore;
import com.davidcryer.trumpquotes.platformindependent.model.quotes.QuoteStoreFactory;
import com.davidcryer.trumpquotes.platformindependent.presenter.factories.QuotePresenterFactory;
import com.davidcryer.trumpquotes.platformindependent.presenter.factories.QuotePresenterFactoryImpl;
import com.davidcryer.trumpquotes.platformindependent.presenter.presenters.Presenter;
import com.davidcryer.trumpquotes.platformindependent.view.QuotesView;
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
