package com.davidcryer.trumpquotes.android.model.quotes.store.factories;

import com.davidcryer.trumpquotes.android.model.quotes.store.AndroidQuoteStoreTaskHandler;
import com.davidcryer.trumpquotes.android.model.quotes.store.tasks.factories.QuoteStoreTaskHandlerFactory;
import com.davidcryer.trumpquotes.android.model.quotes.store.tasks.factories.QuoteStoreTasksFactoryFactory;
import com.davidcryer.trumpquotes.android.framework.tasks.TaskHandler;
import com.davidcryer.trumpquotes.platformindependent.model.quotes.store.QuoteStoreHandler;
import com.davidcryer.trumpquotes.platformindependent.model.quotes.store.factories.QuoteStoreHandlerFactory;

public class AndroidQuoteStoreHandlerFactory implements QuoteStoreHandlerFactory {
    private final QuoteStoreTasksFactoryFactory quoteStoreTasksFactoryFactory;
    private final QuoteStoreTaskHandlerFactory quoteStoreTaskHandlerFactory;

    public AndroidQuoteStoreHandlerFactory(QuoteStoreTasksFactoryFactory quoteStoreTasksFactoryFactory, QuoteStoreTaskHandlerFactory quoteStoreTaskHandlerFactory) {
        this.quoteStoreTasksFactoryFactory = quoteStoreTasksFactoryFactory;
        this.quoteStoreTaskHandlerFactory = quoteStoreTaskHandlerFactory;
    }

    @Override
    public QuoteStoreHandler create() {
        return new AndroidQuoteStoreTaskHandler(quoteStoreTasksFactoryFactory, quoteStoreTaskHandlerFactory.createReadTaskHandler(), quoteStoreTaskHandlerFactory.createWriteTaskHandler());
    }
}
