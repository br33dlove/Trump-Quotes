package com.davidcryer.trumpquotes.platformindependent.model.network.quotes;

import com.davidcryer.trumpquotes.platformindependent.model.network.quotes.QuoteRequestFactory;

public interface QuoteRequestExecutorFactory {
    QuoteRequestFactory create();
}
