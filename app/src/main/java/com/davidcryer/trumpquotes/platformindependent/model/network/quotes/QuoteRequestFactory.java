package com.davidcryer.trumpquotes.platformindependent.model.network.quotes;

import com.davidcryer.trumpquotes.platformindependent.model.framework.network.Request;

public interface QuoteRequestFactory {
    Request randomQuoteRequest(final QuoteRequestCallback requestCallback);
}
