package com.davidcryer.trumpquotes.android.model.framework.network.retrofit.quotes.trumpapi;

import com.davidcryer.trumpquotes.android.model.framework.network.TrumpQuoteApiProvider;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitTrumpQuoteServiceFactoryImpl implements RetrofitTrumpQuoteServiceFactory {
    private final TrumpQuoteApiProvider trumpQuoteApiProvider;

    public RetrofitTrumpQuoteServiceFactoryImpl(TrumpQuoteApiProvider trumpQuoteApiProvider) {
        this.trumpQuoteApiProvider = trumpQuoteApiProvider;
    }

    @Override
    public RetrofitTrumpQuoteService create() {
        final Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(trumpQuoteApiProvider.baseUrl())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return retrofit.create(RetrofitTrumpQuoteService.class);
    }
}
