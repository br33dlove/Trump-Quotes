package com.davidcryer.trumpquotes.android.presenter.presenters.factories;

import com.davidcryer.trumpquotes.android.view.viewmodels.models.AndroidViewQuote;
import com.davidcryer.trumpquotes.platformindependent.presenter.presenters.factories.QuotePresenterFactory;

public interface PresenterFactoryFactory {
    QuotePresenterFactory<AndroidViewQuote> createQuotesPresenterFactory();
}
