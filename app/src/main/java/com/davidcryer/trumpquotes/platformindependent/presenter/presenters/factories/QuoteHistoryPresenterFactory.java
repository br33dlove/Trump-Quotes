package com.davidcryer.trumpquotes.platformindependent.presenter.presenters.factories;

import com.davidcryer.trumpquotes.platformindependent.presenter.presenters.Presenter;
import com.davidcryer.trumpquotes.platformindependent.view.QuoteHistoryView;
import com.davidcryer.trumpquotes.platformindependent.view.viewmodels.models.ViewQuote;

public interface QuoteHistoryPresenterFactory<ViewQuoteType extends ViewQuote> {
    Presenter<QuoteHistoryView.EventsListener> create(final QuoteHistoryView<ViewQuoteType> viewWrapper);
}
