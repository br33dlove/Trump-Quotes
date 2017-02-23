package com.davidcryer.trumpquotes.android.model.repository.quotes.tasks.factories;

import com.davidcryer.trumpquotes.android.framework.tasks.TaskHandler;

public interface QuoteStoreTaskHandlerFactory {
    TaskHandler createReadTaskHandler();
    TaskHandler createWriteTaskHandler();
}
