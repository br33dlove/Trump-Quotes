package com.davidcryer.trumpquotes.android.model.quotes.store.factories;

import com.davidcryer.trumpquotes.android.model.quotes.store.AndroidQuoteRepositoryTaskHandler;
import com.davidcryer.trumpquotes.android.model.quotes.store.tasks.factories.QuoteStoreTaskHandlerFactory;
import com.davidcryer.trumpquotes.android.model.quotes.store.tasks.factories.QuoteStoreTasksFactoryFactory;
import com.davidcryer.trumpquotes.platformindependent.model.quotes.store.QuoteRepositoryHandler;
import com.davidcryer.trumpquotes.platformindependent.model.quotes.store.factories.QuoteStoreHandlerFactory;

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
