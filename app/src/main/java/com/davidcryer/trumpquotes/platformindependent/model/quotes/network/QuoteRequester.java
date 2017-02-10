package com.davidcryer.trumpquotes.platformindependent.model.quotes.network;

public interface QuoteRequester {
    void requestRandomQuote(final QuoteRequestCallback requestCallback);
    void requestPersonalisedQuote(final String name, final QuoteRequestCallback requestCallback);
    void release(final QuoteRequestCallback requestCallback);
    void cancelRequests();
}
