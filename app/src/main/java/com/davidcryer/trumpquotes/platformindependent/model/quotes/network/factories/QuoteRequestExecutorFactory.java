package com.davidcryer.trumpquotes.platformindependent.model.quotes.network.factories;

import com.davidcryer.trumpquotes.platformindependent.model.quotes.network.QuoteRequestExecutor;

public interface QuoteRequestExecutorFactory {
    QuoteRequestExecutor create();
}
