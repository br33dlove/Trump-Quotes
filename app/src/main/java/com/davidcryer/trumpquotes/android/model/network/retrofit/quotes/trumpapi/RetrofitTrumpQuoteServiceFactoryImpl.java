package com.davidcryer.trumpquotes.android.model.network.retrofit.quotes.trumpapi;

import android.content.res.Resources;

import com.davidcryer.trumpquotes.R;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitTrumpQuoteServiceFactoryImpl implements RetrofitTrumpQuoteServiceFactory {
    private final Resources resources;

    public RetrofitTrumpQuoteServiceFactoryImpl(Resources resources) {
        this.resources = resources;
    }

    @Override
    public RetrofitTrumpQuoteService create() {
        final Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(resources.getString(R.string.api_base_url_trump_quotes))
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return retrofit.create(RetrofitTrumpQuoteService.class);
    }
}
