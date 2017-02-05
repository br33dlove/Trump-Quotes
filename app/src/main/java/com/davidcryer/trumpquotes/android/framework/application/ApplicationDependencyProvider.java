package com.davidcryer.trumpquotes.android.framework.application;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.davidcryer.trumpquotes.android.framework.viewwrapperrepositories.ViewWrapperRepositoryFactory;
import com.davidcryer.trumpquotes.android.framework.viewwrapperrepositories.ViewWrapperRepositoryFactoryImpl;
import com.davidcryer.trumpquotes.android.model.quotes.SQLiteQuoteStoreFactory;
import com.davidcryer.trumpquotes.android.presenter.factories.PresenterFactoryFactory;
import com.davidcryer.trumpquotes.android.presenter.factories.PresenterFactoryFactoryImpl;
import com.davidcryer.trumpquotes.android.view.viewmodels.factories.QuotesAndroidViewModelFactory;
import com.davidcryer.trumpquotes.android.view.viewmodels.factories.QuotesAndroidViewModelFactoryImpl;
import com.davidcryer.trumpquotes.android.view.viewmodels.models.AndroidViewQuote;
import com.davidcryer.trumpquotes.android.view.viewmodels.models.factories.AndroidViewQuoteFactory;
import com.davidcryer.trumpquotes.android.view.viewwrapperfactories.ViewWrapperFactory;
import com.davidcryer.trumpquotes.android.view.viewwrapperfactories.ViewWrapperFactoryImpl;
import com.davidcryer.trumpquotes.platformindependent.model.quotes.QuoteRequester;
import com.davidcryer.trumpquotes.platformindependent.model.quotes.QuoteRequesterFactory;
import com.davidcryer.trumpquotes.platformindependent.model.quotes.QuoteStore;
import com.davidcryer.trumpquotes.platformindependent.model.quotes.QuoteStoreFactory;
import com.davidcryer.trumpquotes.platformindependent.presenter.factories.QuotePresenterFactory;
import com.davidcryer.trumpquotes.platformindependent.presenter.factories.QuotePresenterFactoryImpl;
import com.davidcryer.trumpquotes.platformindependent.view.viewmodels.models.factories.ViewQuoteFactory;

class ApplicationDependencyProvider {

    static ViewWrapperRepositoryFactory viewWrapperRepositoryFactory(final Context context) {
        return ViewWrapperRepositoryFactoryImpl.newInstance(createViewStateFactory(context));
    }

    private static ViewWrapperFactory createViewStateFactory(final Context context) {
        return new ViewWrapperFactoryImpl(createPresenterFactory(context), createQuotesAndroidViewModelFactory());
    }

    private static PresenterFactoryFactory createPresenterFactory(final Context context) {
        return new PresenterFactoryFactoryImpl(createQuoteRequesterFactory(), createQuoteStoreFactory(context), createViewQuoteFactory());
    }

    private static QuoteRequesterFactory createQuoteRequesterFactory() {
        return null;//TODO
    }

    private static QuoteStoreFactory createQuoteStoreFactory(final Context context) {
        return new SQLiteQuoteStoreFactory(context, createCursorFactory());
    }

    private static SQLiteDatabase.CursorFactory createCursorFactory() {
        return null;
    }

    private static ViewQuoteFactory<AndroidViewQuote> createViewQuoteFactory() {
        return new AndroidViewQuoteFactory();
    }

    private static QuotesAndroidViewModelFactory createQuotesAndroidViewModelFactory() {
        return QuotesAndroidViewModelFactoryImpl.newInstance();
    }
}
