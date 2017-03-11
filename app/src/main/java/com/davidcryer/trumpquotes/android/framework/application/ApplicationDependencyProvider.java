package com.davidcryer.trumpquotes.android.framework.application;

import android.content.Context;
import android.content.res.Resources;
import android.database.sqlite.SQLiteDatabase;

import com.davidcryer.trumpquotes.android.framework.viewwrapperrepositories.ViewWrapperRepositoryFactory;
import com.davidcryer.trumpquotes.android.framework.viewwrapperrepositories.ViewWrapperRepositoryFactoryImpl;
import com.davidcryer.trumpquotes.android.model.framework.tasks.AndroidThreadPoolExecutorTaskSchedulerFactory;
import com.davidcryer.trumpquotes.android.model.network.retrofit.quotes.trumpapi.RetrofitTrumpQuoteService;
import com.davidcryer.trumpquotes.android.model.network.retrofit.quotes.trumpapi.RetrofitTrumpQuoteRequestFactory;
import com.davidcryer.trumpquotes.android.model.network.retrofit.quotes.trumpapi.RetrofitTrumpQuoteServiceFactory;
import com.davidcryer.trumpquotes.android.model.network.retrofit.quotes.trumpapi.RetrofitTrumpQuoteServiceFactoryImpl;
import com.davidcryer.trumpquotes.android.presenter.presenters.PresenterFactoryFactory;
import com.davidcryer.trumpquotes.android.presenter.presenters.PresenterFactoryFactoryImpl;
import com.davidcryer.trumpquotes.android.view.viewmodels.QuizAndroidViewModelFactory;
import com.davidcryer.trumpquotes.android.view.viewmodels.QuizAndroidViewModelFactoryImpl;
import com.davidcryer.trumpquotes.android.view.viewmodels.models.AndroidViewQuestion;
import com.davidcryer.trumpquotes.android.view.viewmodels.models.AndroidViewQuestionFactory;
import com.davidcryer.trumpquotes.android.view.viewwrapperfactories.ViewWrapperFactory;
import com.davidcryer.trumpquotes.android.view.viewwrapperfactories.ViewWrapperFactoryImpl;
import com.davidcryer.trumpquotes.platformindependent.model.framework.tasks.ThreadPoolExecutorTaskSchedulerFactory;
import com.davidcryer.trumpquotes.platformindependent.model.network.quotes.QuoteRequestFactory;
import com.davidcryer.trumpquotes.platformindependent.model.network.quotes.requesters.QuoteRequesterFactory;
import com.davidcryer.trumpquotes.platformindependent.model.network.quotes.requesters.QuoteRequesterFactoryImpl;
import com.davidcryer.trumpquotes.platformindependent.view.viewmodels.models.ViewQuestionFactory;

class ApplicationDependencyProvider {

    static ViewWrapperRepositoryFactory viewWrapperRepositoryFactory(final Context context) {
        return ViewWrapperRepositoryFactoryImpl.newInstance(createViewStateFactory(context));
    }

    private static ViewWrapperFactory createViewStateFactory(final Context context) {
        return new ViewWrapperFactoryImpl(createPresenterFactory(context), createQuotesAndroidViewModelFactory());
    }

    private static PresenterFactoryFactory createPresenterFactory(final Context context) {
        return new PresenterFactoryFactoryImpl(createQuoteRequesterFactory(context.getResources()), );
    }

    private static QuoteRequesterFactory createQuoteRequesterFactory(final Resources resources) {
        return new QuoteRequesterFactoryImpl(createQuoteRequestFactory(resources));
    }

    private static QuoteRequestFactory createQuoteRequestFactory(final Resources resources) {
        return new RetrofitTrumpQuoteRequestFactory(createRetrofitQuoteService(resources));
    }

    private static RetrofitTrumpQuoteService createRetrofitQuoteService(final Resources resources) {
        return createRetrofitQuoteServiceFactory(resources).create();
    }

    private static RetrofitTrumpQuoteServiceFactory createRetrofitQuoteServiceFactory(final Resources resources) {
        return new RetrofitTrumpQuoteServiceFactoryImpl(resources);
    }

    private static SQLiteDatabase.CursorFactory createCursorFactory() {
        return null;
    }

    private static ThreadPoolExecutorTaskSchedulerFactory createThreadPoolExecutorTaskSchedulerFactory() {
        return new AndroidThreadPoolExecutorTaskSchedulerFactory();
    }

    private static ViewQuestionFactory<AndroidViewQuestion> createViewQuoteFactory() {
        return new AndroidViewQuestionFactory();
    }

    private static QuizAndroidViewModelFactory createQuotesAndroidViewModelFactory() {
        return new QuizAndroidViewModelFactoryImpl();
    }
}
