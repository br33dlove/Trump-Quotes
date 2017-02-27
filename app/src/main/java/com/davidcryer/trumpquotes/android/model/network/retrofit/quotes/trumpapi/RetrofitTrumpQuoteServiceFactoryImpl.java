package com.davidcryer.trumpquotes.android.model.network.retrofit.quotes.trumpapi;

import android.content.res.Resources;

import com.davidcryer.trumpquotes.R;
import com.davidcryer.trumpquotes.android.model.network.retrofit.quotes.RetrofitQuoteServiceFactory;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitTrumpQuoteServiceFactoryImpl implements RetrofitQuoteServiceFactory {
    private final Resources resources;

    public RetrofitTrumpQuoteServiceFactoryImpl(Resources resources) {
        this.resources = resources;
    }

    @Override
    public RetrofitTrumpQuoteService create() {
        final Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(resources.getString(R.string.quote_base_url_trump_quotes_api))
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return retrofit.create(RetrofitTrumpQuoteService.class);
    }
}
