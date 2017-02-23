package com.davidcryer.trumpquotes.android.framework.application;

import android.content.Context;
import android.content.res.Resources;
import android.database.sqlite.SQLiteDatabase;

import com.davidcryer.trumpquotes.android.framework.tasks.ThreadPoolExecutorTaskSchedulerFactory;
import com.davidcryer.trumpquotes.android.framework.tasks.ThreadPoolExecutorTaskSchedulerFactoryImpl;
import com.davidcryer.trumpquotes.android.framework.viewwrapperrepositories.ViewWrapperRepositoryFactory;
import com.davidcryer.trumpquotes.android.framework.viewwrapperrepositories.ViewWrapperRepositoryFactoryImpl;
import com.davidcryer.trumpquotes.android.model.network.retrofit.quotes.RetrofitQuoteService;
import com.davidcryer.trumpquotes.android.model.network.retrofit.quotes.RetrofitQuoteRequestFactory;
import com.davidcryer.trumpquotes.android.model.network.retrofit.quotes.RetrofitQuoteServiceFactory;
import com.davidcryer.trumpquotes.android.model.network.retrofit.quotes.RetrofitQuoteServiceFactoryImpl;
import com.davidcryer.trumpquotes.android.model.repository.quotes.AndroidQuoteStoreHandlerFactory;
import com.davidcryer.trumpquotes.android.model.repository.quotes.QuoteContract;
import com.davidcryer.trumpquotes.android.model.repository.quotes.QuoteContractImpl;
import com.davidcryer.trumpquotes.android.model.repository.quotes.SQLiteQuoteRepositoryFactory;
import com.davidcryer.trumpquotes.android.model.repository.quotes.tasks.factories.QuoteStoreTaskHandlerFactory;
import com.davidcryer.trumpquotes.android.model.repository.quotes.tasks.factories.QuoteStoreTaskHandlerFactoryImpl;
import com.davidcryer.trumpquotes.android.model.repository.quotes.tasks.factories.QuoteStoreTasksFactoryFactory;
import com.davidcryer.trumpquotes.android.model.repository.quotes.tasks.factories.QuoteStoreTasksFactoryFactoryImpl;
import com.davidcryer.trumpquotes.android.model.repository.quotes.tasks.factories.QuoteStoreThreadPoolExecutorFactory;
import com.davidcryer.trumpquotes.android.model.repository.quotes.tasks.factories.QuoteStoreThreadPoolExecutorFactoryImpl;
import com.davidcryer.trumpquotes.android.presenter.presenters.PresenterFactoryFactory;
import com.davidcryer.trumpquotes.android.presenter.presenters.PresenterFactoryFactoryImpl;
import com.davidcryer.trumpquotes.android.view.viewmodels.SwipeQuoteAndroidViewModelFactory;
import com.davidcryer.trumpquotes.android.view.viewmodels.SwipeQuoteAndroidViewModelFactoryImpl;
import com.davidcryer.trumpquotes.android.view.viewmodels.models.AndroidViewQuote;
import com.davidcryer.trumpquotes.android.view.viewmodels.models.AndroidViewQuoteFactory;
import com.davidcryer.trumpquotes.android.view.viewwrapperfactories.ViewWrapperFactory;
import com.davidcryer.trumpquotes.android.view.viewwrapperfactories.ViewWrapperFactoryImpl;
import com.davidcryer.trumpquotes.platformindependent.model.network.quotes.QuoteRequestFactory;
import com.davidcryer.trumpquotes.platformindependent.model.network.quotes.requesters.QuoteRequesterFactory;
import com.davidcryer.trumpquotes.platformindependent.model.network.quotes.requesters.QuoteRequesterFactoryImpl;
import com.davidcryer.trumpquotes.platformindependent.model.repository.quotes.QuoteRepositoryFactory;
import com.davidcryer.trumpquotes.platformindependent.model.repository.quotes.QuoteStoreHandlerFactory;
import com.davidcryer.trumpquotes.platformindependent.view.viewmodels.models.ViewQuoteFactory;

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

    private static QuoteRepositoryFactory createQuoteStoreFactory(final Context context) {
        return new SQLiteQuoteRepositoryFactory(context, createCursorFactory(), createQuoteContract());
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
