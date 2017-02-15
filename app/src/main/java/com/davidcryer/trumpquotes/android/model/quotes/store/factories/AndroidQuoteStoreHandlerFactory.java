package com.davidcryer.trumpquotes.android.model.quotes.store.factories;

import com.davidcryer.trumpquotes.android.model.quotes.store.AndroidQuoteStoreTaskHandler;
import com.davidcryer.trumpquotes.android.model.quotes.store.threadscheduling.QuoteStoreTasksFactoryFactory;
import com.davidcryer.trumpquotes.android.framework.threadscheduling.TaskHandler;
import com.davidcryer.trumpquotes.platformindependent.model.quotes.store.QuoteStoreHandler;
import com.davidcryer.trumpquotes.platformindependent.model.quotes.store.factories.QuoteStoreHandlerFactory;

public class AndroidQuoteStoreHandlerFactory implements QuoteStoreHandlerFactory {
    private final QuoteStoreTasksFactoryFactory quoteStoreTasksFactoryFactory;
    private final TaskHandler readTaskHandler;
    private final TaskHandler writeTaskHandler;

    public AndroidQuoteStoreHandlerFactory(QuoteStoreTasksFactoryFactory quoteStoreTasksFactoryFactory, TaskHandler readTaskHandler, TaskHandler writeTaskHandler) {
        this.quoteStoreTasksFactoryFactory = quoteStoreTasksFactoryFactory;
        this.readTaskHandler = readTaskHandler;
        this.writeTaskHandler = writeTaskHandler;
    }

    @Override
    public QuoteStoreHandler create() {
        return new AndroidQuoteStoreTaskHandler(quoteStoreTasksFactoryFactory, readTaskHandler, writeTaskHandler);
    }
}
