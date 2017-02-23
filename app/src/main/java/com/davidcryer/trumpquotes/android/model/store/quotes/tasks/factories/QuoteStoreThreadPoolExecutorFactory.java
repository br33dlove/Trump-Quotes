package com.davidcryer.trumpquotes.android.model.store.quotes.tasks.factories;

import java.util.concurrent.ThreadPoolExecutor;

public interface QuoteStoreThreadPoolExecutorFactory {
    ThreadPoolExecutor createReadTaskThreadPoolExecutor();
    ThreadPoolExecutor createWriteTaskThreadPoolExecutor();
}
