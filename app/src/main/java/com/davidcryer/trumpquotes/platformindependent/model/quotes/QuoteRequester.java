package com.davidcryer.trumpquotes.platformindependent.model.quotes;

public interface QuoteRequester {
    void requestQuote(final QuoteResponseHandler responseHandler);
}
