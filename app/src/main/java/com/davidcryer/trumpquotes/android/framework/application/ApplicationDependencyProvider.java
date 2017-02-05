package com.davidcryer.trumpquotes.android.framework.application;

import com.davidcryer.trumpquotes.android.framework.viewwrapperrepositories.ViewWrapperRepositoryFactory;
import com.davidcryer.trumpquotes.android.framework.viewwrapperrepositories.ViewWrapperRepositoryFactoryImpl;
import com.davidcryer.trumpquotes.android.view.viewmodels.factories.QuotesAndroidViewModelFactory;
import com.davidcryer.trumpquotes.android.view.viewmodels.factories.QuotesAndroidViewModelFactoryImpl;
import com.davidcryer.trumpquotes.android.view.viewmodels.models.AndroidViewQuote;
import com.davidcryer.trumpquotes.android.view.viewmodels.models.factories.AndroidViewQuoteFactory;
import com.davidcryer.trumpquotes.android.view.viewwrapperfactories.ViewWrapperFactory;
import com.davidcryer.trumpquotes.android.view.viewwrapperfactories.ViewWrapperFactoryImpl;
import com.davidcryer.trumpquotes.platformindependent.model.quotes.QuoteRequester;
import com.davidcryer.trumpquotes.platformindependent.model.quotes.QuoteStore;
import com.davidcryer.trumpquotes.platformindependent.presenter.factories.PresenterFactory;
import com.davidcryer.trumpquotes.platformindependent.presenter.factories.PresenterFactoryImpl;
import com.davidcryer.trumpquotes.platformindependent.view.viewmodels.models.factories.ViewQuoteFactory;

class ApplicationDependencyProvider {

    static ViewWrapperRepositoryFactory viewWrapperRepositoryFactory() {
        return ViewWrapperRepositoryFactoryImpl.newInstance(createViewStateFactory());
    }

    private static ViewWrapperFactory createViewStateFactory() {
        return ViewWrapperFactoryImpl.newInstance(createPresenterFactory(), createQuotesAndroidViewModelFactory());
    }

    private static PresenterFactory<AndroidViewQuote> createPresenterFactory() {
        return new PresenterFactoryImpl<>(createQuoteRequester(), createQuoteStore(), createViewQuoteFactory());
    }

    private static QuoteRequester createQuoteRequester() {
        //TODO
        return null;
    }

    private static QuoteStore createQuoteStore() {
        //TODO
        return null;
    }

    private static ViewQuoteFactory<AndroidViewQuote> createViewQuoteFactory() {
        return new AndroidViewQuoteFactory();
    }

    private static QuotesAndroidViewModelFactory createQuotesAndroidViewModelFactory() {
        return QuotesAndroidViewModelFactoryImpl.newInstance();
    }
}
