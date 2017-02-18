package com.davidcryer.trumpquotes.android.model.quotes.network.retrofit;

import com.davidcryer.trumpquotes.platformindependent.model.quotes.network.trumpapi.TrumpQuote;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface RetrofitQuoteService {
    @GET("v1/quotes/random")
    Call<TrumpQuote> randomQuote();

    @GET("v1/quotes/personalized?q={name}")
    Call<TrumpQuote> personalisedQuote(@Path("name") String name);
}
