package com.davidcryer.trumpquotes.platformindependent.model.quotes.network;

import com.davidcryer.trumpquotes.platformindependent.model.quotes.Quote;

import java.util.Set;

public abstract class QuoteRequester {//TODO separate from Quote and make generic
    private final Set<QuoteRequestCallback> callbacks;
    private Quote lastReceivedQuote;
    private QuoteRequest request;

    public QuoteRequester(Set<QuoteRequestCallback> callbacks) {
        this.callbacks = callbacks;
    }

    void requestQuote(final QuoteRequestCallback requestCallback, final boolean preferLastReceivedQuote, final RequestProvider requestProvider) {
        if (preferLastReceivedQuote && lastReceivedQuote != null) {
            requestCallback.success(lastReceivedQuote);
        } else if (request == null) {
            executeNewRequest(requestProvider.request(), requestCallback);
        }
    }

    QuoteRequestCallback requestCallback() {
        return new QuoteRequestCallback() {
            @Override
            public void success(Quote quote) {
                onSuccess(quote);
            }

            @Override
            public void failure() {
                onFailure();
            }
        };
    }

    private void executeNewRequest(final QuoteRequest request, final QuoteRequestCallback requestCallback) {
        callbacks.add(requestCallback);
        request.executeAsync();
    }

    private void onSuccess(final Quote quote) {
        clearRequest();
        lastReceivedQuote = quote;
        notifyCallbacksOfSuccess(quote);
    }

    private void onFailure() {
        clearRequest();
        notifyCallbacksOfFailure();
    }

    void remove(QuoteRequestCallback callback, boolean cancelRequest) {
        callbacks.remove(callback);
        if (cancelRequest) {
            cancelRequest();
        }
    }

    private void notifyCallbacksOfSuccess(final Quote quote) {
        for (final QuoteRequestCallback callback : callbacks) {
            if (callback != null) {
                callback.success(quote);
            }
        }
        callbacks.clear();
    }

    private void notifyCallbacksOfFailure() {
        for (final QuoteRequestCallback callback : callbacks) {
            if (callback != null) {
                callback.failure();
            }
        }
        callbacks.clear();
    }

    private void cancelRequest() {
        if (request != null) {
            request.cancel();
            clearRequest();
        }
    }

    private void clearRequest() {
        request = null;
    }

    interface RequestProvider {
        QuoteRequest request();
    }
}
