package com.davidcryer.trumpquotes.platformindependent.model.quotes.network;

public interface QuoteRequestExecutor {
    QuoteRequest executeRandomQuoteRequest(final QuoteRequestCallback... requestCallbacks);
    QuoteRequest executePersonalisedQuoteRequest(final String name, final QuoteRequestCallback... requestCallbacks);
}
