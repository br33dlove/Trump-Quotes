package com.davidcryer.trumpquotes.platformindependent.presenter.presenters.factories;

import com.davidcryer.trumpquotes.platformindependent.presenter.presenters.Presenter;
import com.davidcryer.trumpquotes.platformindependent.view.QuotesView;
import com.davidcryer.trumpquotes.platformindependent.view.viewmodels.models.ViewQuote;

public interface QuotePresenterFactory<ViewQuoteType extends ViewQuote> {
    Presenter<QuotesView.EventsListener> create(final QuotesView<ViewQuoteType> viewWrapper);
}
