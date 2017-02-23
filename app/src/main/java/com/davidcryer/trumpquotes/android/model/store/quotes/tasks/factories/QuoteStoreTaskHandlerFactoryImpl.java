package com.davidcryer.trumpquotes.android.model.store.quotes.tasks.factories;

import com.davidcryer.trumpquotes.android.framework.tasks.TaskHandler;
import com.davidcryer.trumpquotes.android.framework.tasks.TaskHandlerImpl;
import com.davidcryer.trumpquotes.android.framework.tasks.ThreadPoolExecutorTaskSchedulerFactory;

public class QuoteStoreTaskHandlerFactoryImpl implements QuoteStoreTaskHandlerFactory {
    private final ThreadPoolExecutorTaskSchedulerFactory threadPoolExecutorTaskSchedulerFactory;
    private final QuoteStoreThreadPoolExecutorFactory quoteStoreThreadPoolExecutorFactory;

    public QuoteStoreTaskHandlerFactoryImpl(ThreadPoolExecutorTaskSchedulerFactory threadPoolExecutorTaskSchedulerFactory, QuoteStoreThreadPoolExecutorFactory quoteStoreThreadPoolExecutorFactory) {
        this.threadPoolExecutorTaskSchedulerFactory = threadPoolExecutorTaskSchedulerFactory;
        this.quoteStoreThreadPoolExecutorFactory = quoteStoreThreadPoolExecutorFactory;
    }

    @Override
    public TaskHandler createReadTaskHandler() {
        return new TaskHandlerImpl(threadPoolExecutorTaskSchedulerFactory.create(quoteStoreThreadPoolExecutorFactory.createReadTaskThreadPoolExecutor()));
    }

    @Override
    public TaskHandler createWriteTaskHandler() {
        return new TaskHandlerImpl(threadPoolExecutorTaskSchedulerFactory.create(quoteStoreThreadPoolExecutorFactory.createWriteTaskThreadPoolExecutor()));
    }
}
