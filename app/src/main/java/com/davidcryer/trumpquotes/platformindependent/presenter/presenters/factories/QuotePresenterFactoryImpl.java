package com.davidcryer.trumpquotes.platformindependent.presenter.presenters.factories;

import com.davidcryer.trumpquotes.platformindependent.model.quotes.store.QuoteStoreHandler;
import com.davidcryer.trumpquotes.platformindependent.model.quotes.network.PersonalisedQuoteRequester;
import com.davidcryer.trumpquotes.platformindependent.presenter.presenters.Presenter;
import com.davidcryer.trumpquotes.platformindependent.presenter.presenters.QuotesPresenter;
import com.davidcryer.trumpquotes.platformindependent.view.QuotesView;
import com.davidcryer.trumpquotes.platformindependent.view.viewmodels.models.ViewQuote;
import com.davidcryer.trumpquotes.platformindependent.view.viewmodels.models.factories.ViewQuoteFactory;

public class QuotePresenterFactoryImpl<ViewQuoteType extends ViewQuote> implements QuotePresenterFactory<ViewQuoteType> {
    private final PersonalisedQuoteRequester personalisedQuoteRequester;
    private final QuoteStoreHandler quoteStoreHandler;
    private final ViewQuoteFactory<ViewQuoteType> viewQuoteFactory;

    public QuotePresenterFactoryImpl(
            final PersonalisedQuoteRequester personalisedQuoteRequester,
            final QuoteStoreHandler quoteStoreHandler,
            final ViewQuoteFactory<ViewQuoteType> viewQuoteFactory
    ) {
        this.personalisedQuoteRequester = personalisedQuoteRequester;
        this.quoteStoreHandler = quoteStoreHandler;
        this.viewQuoteFactory = viewQuoteFactory;
    }

    @Override
    public Presenter<QuotesView.EventsListener> create(final QuotesView<ViewQuoteType> viewWrapper) {
        return new QuotesPresenter<>(viewWrapper, personalisedQuoteRequester, quoteStoreHandler, viewQuoteFactory);
    }
}
