package com.davidcryer.trumpquotes.android.model.quotes.network.retrofit;

import com.davidcryer.trumpquotes.platformindependent.model.quotes.network.QuoteRequest;
import com.davidcryer.trumpquotes.platformindependent.model.quotes.network.QuoteRequestCallback;
import com.davidcryer.trumpquotes.platformindependent.model.quotes.network.QuoteRequestFactory;
import com.davidcryer.trumpquotes.platformindependent.model.quotes.network.trumpapi.TrumpQuoteToQuoteAdapter;

import java.util.Arrays;

public class RetrofitQuoteRequestFactory implements QuoteRequestFactory {
    private final TrumpQuoteToQuoteAdapter trumpQuoteToQuoteAdapter;
    private final RetrofitQuoteService quoteService;

    public RetrofitQuoteRequestFactory(TrumpQuoteToQuoteAdapter trumpQuoteToQuoteAdapter, RetrofitQuoteService quoteService) {
        this.trumpQuoteToQuoteAdapter = trumpQuoteToQuoteAdapter;
        this.quoteService = quoteService;
    }

    @Override
    public QuoteRequest randomQuoteRequest(final QuoteRequestCallback... requestCallbacks) {
        return new RetrofitQuoteRequest(trumpQuoteToQuoteAdapter, quoteService.randomQuote(), Arrays.asList(requestCallbacks));
    }

    @Override
    public QuoteRequest personalisedQuoteRequest(String name, final QuoteRequestCallback... requestCallbacks) {
        return new RetrofitQuoteRequest(trumpQuoteToQuoteAdapter, quoteService.personalisedQuote(name), Arrays.asList(requestCallbacks));
    }
}
