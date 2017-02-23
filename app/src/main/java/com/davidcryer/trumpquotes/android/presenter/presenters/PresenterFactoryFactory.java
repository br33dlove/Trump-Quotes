package com.davidcryer.trumpquotes.android.presenter.presenters;

import com.davidcryer.trumpquotes.android.view.viewmodels.models.AndroidViewQuote;
import com.davidcryer.trumpquotes.platformindependent.presenter.presenters.SwipeQuotePresenterFactory;

public interface PresenterFactoryFactory {
    SwipeQuotePresenterFactory<AndroidViewQuote> createSwipeQuotePresenterFactory();
}
