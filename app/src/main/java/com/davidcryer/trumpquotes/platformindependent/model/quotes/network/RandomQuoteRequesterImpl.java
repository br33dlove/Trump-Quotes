package com.davidcryer.trumpquotes.platformindependent.model.quotes.network;

import java.util.Set;

public class RandomQuoteRequesterImpl extends QuoteRequester implements RandomQuoteRequester {
    private final QuoteRequestFactory requestFactory;

    public RandomQuoteRequesterImpl(QuoteRequestFactory requestFactory, Set<QuoteRequestCallback> callbacks) {
        super(callbacks);
        this.requestFactory = requestFactory;
    }

    @Override
    public void requestRandomQuote(QuoteRequestCallback requestCallback, boolean preferLastReceivedQuote) {
        requestQuote(requestCallback, preferLastReceivedQuote, new RequestProvider() {
            @Override
            public QuoteRequest request() {
                return requestFactory.randomQuoteRequest(requestCallback());
            }
        });
    }

    @Override
    public void remove(QuoteRequestCallback callback, boolean cancelRequest) {
        super.remove(callback, cancelRequest);
    }
}
