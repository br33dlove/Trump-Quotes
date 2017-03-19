package com.davidcryer.trumpquotes.platformindependent.model.framework.network.quotes.requesters;

import com.davidcryer.trumpquotes.platformindependent.model.framework.Cancelable;
import com.davidcryer.trumpquotes.platformindependent.model.framework.network.Request;
import com.davidcryer.trumpquotes.platformindependent.model.framework.network.RequestCallback;
import com.davidcryer.trumpquotes.platformindependent.model.framework.network.Requester;
import com.davidcryer.trumpquotes.platformindependent.model.framework.network.quotes.Quote;
import com.davidcryer.trumpquotes.platformindependent.model.framework.network.quotes.QuoteRequestFactory;

class RandomQuoteRequesterImpl extends Requester<Quote> implements RandomQuoteRequester {
    private final QuoteRequestFactory requestFactory;

    RandomQuoteRequesterImpl(QuoteRequestFactory requestFactory) {
        this.requestFactory = requestFactory;
    }

    @Override
    public Cancelable randomQuoteRequest(RequestCallback<Quote> callback) {
        return enqueuedQuoteRequest(new RequestProvider<Quote>() {
            @Override
            public Request request(final RequestCallback<Quote> callback) {
                return requestFactory.randomQuoteRequest(callback);
            }
        }, callback);
    }
}
