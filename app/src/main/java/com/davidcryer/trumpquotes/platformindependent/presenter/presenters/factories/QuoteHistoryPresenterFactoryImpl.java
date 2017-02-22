package com.davidcryer.trumpquotes.platformindependent.presenter.presenters.factories;

import com.davidcryer.trumpquotes.platformindependent.model.quotes.store.QuoteRepositoryHandler;
import com.davidcryer.trumpquotes.platformindependent.presenter.presenters.Presenter;
import com.davidcryer.trumpquotes.platformindependent.presenter.presenters.QuoteHistoryPresenter;
import com.davidcryer.trumpquotes.platformindependent.view.QuoteHistoryView;
import com.davidcryer.trumpquotes.platformindependent.view.viewmodels.models.ViewQuote;
import com.davidcryer.trumpquotes.platformindependent.view.viewmodels.models.factories.ViewQuoteFactory;

public class QuoteHistoryPresenterFactoryImpl<ViewQuoteType extends ViewQuote> implements QuoteHistoryPresenterFactory<ViewQuoteType> {
    private final QuoteRepositoryHandler quoteRepositoryHandler;
    private final ViewQuoteFactory<ViewQuoteType> viewQuoteFactory;

    public QuoteHistoryPresenterFactoryImpl(QuoteRepositoryHandler quoteRepositoryHandler, ViewQuoteFactory<ViewQuoteType> viewQuoteFactory) {
        this.quoteRepositoryHandler = quoteRepositoryHandler;
        this.viewQuoteFactory = viewQuoteFactory;
    }

    @Override
    public Presenter<QuoteHistoryView.EventsListener> create(QuoteHistoryView<ViewQuoteType> viewWrapper) {
        return new QuoteHistoryPresenter<>(viewWrapper, quoteRepositoryHandler, viewQuoteFactory);
    }
}
