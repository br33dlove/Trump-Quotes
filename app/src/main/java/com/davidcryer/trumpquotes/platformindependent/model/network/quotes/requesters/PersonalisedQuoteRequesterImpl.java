package com.davidcryer.trumpquotes.platformindependent.model.network.quotes.requesters;

import com.davidcryer.trumpquotes.platformindependent.model.network.quotes.QuoteRequest;
import com.davidcryer.trumpquotes.platformindependent.model.network.quotes.QuoteRequestCallback;
import com.davidcryer.trumpquotes.platformindependent.model.network.quotes.QuoteRequestFactory;

import java.util.Set;

class PersonalisedQuoteRequesterImpl extends QuoteRequester implements PersonalisedQuoteRequester {
    private final QuoteRequestFactory requestFactory;

    PersonalisedQuoteRequesterImpl(QuoteRequestFactory requestFactory, Set<QuoteRequestCallback> callbacks) {
        super(callbacks);
        this.requestFactory = requestFactory;
    }

    @Override
    public void requestPersonalisedQuote(final String name, QuoteRequestCallback requestCallback, boolean preferLastReceivedQuote) {
        requestQuote(requestCallback, preferLastReceivedQuote, new RequestProvider() {
            @Override
            public QuoteRequest request(final QuoteRequestCallback callback) {
                return requestFactory.personalisedQuoteRequest(name, callback);
            }
        });
    }

    @Override
    public void remove(QuoteRequestCallback callback, boolean cancelRequest) {
        super.remove(callback, cancelRequest);
    }
}
