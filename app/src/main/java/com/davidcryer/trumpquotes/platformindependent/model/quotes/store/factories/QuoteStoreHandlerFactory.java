package com.davidcryer.trumpquotes.platformindependent.model.quotes.store.factories;

import com.davidcryer.trumpquotes.platformindependent.model.quotes.store.QuoteStoreHandler;

public interface QuoteStoreHandlerFactory {
    QuoteStoreHandler create();
}
