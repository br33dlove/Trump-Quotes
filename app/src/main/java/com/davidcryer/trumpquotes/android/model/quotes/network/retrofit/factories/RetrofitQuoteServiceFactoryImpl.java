package com.davidcryer.trumpquotes.android.model.quotes.network.retrofit.factories;

import com.davidcryer.trumpquotes.android.model.quotes.network.retrofit.RetrofitQuoteService;

public class RetrofitQuoteServiceFactoryImpl implements RetrofitQuoteServiceFactory {
    private final QuoteRetrofitFactory quoteRetrofitFactory;

    public RetrofitQuoteServiceFactoryImpl(QuoteRetrofitFactory quoteRetrofitFactory) {
        this.quoteRetrofitFactory = quoteRetrofitFactory;
    }

    @Override
    public RetrofitQuoteService create() {
        return quoteRetrofitFactory.create().create(RetrofitQuoteService.class);
    }
}
