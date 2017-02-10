package com.davidcryer.trumpquotes.platformindependent.model.quotes.network;

import com.davidcryer.trumpquotes.platformindependent.javahelpers.MapHelper;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class QuoteRequesterImpl implements QuoteRequester {
    private final QuoteRequestExecutor requestExecutor;
    private final Map<QuoteRequestCallback, LinkedList<QuoteRequest>> requestsMap;

    public QuoteRequesterImpl(QuoteRequestExecutor requestExecutor, Map<QuoteRequestCallback, LinkedList<QuoteRequest>> requestsMap) {
        this.requestExecutor = requestExecutor;
        this.requestsMap = requestsMap;
    }

    @Override
    public void requestRandomQuote(QuoteRequestCallback requestCallback) {
        final QuoteRequest request = requestExecutor.executeRandomQuoteRequest(requestCallback);
        MapHelper.forKeyAddValueToLinkedListInMap(requestCallback, request, requestsMap);
    }

    @Override
    public void requestPersonalisedQuote(String name, QuoteRequestCallback requestCallback) {
        final QuoteRequest request = requestExecutor.executePersonalisedQuoteRequest(name, requestCallback);
        MapHelper.forKeyAddValueToLinkedListInMap(requestCallback, request, requestsMap);
    }

    @Override
    public void release(QuoteRequestCallback requestCallback) {
        final List<QuoteRequest> requests = requestsMap.get(requestCallback);
        for (final QuoteRequest request : requests) {
            request.remove(requestCallback);
        }
        requestsMap.remove(requestCallback);
    }

    @Override
    public void cancelRequests() {
        for (final Collection<QuoteRequest> requests : requestsMap.values()) {
            for (final QuoteRequest request : requests) {
                if (!request.isCancelled()) {
                    request.cancel();
                }
            }
        }
        requestsMap.clear();
    }
}
