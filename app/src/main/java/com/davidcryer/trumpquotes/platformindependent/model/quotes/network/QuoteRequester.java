package com.davidcryer.trumpquotes.platformindependent.model.quotes.network;

import com.davidcryer.trumpquotes.platformindependent.model.network.Requester;

public interface QuoteRequester extends Requester<QuoteRequestCallback> {
    void requestRandomQuote(final QuoteRequestCallback requestCallback);
    void requestPersonalisedQuote(final String name, final QuoteRequestCallback requestCallback);
}
