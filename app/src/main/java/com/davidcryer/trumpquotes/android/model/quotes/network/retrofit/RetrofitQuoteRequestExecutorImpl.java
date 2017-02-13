package com.davidcryer.trumpquotes.android.model.quotes.network.retrofit;

import com.davidcryer.trumpquotes.platformindependent.model.quotes.network.QuoteRequestCallback;
import com.davidcryer.trumpquotes.platformindependent.model.quotes.network.QuoteRequestExecutor;

public class RetrofitQuoteRequestExecutorImpl implements QuoteRequestExecutor<RetrofitQuoteRequest> {
    private final RetrofitQuoteCalls quoteCaller;

    public RetrofitQuoteRequestExecutorImpl(RetrofitQuoteCalls quoteCaller) {
        this.quoteCaller = quoteCaller;
    }

    @Override
    public RetrofitQuoteRequest executeRandomQuoteRequest(QuoteRequestCallback requestCallback) {
        final RetrofitQuoteRequest request = new RetrofitAsyncQuoteRequest(quoteCaller.randomQuote(), requestCallback);
        request.executeAsync();
        return request;
    }

    @Override
    public RetrofitQuoteRequest executePersonalisedQuoteRequest(String name, QuoteRequestCallback requestCallback) {
        final RetrofitQuoteRequest request = new RetrofitAsyncQuoteRequest(quoteCaller.personalisedQuote(name), requestCallback);
        request.executeAsync();
        return request;
    }
}
