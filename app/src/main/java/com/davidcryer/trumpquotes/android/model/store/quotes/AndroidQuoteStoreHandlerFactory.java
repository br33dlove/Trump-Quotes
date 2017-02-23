package com.davidcryer.trumpquotes.android.model.store.quotes;

import com.davidcryer.trumpquotes.android.model.store.quotes.tasks.factories.QuoteStoreTaskHandlerFactory;
import com.davidcryer.trumpquotes.android.model.store.quotes.tasks.factories.QuoteStoreTasksFactoryFactory;
import com.davidcryer.trumpquotes.platformindependent.model.store.quotes.QuoteRepositoryHandler;
import com.davidcryer.trumpquotes.platformindependent.model.store.quotes.QuoteStoreHandlerFactory;

public class AndroidQuoteStoreHandlerFactory implements QuoteStoreHandlerFactory {
    private final QuoteStoreTasksFactoryFactory quoteStoreTasksFactoryFactory;
    private final QuoteStoreTaskHandlerFactory quoteStoreTaskHandlerFactory;

    public AndroidQuoteStoreHandlerFactory(QuoteStoreTasksFactoryFactory quoteStoreTasksFactoryFactory, QuoteStoreTaskHandlerFactory quoteStoreTaskHandlerFactory) {
        this.quoteStoreTasksFactoryFactory = quoteStoreTasksFactoryFactory;
        this.quoteStoreTaskHandlerFactory = quoteStoreTaskHandlerFactory;
    }

    @Override
    public QuoteRepositoryHandler create() {
        return new AndroidQuoteRepositoryTaskHandler(quoteStoreTasksFactoryFactory, quoteStoreTaskHandlerFactory.createReadTaskHandler(), quoteStoreTaskHandlerFactory.createWriteTaskHandler());
    }
}
