package com.davidcryer.trumpquotes.platformindependent.model.quotes.network;

import com.davidcryer.trumpquotes.platformindependent.model.quotes.Quote;

import java.util.Set;

public class RandomQuoteRequesterImpl implements RandomQuoteRequester {
    private final QuoteRequestFactory requestFactory;
    private final Set<QuoteRequestCallback> callbacks;
    private Quote lastReceivedQuote;
    private QuoteRequest request;

    public RandomQuoteRequesterImpl(QuoteRequestFactory requestFactory, Set<QuoteRequestCallback> callbacks) {
        this.requestFactory = requestFactory;
        this.callbacks = callbacks;
    }

    @Override
    public void requestRandomQuote(QuoteRequestCallback requestCallback, boolean preferLastReceivedQuote) {
        if (preferLastReceivedQuote) {
            requestCallback.success(lastReceivedQuote);
        } else if (request == null) {
            makeNewRequest(requestCallback);
        }
    }

    private void makeNewRequest(final QuoteRequestCallback requestCallback) {
        callbacks.add(requestCallback);
        request = requestFactory.randomQuoteRequest(new QuoteRequestCallback() {
            @Override
            public void success(Quote quote) {
                clearRequest();
                lastReceivedQuote = quote;
                notifyCallbacksOfSuccess(quote);
            }

            @Override
            public void failure() {
                clearRequest();
                notifyCallbacksOfFailure();
            }
        });
        request.executeAsync();
    }

    @Override
    public void remove(QuoteRequestCallback callback, boolean cancelRequest) {
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
}
