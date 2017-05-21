package com.davidcryer.trumpquotes.android.model.framework.network.retrofit.quotes.trumpapi;

import com.davidcryer.trumpquotes.android.model.framework.network.quotes.trumpapi.TrumpQuote;

import retrofit2.Call;
import retrofit2.http.GET;

public interface RetrofitTrumpQuoteService {
    @GET("v1/quotes/random")
    Call<TrumpQuote> randomQuote();
}
