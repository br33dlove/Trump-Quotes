package com.davidcryer.trumpquotes.platformindependent.model.quotes.network;

public interface QuoteRequestExecutor<RequestType extends QuoteRequest> {
    RequestType executeRandomQuoteRequest(final QuoteRequestCallback requestCallback);
    RequestType executePersonalisedQuoteRequest(final String name, final QuoteRequestCallback requestCallback);
}
