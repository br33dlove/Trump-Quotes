package com.davidcryer.trumpquotes.platformindependent.model.quotes.store.factories;

import com.davidcryer.trumpquotes.platformindependent.model.quotes.store.QuoteStore;

public interface QuoteStoreFactory {
    QuoteStore create();
}
