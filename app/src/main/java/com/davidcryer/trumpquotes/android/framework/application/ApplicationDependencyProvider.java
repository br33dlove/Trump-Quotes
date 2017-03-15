package com.davidcryer.trumpquotes.android.framework.application;

import android.content.Context;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.database.sqlite.SQLiteDatabase;
import android.os.Handler;
import android.os.Looper;

import com.davidcryer.trumpquotes.android.framework.viewwrapperrepositories.ViewWrapperRepositoryFactory;
import com.davidcryer.trumpquotes.android.framework.viewwrapperrepositories.ViewWrapperRepositoryFactoryImpl;
import com.davidcryer.trumpquotes.android.model.framework.localfiles.AndroidQuoteFile;
import com.davidcryer.trumpquotes.android.model.framework.tasks.AndroidThreadPoolExecutorTaskScheduler;
import com.davidcryer.trumpquotes.android.model.framework.tasks.AndroidThreadPoolExecutorTaskSchedulerFactory;
import com.davidcryer.trumpquotes.android.model.network.retrofit.quotes.trumpapi.RetrofitTrumpQuoteService;
import com.davidcryer.trumpquotes.android.model.network.retrofit.quotes.trumpapi.RetrofitTrumpQuoteRequestFactory;
import com.davidcryer.trumpquotes.android.model.network.retrofit.quotes.trumpapi.RetrofitTrumpQuoteServiceFactory;
import com.davidcryer.trumpquotes.android.model.network.retrofit.quotes.trumpapi.RetrofitTrumpQuoteServiceFactoryImpl;
import com.davidcryer.trumpquotes.android.model.store.games.sql.GameContract;
import com.davidcryer.trumpquotes.android.model.store.games.sql.GameContractImpl;
import com.davidcryer.trumpquotes.android.model.store.games.sql.SQLiteGameStoreFactory;
import com.davidcryer.trumpquotes.android.model.store.questions.QuestionContract;
import com.davidcryer.trumpquotes.android.model.store.questions.QuestionContractImpl;
import com.davidcryer.trumpquotes.android.model.store.questions.SQLiteQuestionStoreFactory;
import com.davidcryer.trumpquotes.android.presenter.presenters.PresenterFactoryFactory;
import com.davidcryer.trumpquotes.android.presenter.presenters.PresenterFactoryFactoryImpl;
import com.davidcryer.trumpquotes.android.view.viewmodels.QuizAndroidViewModelFactory;
import com.davidcryer.trumpquotes.android.view.viewmodels.QuizAndroidViewModelFactoryImpl;
import com.davidcryer.trumpquotes.android.view.viewmodels.models.AndroidViewQuestion;
import com.davidcryer.trumpquotes.android.view.viewmodels.models.AndroidViewQuestionFactory;
import com.davidcryer.trumpquotes.android.view.viewwrapperfactories.ViewWrapperFactory;
import com.davidcryer.trumpquotes.android.view.viewwrapperfactories.ViewWrapperFactoryImpl;
import com.davidcryer.trumpquotes.platformindependent.model.domain.interactors.InteractorFactory;
import com.davidcryer.trumpquotes.platformindependent.model.domain.services.ServiceFactory;
import com.davidcryer.trumpquotes.platformindependent.model.domain.services.implementations.ServiceFactoryImpl;
import com.davidcryer.trumpquotes.platformindependent.model.framework.localfiles.QuoteFile;
import com.davidcryer.trumpquotes.platformindependent.model.framework.tasks.TaskScheduler;
import com.davidcryer.trumpquotes.platformindependent.model.framework.tasks.ThreadPoolExecutorTaskSchedulerFactory;
import com.davidcryer.trumpquotes.platformindependent.model.network.quotes.QuoteRequestFactory;
import com.davidcryer.trumpquotes.platformindependent.model.localfiles.quotes.gumpfile.GumpQuote;
import com.davidcryer.trumpquotes.platformindependent.model.network.quotes.requesters.QuoteRequesterFactory;
import com.davidcryer.trumpquotes.platformindependent.model.network.quotes.requesters.QuoteRequesterFactoryImpl;
import com.davidcryer.trumpquotes.platformindependent.model.network.quotes.requesters.RandomQuoteRequester;
import com.davidcryer.trumpquotes.platformindependent.model.store.stores.TrumpQuizGameStore;
import com.davidcryer.trumpquotes.platformindependent.model.store.stores.TrumpQuizQuestionStore;
import com.davidcryer.trumpquotes.platformindependent.view.viewmodels.models.ViewQuestionFactory;
import com.google.gson.Gson;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

class ApplicationDependencyProvider {
    private final static String FILE_PATH_GUMP_QUOTES = "gump_quotes.json";

    static ViewWrapperRepositoryFactory viewWrapperRepositoryFactory(final Context context) {
        return ViewWrapperRepositoryFactoryImpl.newInstance(createViewStateFactory(context));
    }

    private static ViewWrapperFactory createViewStateFactory(final Context context) {
        return new ViewWrapperFactoryImpl(createPresenterFactory(context), createQuotesAndroidViewModelFactory());
    }

    private static PresenterFactoryFactory createPresenterFactory(final Context context) {
        return new PresenterFactoryFactoryImpl(createViewQuestionFactory(), createInteractorFactory(context));
    }

    private static ViewQuestionFactory<AndroidViewQuestion> createViewQuestionFactory() {
        return new AndroidViewQuestionFactory();
    }

    private static InteractorFactory createInteractorFactory(final Context context) {
        return new InteractorFactory(createInteractorTaskScheduler(), createServiceFactory(context));
    }

    private static ThreadPoolExecutorTaskSchedulerFactory createThreadPoolExecutorTaskSchedulerFactory() {
        return new AndroidThreadPoolExecutorTaskSchedulerFactory();
    }

    private static TaskScheduler createInteractorTaskScheduler() {
        return new AndroidThreadPoolExecutorTaskScheduler(createInteractorThreadPoolExecutor(), new Handler(Looper.getMainLooper()));
    }

    private static ThreadPoolExecutor createInteractorThreadPoolExecutor() {
        return new ThreadPoolExecutor(1, 2, 0, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<Runnable>());
    }

    private static ServiceFactory createServiceFactory(final Context context) {
        return new ServiceFactoryImpl(
                createGumpQuoteFile(context.getAssets()),
                createRandomQuoteRequester(context.getResources()),
                createTrumpQuizGameStore(context),
                createTrumpQuizQuestionStore(context)
        );
    }

    private static QuoteFile createGumpQuoteFile(final AssetManager assetManager) {
        return new AndroidQuoteFile<>(FILE_PATH_GUMP_QUOTES, assetManager, GumpQuote[].class, new Gson());
    }

    private static RandomQuoteRequester createRandomQuoteRequester(final Resources resources) {
        return createQuoteRequesterFactory(resources).createRandomQuoteRequester();
    }

    private static QuoteRequesterFactory createQuoteRequesterFactory(final Resources resources) {
        return new QuoteRequesterFactoryImpl(createQuoteRequestFactory(resources));
    }

    private static QuoteRequestFactory createQuoteRequestFactory(final Resources resources) {
        return new RetrofitTrumpQuoteRequestFactory(createRetrofitQuoteService(resources));
    }

    private static RetrofitTrumpQuoteService createRetrofitQuoteService(final Resources resources) {
        return createRetrofitTrumpQuoteServiceFactory(resources).create();
    }

    private static RetrofitTrumpQuoteServiceFactory createRetrofitTrumpQuoteServiceFactory(final Resources resources) {
        return new RetrofitTrumpQuoteServiceFactoryImpl(resources);
    }

    private static TrumpQuizGameStore createTrumpQuizGameStore(final Context context) {
        return createSQLiteGameStoreFactory(context).create();
    }

    private static SQLiteGameStoreFactory createSQLiteGameStoreFactory(final Context context) {
        return new SQLiteGameStoreFactory(context, createCursorFactory(), createGameContract());
    }

    private static SQLiteDatabase.CursorFactory createCursorFactory() {
        return null;
    }

    private static QuizAndroidViewModelFactory createQuotesAndroidViewModelFactory() {
        return new QuizAndroidViewModelFactoryImpl();
    }

    private static GameContract createGameContract() {
        return new GameContractImpl();
    }

    private static TrumpQuizQuestionStore createTrumpQuizQuestionStore(final Context context) {
        return createSQLiteQuestionStoreFactory(context).create();
    }

    private static SQLiteQuestionStoreFactory createSQLiteQuestionStoreFactory(final Context context) {
        return new SQLiteQuestionStoreFactory(context, createCursorFactory(), createQuestionContract());
    }

    private static QuestionContract createQuestionContract() {
        return new QuestionContractImpl();
    }
}
