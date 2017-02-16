package com.davidcryer.trumpquotes.platformindependent.presenter.presenters.factories;

import com.davidcryer.trumpquotes.platformindependent.presenter.presenters.Presenter;
import com.davidcryer.trumpquotes.platformindependent.view.SwipeQuoteView;
import com.davidcryer.trumpquotes.platformindependent.view.viewmodels.models.ViewQuote;

public interface QuotePresenterFactory<ViewQuoteType extends ViewQuote> {
    Presenter<SwipeQuoteView.EventsListener> create(final SwipeQuoteView<ViewQuoteType> viewWrapper);
}
