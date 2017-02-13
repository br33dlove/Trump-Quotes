package com.davidcryer.trumpquotes.android.model.quotes.network.retrofit;

import com.davidcryer.trumpquotes.platformindependent.model.quotes.network.QuoteRequest;
import com.davidcryer.trumpquotes.platformindependent.model.quotes.network.QuoteRequestCallback;
import com.davidcryer.trumpquotes.platformindependent.model.quotes.network.QuoteRequestExecutor;

import java.util.Arrays;

public class RetrofitQuoteRequestExecutor implements QuoteRequestExecutor {
    private final RetrofitQuoteCalls quoteCaller;

    public RetrofitQuoteRequestExecutor(RetrofitQuoteCalls quoteCaller) {
        this.quoteCaller = quoteCaller;
    }

    @Override
    public QuoteRequest executeRandomQuoteRequest(final QuoteRequestCallback... requestCallbacks) {
        final QuoteRequest request = new RetrofitQuoteRequestImpl(quoteCaller.randomQuote(), Arrays.asList(requestCallbacks));
        request.executeAsync();
        return request;
    }

    @Override
    public QuoteRequest executePersonalisedQuoteRequest(String name, final QuoteRequestCallback... requestCallbacks) {
        final QuoteRequest request = new RetrofitQuoteRequestImpl(quoteCaller.personalisedQuote(name), Arrays.asList(requestCallbacks));
        request.executeAsync();
        return request;
    }
}
