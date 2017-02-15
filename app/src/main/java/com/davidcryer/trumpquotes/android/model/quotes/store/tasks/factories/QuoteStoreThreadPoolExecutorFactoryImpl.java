package com.davidcryer.trumpquotes.android.model.quotes.store.tasks.factories;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class QuoteStoreThreadPoolExecutorFactoryImpl implements QuoteStoreThreadPoolExecutorFactory {

    @Override
    public ThreadPoolExecutor createReadTaskThreadPoolExecutor() {
        final int corePoolSize = 2;
        return new ThreadPoolExecutor(corePoolSize, 4, 0, TimeUnit.MILLISECONDS, new ArrayBlockingQueue<Runnable>(corePoolSize));
    }

    @Override
    public ThreadPoolExecutor createWriteTaskThreadPoolExecutor() {
        final int corePoolSize = 1;
        return new ThreadPoolExecutor(corePoolSize, 1, 0, TimeUnit.MILLISECONDS, new ArrayBlockingQueue<Runnable>(corePoolSize));
    }
}
