package com.davidcryer.trumpquotes.android.model.quotes.network.retrofit;

import com.davidcryer.trumpquotes.platformindependent.model.quotes.network.QuoteRequest;
import com.davidcryer.trumpquotes.platformindependent.model.quotes.network.QuoteRequestCallback;
import com.davidcryer.trumpquotes.platformindependent.model.quotes.network.QuoteRequestFactory;

import java.util.Arrays;

public class RetrofitQuoteRequestFactory implements QuoteRequestFactory {
    private final RetrofitQuoteService quoteService;

    public RetrofitQuoteRequestFactory(RetrofitQuoteService quoteService) {
        this.quoteService = quoteService;
    }

    @Override
    public QuoteRequest randomQuoteRequest(final QuoteRequestCallback... requestCallbacks) {
        return new RetrofitQuoteRequest(quoteService.randomQuote(), Arrays.asList(requestCallbacks));
    }

    @Override
    public QuoteRequest personalisedQuoteRequest(String name, final QuoteRequestCallback... requestCallbacks) {
        return new RetrofitQuoteRequest(quoteService.personalisedQuote(name), Arrays.asList(requestCallbacks));
    }
}
