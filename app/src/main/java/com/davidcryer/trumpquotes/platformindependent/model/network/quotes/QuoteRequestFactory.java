package com.davidcryer.trumpquotes.platformindependent.model.network.quotes;

public interface QuoteRequestFactory {
    QuoteRequest randomQuoteRequest(final QuoteRequestCallback... requestCallbacks);
    QuoteRequest personalisedQuoteRequest(final String name, final QuoteRequestCallback... requestCallbacks);
}
