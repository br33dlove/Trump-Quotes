package com.davidcryer.trumpquotes.platformindependent.model.quotes.network.requesters;

import com.davidcryer.trumpquotes.platformindependent.model.quotes.network.QuoteRequest;
import com.davidcryer.trumpquotes.platformindependent.model.quotes.network.QuoteRequestCallback;
import com.davidcryer.trumpquotes.platformindependent.model.quotes.network.QuoteRequestFactory;

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
            public QuoteRequest request(final QuoteRequestCallback callback) {
                return requestFactory.randomQuoteRequest(callback);
            }
        });
    }

    @Override
    public void remove(QuoteRequestCallback callback, boolean cancelRequest) {
        super.remove(callback, cancelRequest);
    }
}
