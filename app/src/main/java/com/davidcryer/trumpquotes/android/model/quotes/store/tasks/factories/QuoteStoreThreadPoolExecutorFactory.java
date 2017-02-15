package com.davidcryer.trumpquotes.android.model.quotes.store.tasks.factories;

import java.util.concurrent.ThreadPoolExecutor;

public interface QuoteStoreThreadPoolExecutorFactory {
    ThreadPoolExecutor createReadTaskThreadPoolExecutor();
    ThreadPoolExecutor createWriteTaskThreadPoolExecutor();
}
