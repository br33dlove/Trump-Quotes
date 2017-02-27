package com.davidcryer.trumpquotes.platformindependent.model.network.quotes.requesters;

import com.davidcryer.trumpquotes.platformindependent.model.framework.Cancelable;
import com.davidcryer.trumpquotes.platformindependent.model.framework.network.Request;
import com.davidcryer.trumpquotes.platformindependent.model.network.quotes.QuoteRequestCallback;

abstract class QuoteRequester {

    QuoteRequester() {

    }

    Cancelable enqueuedQuoteRequest(final RequestProvider requestProvider, final QuoteRequestCallback requestCallback) {
        return enqueuedQuoteRequest(requestProvider.request(requestCallback));
    }

    private Cancelable enqueuedQuoteRequest(final Request request) {
        request.enqueue();
        return request;
    }

    interface RequestProvider {
        Request request(final QuoteRequestCallback callback);
    }
}
