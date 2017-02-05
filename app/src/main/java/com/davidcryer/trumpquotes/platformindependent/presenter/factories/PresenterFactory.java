package com.davidcryer.trumpquotes.platformindependent.presenter.factories;

import com.davidcryer.trumpquotes.platformindependent.presenter.presenters.Presenter;
import com.davidcryer.trumpquotes.platformindependent.view.QuotesView;
import com.davidcryer.trumpquotes.platformindependent.view.viewmodels.models.ViewQuote;

public interface PresenterFactory<ViewQuoteType extends ViewQuote> {
    Presenter<QuotesView.EventsListener> createQuotesPresenter(final QuotesView<ViewQuoteType> viewWrapper);
}
