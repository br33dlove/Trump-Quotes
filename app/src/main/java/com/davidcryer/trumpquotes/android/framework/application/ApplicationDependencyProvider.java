package com.davidcryer.trumpquotes.android.framework.application;

import android.content.Context;
import android.content.res.AssetManager;
import android.database.sqlite.SQLiteDatabase;
import android.os.Looper;

import com.davidc.interactor.TaskScheduler;
import com.davidc.interactor.ThreadPoolExecutorAndHandlerTaskScheduler;
import com.davidc.uiwrapper.UiWrapperRepositoryFactory;
import com.davidcryer.trumpquotes.android.framework.uiwrapperrepositories.UiWrapperRepository;
import com.davidcryer.trumpquotes.android.framework.uiwrapperrepositories.UiWrapperRepositoryFactoryImpl;
import com.davidcryer.trumpquotes.android.model.framework.localfiles.AndroidQuoteFile;
import com.davidcryer.trumpquotes.android.model.framework.network.retrofit.quotes.trumpapi.RetrofitTrumpQuoteService;
import com.davidcryer.trumpquotes.android.model.framework.network.retrofit.quotes.trumpapi.RetrofitTrumpQuoteRequestFactory;
import com.davidcryer.trumpquotes.android.model.framework.network.retrofit.quotes.trumpapi.RetrofitTrumpQuoteServiceFactory;
import com.davidcryer.trumpquotes.android.model.framework.network.retrofit.quotes.trumpapi.RetrofitTrumpQuoteServiceFactoryImpl;
import com.davidcryer.trumpquotes.android.model.framework.store.games.sqlite.GameContract;
import com.davidcryer.trumpquotes.android.model.framework.store.games.sqlite.GameContractImpl;
import com.davidcryer.trumpquotes.android.model.framework.store.games.sqlite.SQLiteGameStoreFactory;
import com.davidcryer.trumpquotes.android.model.framework.store.questions.sqlite.QuestionContract;
import com.davidcryer.trumpquotes.android.model.framework.store.questions.sqlite.QuestionContractImpl;
import com.davidcryer.trumpquotes.android.model.framework.store.questions.sqlite.SQLiteQuestionStoreFactory;
import com.davidcryer.trumpquotes.android.view.uiwrapperfactories.UiWrapperFactory;
import com.davidcryer.trumpquotes.android.view.uiwrapperfactories.UiWrapperFactoryImpl;
import com.davidcryer.trumpquotes.platformindependent.model.framework.network.TrumpQuoteApiProvider;
import com.davidcryer.trumpquotes.platformindependent.model.framework.network.quotes.trumpapi.LiveTrumpQuoteApiProvider;
import com.davidcryer.trumpquotes.platformindependent.model.framework.store.stores.TrumpQuizGameStoreFactory;
import com.davidcryer.trumpquotes.platformindependent.model.interactors.InteractorFactory;
import com.davidcryer.trumpquotes.platformindependent.model.services.ServiceFactory;
import com.davidcryer.trumpquotes.platformindependent.model.services.implementations.ServiceFactoryImpl;
import com.davidcryer.trumpquotes.platformindependent.model.framework.localfiles.quotes.QuoteFile;
import com.davidcryer.trumpquotes.platformindependent.model.framework.network.quotes.QuoteRequestFactory;
import com.davidcryer.trumpquotes.platformindependent.model.framework.localfiles.quotes.gump.GumpQuote;
import com.davidcryer.trumpquotes.platformindependent.model.framework.network.quotes.requesters.QuoteRequesterFactory;
import com.davidcryer.trumpquotes.platformindependent.model.framework.network.quotes.requesters.QuoteRequesterFactoryImpl;
import com.davidcryer.trumpquotes.platformindependent.model.framework.network.quotes.requesters.RandomQuoteRequester;
import com.davidcryer.trumpquotes.platformindependent.model.framework.store.stores.TrumpQuizGameStore;
import com.davidcryer.trumpquotes.platformindependent.model.framework.store.stores.TrumpQuizQuestionStore;
import com.google.gson.Gson;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

class ApplicationDependencyProvider {
    private final static String FILE_PATH_GUMP_QUOTES = "gump_quotes.json";

    static UiWrapperRepositoryFactory<UiWrapperRepository> uiWrapperRepositoryFactory(final Context context) {
        return UiWrapperRepositoryFactoryImpl.newInstance(createViewStateFactory(context));
    }

    private static UiWrapperFactory createViewStateFactory(final Context context) {
        return new UiWrapperFactoryImpl(createInteractorFactory(context));
    }

    private static InteractorFactory createInteractorFactory(final Context context) {
        return new InteractorFactory(createInteractorTaskScheduler(), createServiceFactory(context));
    }

    private static TaskScheduler createInteractorTaskScheduler() {
        return new ThreadPoolExecutorAndHandlerTaskScheduler(createInteractorThreadPoolExecutor(), Looper.getMainLooper());
    }

    private static ThreadPoolExecutor createInteractorThreadPoolExecutor() {
        return new ThreadPoolExecutor(1, 2, 0, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<Runnable>());
    }

    private static ServiceFactory createServiceFactory(final Context context) {
        return new ServiceFactoryImpl(
                createGumpQuoteFile(context.getAssets()),
                createRandomQuoteRequester(),
                createTrumpQuizGameStore(context),
                createTrumpQuizQuestionStore(context)
        );
    }

    private static QuoteFile createGumpQuoteFile(final AssetManager assetManager) {
        return new AndroidQuoteFile<>(FILE_PATH_GUMP_QUOTES, assetManager, GumpQuote[].class, new Gson());
    }

    private static RandomQuoteRequester createRandomQuoteRequester() {
        return createQuoteRequesterFactory().createRandomQuoteRequester();
    }

    private static QuoteRequesterFactory createQuoteRequesterFactory() {
        return new QuoteRequesterFactoryImpl(createQuoteRequestFactory());
    }

    private static QuoteRequestFactory createQuoteRequestFactory() {
        return new RetrofitTrumpQuoteRequestFactory(createRetrofitQuoteService());
    }

    private static RetrofitTrumpQuoteService createRetrofitQuoteService() {
        return createRetrofitTrumpQuoteServiceFactory().create();
    }

    private static RetrofitTrumpQuoteServiceFactory createRetrofitTrumpQuoteServiceFactory() {
        return new RetrofitTrumpQuoteServiceFactoryImpl(createTrumpQuoteApiProvider());
    }

    private static TrumpQuoteApiProvider createTrumpQuoteApiProvider() {
        return new LiveTrumpQuoteApiProvider();
    }

    private static TrumpQuizGameStore createTrumpQuizGameStore(final Context context) {
        return createTrumpQuizGameStoreFactory(context).create();
    }

    private static TrumpQuizGameStoreFactory createTrumpQuizGameStoreFactory(final Context context) {
        return new SQLiteGameStoreFactory(context, createCursorFactory(), createGameContract());
    }

    private static SQLiteDatabase.CursorFactory createCursorFactory() {
        return null;
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
