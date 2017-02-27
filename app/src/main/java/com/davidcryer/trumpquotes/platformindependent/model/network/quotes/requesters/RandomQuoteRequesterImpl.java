package com.davidcryer.trumpquotes.platformindependent.model.network.quotes.requesters;

import com.davidcryer.trumpquotes.platformindependent.model.framework.Cancelable;
import com.davidcryer.trumpquotes.platformindependent.model.framework.network.Request;
import com.davidcryer.trumpquotes.platformindependent.model.network.quotes.QuoteRequestCallback;
import com.davidcryer.trumpquotes.platformindependent.model.network.quotes.QuoteRequestFactory;

class RandomQuoteRequesterImpl extends QuoteRequester implements RandomQuoteRequester {
    private final QuoteRequestFactory requestFactory;

    RandomQuoteRequesterImpl(QuoteRequestFactory requestFactory) {
        this.requestFactory = requestFactory;
    }

    @Override
    public Cancelable request(QuoteRequestCallback callback) {
        return enqueuedQuoteRequest(new RequestProvider() {
            @Override
            public Request request(final QuoteRequestCallback callback) {
                return requestFactory.randomQuoteRequest(callback);
            }
        }, callback);
    }
}
