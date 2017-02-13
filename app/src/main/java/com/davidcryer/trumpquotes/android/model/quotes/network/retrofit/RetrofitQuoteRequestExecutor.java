package com.davidcryer.trumpquotes.android.model.quotes.network.retrofit;

import com.davidcryer.trumpquotes.platformindependent.model.quotes.network.QuoteRequest;
import com.davidcryer.trumpquotes.platformindependent.model.quotes.network.QuoteRequestCallback;
import com.davidcryer.trumpquotes.platformindependent.model.quotes.network.QuoteRequestExecutor;

public class RetrofitQuoteRequestExecutor implements QuoteRequestExecutor {
    private final RetrofitQuoteCalls quoteCaller;

    public RetrofitQuoteRequestExecutor(RetrofitQuoteCalls quoteCaller) {
        this.quoteCaller = quoteCaller;
    }

    @Override
    public QuoteRequest executeRandomQuoteRequest(QuoteRequestCallback requestCallback) {
        final QuoteRequest request = new RetrofitQuoteRequestImpl(quoteCaller.randomQuote(), requestCallback);
        request.executeAsync();
        return request;
    }

    @Override
    public QuoteRequest executePersonalisedQuoteRequest(String name, QuoteRequestCallback requestCallback) {
        final QuoteRequest request = new RetrofitQuoteRequestImpl(quoteCaller.personalisedQuote(name), requestCallback);
        request.executeAsync();
        return request;
    }
}
