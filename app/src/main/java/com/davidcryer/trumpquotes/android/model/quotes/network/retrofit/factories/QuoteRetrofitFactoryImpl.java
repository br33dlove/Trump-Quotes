package com.davidcryer.trumpquotes.android.model.quotes.network.retrofit.factories;

import android.content.res.Resources;

import com.davidcryer.trumpquotes.R;

import retrofit2.Retrofit;

public class QuoteRetrofitFactoryImpl implements QuoteRetrofitFactory {
    private final Resources resources;

    public QuoteRetrofitFactoryImpl(Resources resources) {
        this.resources = resources;
    }

    @Override
    public Retrofit create() {
        return new Retrofit.Builder().baseUrl(resources.getString(R.string.quotes_base_url)).build();
    }
}
