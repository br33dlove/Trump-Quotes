package com.davidcryer.trumpquotes.platformindependent.model.quotes.network;

public interface Request {
    boolean hasResponse();
    void cancel();
    boolean isCancelled();
    void remove(final QuoteRequestCallback requestCallback);
}
