package com.davidcryer.trumpquotes.platformindependent.model.quotes.store.factories;

import com.davidcryer.trumpquotes.platformindependent.model.quotes.store.QuoteRepositoryHandler;

public interface QuoteStoreHandlerFactory {
    QuoteRepositoryHandler create();
}
