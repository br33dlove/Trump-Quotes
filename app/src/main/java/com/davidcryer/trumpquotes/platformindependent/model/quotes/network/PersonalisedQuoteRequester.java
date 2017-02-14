package com.davidcryer.trumpquotes.platformindependent.model.quotes.network;

import com.davidcryer.trumpquotes.platformindependent.model.network.Requester;

public interface PersonalisedQuoteRequester extends Requester<QuoteRequestCallback> {
    void requestPersonalisedQuote(final String name, final QuoteRequestCallback requestCallback, final boolean preferLastReceivedQuote);
}
