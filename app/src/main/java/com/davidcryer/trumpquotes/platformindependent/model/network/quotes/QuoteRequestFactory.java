package com.davidcryer.trumpquotes.platformindependent.model.network.quotes;

import com.davidcryer.trumpquotes.platformindependent.model.framework.network.Request;
import com.davidcryer.trumpquotes.platformindependent.model.framework.network.RequestCallback;

public interface QuoteRequestFactory {
    Request randomQuoteRequest(final RequestCallback<Quote> requestCallback);
}
