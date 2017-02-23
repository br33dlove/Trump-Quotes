package com.davidcryer.trumpquotes.platformindependent.presenter.presenters;

import com.davidcryer.trumpquotes.platformindependent.view.SwipeQuoteView;
import com.davidcryer.trumpquotes.platformindependent.view.viewmodels.models.ViewQuote;

public interface SwipeQuotePresenterFactory<ViewQuoteType extends ViewQuote> {
    Presenter<SwipeQuoteView.EventsListener> create(final SwipeQuoteView<ViewQuoteType> viewWrapper);
}
