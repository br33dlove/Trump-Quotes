package com.davidcryer.trumpquotes.platformindependent.model.quotes.network;

import java.util.Set;

public class PersonalisedQuoteRequesterImpl extends QuoteRequester implements PersonalisedQuoteRequester {
    private final QuoteRequestFactory requestFactory;

    public PersonalisedQuoteRequesterImpl(QuoteRequestFactory requestFactory, Set<QuoteRequestCallback> callbacks) {
        super(callbacks);
        this.requestFactory = requestFactory;
    }

    @Override
    public void requestPersonalisedQuote(final String name, QuoteRequestCallback requestCallback, boolean preferLastReceivedQuote) {
        requestQuote(requestCallback, preferLastReceivedQuote, new RequestProvider() {
            @Override
            public QuoteRequest request() {
                return requestFactory.personalisedQuoteRequest(name, requestCallback());
            }
        });
    }

    @Override
    public void remove(QuoteRequestCallback callback, boolean cancelRequest) {
        super.remove(callback, cancelRequest);
    }
}
