package com.davidcryer.trumpquotes.platformindependent.model.quotes.network.requesters;

import com.davidcryer.trumpquotes.platformindependent.model.network.Requester;
import com.davidcryer.trumpquotes.platformindependent.model.quotes.network.QuoteRequestCallback;

public interface PersonalisedQuoteRequester extends Requester<QuoteRequestCallback> {
    void requestPersonalisedQuote(final String name, final QuoteRequestCallback requestCallback, final boolean preferLastReceivedQuote);
}
