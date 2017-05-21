package com.davidcryer.trumpquotes.android.model.framework.network;

import com.davidcryer.trumpquotes.android.model.framework.store.Cancelable;

public abstract class Requester<ReturnType> {

    protected Requester() {

    }

    protected Cancelable enqueuedQuoteRequest(final RequestProvider<ReturnType> requestProvider, final RequestCallback<ReturnType> requestCallback) {
        return enqueuedQuoteRequest(requestProvider.request(requestCallback));
    }

    private Cancelable enqueuedQuoteRequest(final Request request) {
        request.enqueue();
        return request;
    }

    protected interface RequestProvider<ReturnType> {
        Request request(final RequestCallback<ReturnType> callback);
    }
}
