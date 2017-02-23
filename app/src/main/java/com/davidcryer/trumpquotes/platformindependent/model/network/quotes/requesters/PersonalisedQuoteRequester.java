package com.davidcryer.trumpquotes.platformindependent.model.network.quotes.requesters;

import com.davidcryer.trumpquotes.platformindependent.model.network.Requester;
import com.davidcryer.trumpquotes.platformindependent.model.network.quotes.QuoteRequestCallback;

interface PersonalisedQuoteRequester extends Requester<QuoteRequestCallback> {
    void requestPersonalisedQuote(final String name, final QuoteRequestCallback requestCallback, final boolean preferLastReceivedQuote);
}
