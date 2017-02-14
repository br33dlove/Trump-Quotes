package com.davidcryer.trumpquotes.android.model.quotes.network.retrofit;

import com.davidcryer.trumpquotes.platformindependent.model.quotes.network.QuoteRequest;
import com.davidcryer.trumpquotes.platformindependent.model.quotes.network.QuoteRequestCallback;
import com.davidcryer.trumpquotes.platformindependent.model.quotes.network.QuoteRequestFactory;

import java.util.Arrays;

public class RetrofitQuoteRequestFactory implements QuoteRequestFactory {
    private final RetrofitQuoteCalls quoteCaller;

    public RetrofitQuoteRequestFactory(RetrofitQuoteCalls quoteCaller) {
        this.quoteCaller = quoteCaller;
    }

    @Override
    public QuoteRequest randomQuoteRequest(final QuoteRequestCallback... requestCallbacks) {
        final QuoteRequest request = new RetrofitQuoteRequestImpl(quoteCaller.randomQuote(), Arrays.asList(requestCallbacks));
        request.executeAsync();
        return request;
    }

    @Override
    public QuoteRequest personalisedQuoteRequest(String name, final QuoteRequestCallback... requestCallbacks) {
        final QuoteRequest request = new RetrofitQuoteRequestImpl(quoteCaller.personalisedQuote(name), Arrays.asList(requestCallbacks));
        request.executeAsync();
        return request;
    }
}
