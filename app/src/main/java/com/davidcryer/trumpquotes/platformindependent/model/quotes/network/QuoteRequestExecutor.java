package com.davidcryer.trumpquotes.platformindependent.model.quotes.network;

public interface QuoteRequestExecutor<RequestType extends QuoteRequest> {
    RequestType requestRandomQuote(final QuoteRequestCallback requestCallback);
    RequestType requestPersonalisedQuote(final String name, final QuoteRequestCallback requestCallback);
}
