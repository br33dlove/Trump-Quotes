package com.davidcryer.trumpquotes.platformindependent.model.network.quotes.requesters;

import com.davidcryer.trumpquotes.platformindependent.model.network.Requester;
import com.davidcryer.trumpquotes.platformindependent.model.network.quotes.QuoteRequestCallback;

public interface RandomQuoteRequester extends Requester<QuoteRequestCallback> {
    void requestRandomQuote(final QuoteRequestCallback requestCallback, final boolean preferLastReceivedQuote);
}
