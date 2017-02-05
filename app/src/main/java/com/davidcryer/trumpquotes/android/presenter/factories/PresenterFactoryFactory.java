package com.davidcryer.trumpquotes.android.presenter.factories;

import com.davidcryer.trumpquotes.android.view.viewmodels.models.AndroidViewQuote;
import com.davidcryer.trumpquotes.platformindependent.presenter.factories.QuotePresenterFactory;

public interface PresenterFactoryFactory {
    QuotePresenterFactory<AndroidViewQuote> createQuotesPresenterFactory();
}
