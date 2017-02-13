package com.davidcryer.trumpquotes.android.model.quotes.network.retrofit;

import retrofit2.Response;

public interface RetrofitRequest<ReturnType> {
    Response<ReturnType> response();
}
