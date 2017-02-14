package com.davidcryer.trumpquotes.platformindependent.model.quotes.network;

import com.davidcryer.trumpquotes.platformindependent.model.network.Requester;

public interface RandomQuoteRequester extends Requester<QuoteRequestCallback> {
    void requestRandomQuote(final QuoteRequestCallback requestCallback, final boolean preferLastReceivedQuote);
}
