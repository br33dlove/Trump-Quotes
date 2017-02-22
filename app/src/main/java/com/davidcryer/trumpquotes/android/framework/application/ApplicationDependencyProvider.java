package com.davidcryer.trumpquotes.android.framework.application;

import android.content.Context;
import android.content.res.Resources;
import android.database.sqlite.SQLiteDatabase;

import com.davidcryer.trumpquotes.android.framework.tasks.factories.ThreadPoolExecutorTaskSchedulerFactory;
import com.davidcryer.trumpquotes.android.framework.tasks.factories.ThreadPoolExecutorTaskSchedulerFactoryImpl;
import com.davidcryer.trumpquotes.android.framework.viewwrapperrepositories.ViewWrapperRepositoryFactory;
import com.davidcryer.trumpquotes.android.framework.viewwrapperrepositories.ViewWrapperRepositoryFactoryImpl;
import com.davidcryer.trumpquotes.android.model.quotes.network.retrofit.RetrofitQuoteService;
import com.davidcryer.trumpquotes.android.model.quotes.network.retrofit.RetrofitQuoteRequestFactory;
import com.davidcryer.trumpquotes.android.model.quotes.network.retrofit.factories.RetrofitQuoteServiceFactory;
import com.davidcryer.trumpquotes.android.model.quotes.network.retrofit.factories.RetrofitQuoteServiceFactoryImpl;
import com.davidcryer.trumpquotes.android.model.quotes.store.QuoteContract;
import com.davidcryer.trumpquotes.android.model.quotes.store.QuoteContractImpl;
import com.davidcryer.trumpquotes.android.model.quotes.store.factories.AndroidQuoteStoreHandlerFactory;
import com.davidcryer.trumpquotes.android.model.quotes.store.factories.SQLiteQuoteStoreFactory;
import com.davidcryer.trumpquotes.android.model.quotes.store.tasks.factories.QuoteStoreTaskHandlerFactory;
import com.davidcryer.trumpquotes.android.model.quotes.store.tasks.factories.QuoteStoreTaskHandlerFactoryImpl;
import com.davidcryer.trumpquotes.android.model.quotes.store.tasks.factories.QuoteStoreTasksFactoryFactory;
import com.davidcryer.trumpquotes.android.model.quotes.store.tasks.factories.QuoteStoreTasksFactoryFactoryImpl;
import com.davidcryer.trumpquotes.android.model.quotes.store.tasks.factories.QuoteStoreThreadPoolExecutorFactory;
import com.davidcryer.trumpquotes.android.model.quotes.store.tasks.factories.QuoteStoreThreadPoolExecutorFactoryImpl;
import com.davidcryer.trumpquotes.android.presenter.presenters.factories.PresenterFactoryFactory;
import com.davidcryer.trumpquotes.android.presenter.presenters.factories.PresenterFactoryFactoryImpl;
import com.davidcryer.trumpquotes.android.view.viewmodels.factories.SwipeQuoteAndroidViewModelFactory;
import com.davidcryer.trumpquotes.android.view.viewmodels.factories.SwipeQuoteAndroidViewModelFactoryImpl;
import com.davidcryer.trumpquotes.android.view.viewmodels.models.AndroidViewQuote;
import com.davidcryer.trumpquotes.android.view.viewmodels.models.factories.AndroidViewQuoteFactory;
import com.davidcryer.trumpquotes.android.view.viewwrapperfactories.ViewWrapperFactory;
import com.davidcryer.trumpquotes.android.view.viewwrapperfactories.ViewWrapperFactoryImpl;
import com.davidcryer.trumpquotes.platformindependent.model.quotes.QuoteFactory;
import com.davidcryer.trumpquotes.platformindependent.model.quotes.network.QuoteRequestFactory;
import com.davidcryer.trumpquotes.platformindependent.model.quotes.network.requesters.factories.QuoteRequesterFactory;
import com.davidcryer.trumpquotes.platformindependent.model.quotes.network.requesters.factories.QuoteRequesterFactoryImpl;
import com.davidcryer.trumpquotes.platformindependent.model.quotes.network.trumpapi.TrumpQuote;
import com.davidcryer.trumpquotes.platformindependent.model.quotes.network.trumpapi.TrumpQuoteToQuoteAdapter;
import com.davidcryer.trumpquotes.platformindependent.model.quotes.store.factories.QuoteStoreFactory;
import com.davidcryer.trumpquotes.platformindependent.model.quotes.store.factories.QuoteStoreHandlerFactory;
import com.davidcryer.trumpquotes.platformindependent.view.viewmodels.models.factories.ViewQuoteFactory;

class ApplicationDependencyProvider {

    static ViewWrapperRepositoryFactory viewWrapperRepositoryFactory(final Context context) {
        return ViewWrapperRepositoryFactoryImpl.newInstance(createViewStateFactory(context));
    }

    private static ViewWrapperFactory createViewStateFactory(final Context context) {
        return new ViewWrapperFactoryImpl(createPresenterFactory(context), createQuotesAndroidViewModelFactory());
    }

    private static PresenterFactoryFactory createPresenterFactory(final Context context) {
        return new PresenterFactoryFactoryImpl(createQuoteRequesterFactory(context.getResources()), createQuoteStoreHandlerFactory(context), createViewQuoteFactory());
    }

    private static QuoteRequesterFactory createQuoteRequesterFactory(final Resources resources) {
        return new QuoteRequesterFactoryImpl(createQuoteRequestFactory(resources));
    }

    private static QuoteRequestFactory createQuoteRequestFactory(final Resources resources) {
        return new RetrofitQuoteRequestFactory(createRetrofitQuoteService(resources));
    }

    private static RetrofitQuoteService createRetrofitQuoteService(final Resources resources) {
        return createRetrofitQuoteServiceFactory(resources).create();
    }

    private static RetrofitQuoteServiceFactory createRetrofitQuoteServiceFactory(final Resources resources) {
        return new RetrofitQuoteServiceFactoryImpl(resources);
    }

    private static QuoteStoreHandlerFactory createQuoteStoreHandlerFactory(final Context context) {
        return new AndroidQuoteStoreHandlerFactory(createQuoteStoreTasksFactoryFactory(context), createQuoteStoreTaskHandlerFactory());
    }

    private static QuoteStoreTasksFactoryFactory createQuoteStoreTasksFactoryFactory(final Context context) {
        return new QuoteStoreTasksFactoryFactoryImpl(createQuoteStoreFactory(context).create());
    }

    private static QuoteStoreFactory createQuoteStoreFactory(final Context context) {
        return new SQLiteQuoteStoreFactory(context, createCursorFactory(), createQuoteContract());
    }

    private static SQLiteDatabase.CursorFactory createCursorFactory() {
        return null;
    }

    private static QuoteContract createQuoteContract() {
        return new QuoteContractImpl();
    }

    private static QuoteStoreTaskHandlerFactory createQuoteStoreTaskHandlerFactory() {
        return new QuoteStoreTaskHandlerFactoryImpl(createThreadPoolExecutorTaskSchedulerFactory(), createQuoteStoreThreadPoolExecutorFactory());
    }

    private static ThreadPoolExecutorTaskSchedulerFactory createThreadPoolExecutorTaskSchedulerFactory() {
        return new ThreadPoolExecutorTaskSchedulerFactoryImpl();
    }

    private static QuoteStoreThreadPoolExecutorFactory createQuoteStoreThreadPoolExecutorFactory() {
        return new QuoteStoreThreadPoolExecutorFactoryImpl();
    }

    private static ViewQuoteFactory<AndroidViewQuote> createViewQuoteFactory() {
        return new AndroidViewQuoteFactory();
    }

    private static SwipeQuoteAndroidViewModelFactory createQuotesAndroidViewModelFactory() {
        return new SwipeQuoteAndroidViewModelFactoryImpl();
    }
}
