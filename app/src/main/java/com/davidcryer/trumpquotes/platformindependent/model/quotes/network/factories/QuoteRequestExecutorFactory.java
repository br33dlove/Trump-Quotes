package com.davidcryer.trumpquotes.platformindependent.model.quotes.network.factories;

import com.davidcryer.trumpquotes.platformindependent.model.quotes.network.QuoteRequestFactory;

public interface QuoteRequestExecutorFactory {
    QuoteRequestFactory create();
}
