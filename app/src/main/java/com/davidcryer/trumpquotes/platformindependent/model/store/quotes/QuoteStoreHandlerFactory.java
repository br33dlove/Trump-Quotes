package com.davidcryer.trumpquotes.platformindependent.model.store.quotes;

public interface QuoteStoreHandlerFactory {
    QuoteRepositoryHandler create();
}
