package com.davidcryer.trumpquotes.platformindependent.model.quotes.store.factories;

import com.davidcryer.trumpquotes.platformindependent.model.quotes.store.QuoteRepository;

public interface QuoteRepositoryFactory {
    QuoteRepository create();
}
