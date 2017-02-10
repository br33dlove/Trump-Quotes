package com.davidcryer.trumpquotes.android.model.quotes.network.retrofit;

import com.davidcryer.trumpquotes.platformindependent.model.quotes.network.QuoteRequest;
import com.davidcryer.trumpquotes.platformindependent.model.quotes.network.QuoteRequestCallback;
import com.davidcryer.trumpquotes.platformindependent.model.quotes.network.QuoteRequestExecutor;

public class RetrofitQuoteRequestExecutor implements QuoteRequestExecutor {
    private final RetrofitQuoteCaller quoteCaller;

    public RetrofitQuoteRequestExecutor(RetrofitQuoteCaller quoteCaller) {
        this.quoteCaller = quoteCaller;
    }

    @Override
    public QuoteRequest executeRandomQuoteRequest(QuoteRequestCallback requestCallback) {
        final RetrofitQuoteRequest request = new RetrofitQuoteRequest(quoteCaller.randomQuote());
        request.execute();
        return request;
    }

    @Override
    public QuoteRequest executePersonalisedQuoteRequest(String name, QuoteRequestCallback requestCallback) {
        return null;
    }
}
