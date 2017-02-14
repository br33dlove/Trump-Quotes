package com.davidcryer.trumpquotes.platformindependent.model.quotes.network;

public interface QuoteRequestFactory {
    QuoteRequest randomQuoteRequest(final QuoteRequestCallback... requestCallbacks);
    QuoteRequest personalisedQuoteRequest(final String name, final QuoteRequestCallback... requestCallbacks);
}
