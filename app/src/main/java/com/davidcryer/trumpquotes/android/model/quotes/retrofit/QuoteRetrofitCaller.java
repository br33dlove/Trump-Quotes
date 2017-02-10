package com.davidcryer.trumpquotes.android.model.quotes.retrofit;

import com.davidcryer.trumpquotes.platformindependent.model.quotes.Quote;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface QuoteRetrofitCaller {
    @GET("v1/quotes/random")
    Call<Quote> randomQuote();

    @GET("v1/quotes/personalized?q={name}")
    Call<Quote> personalisedQuote(@Path("name") String name);
}
