package com.davidcryer.trumpquotes.android.model.framework.network.retrofit.quotes;

import com.davidcryer.trumpquotes.platformindependent.model.framework.network.Request;
import com.davidcryer.trumpquotes.platformindependent.model.framework.network.RequestCallback;
import com.davidcryer.trumpquotes.platformindependent.model.framework.network.quotes.Quote;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RetrofitQuoteRequest<QuoteType extends Quote> implements Request {
    private final Call<QuoteType> call;
    private final RequestCallback<Quote> callback;

    public RetrofitQuoteRequest(Call<QuoteType> call, RequestCallback<Quote> callback) {
        this.call = call;
        this.callback = callback;
    }

    @Override
    public void enqueue() {
        call.enqueue(new Callback<QuoteType>() {
            @Override
            public void onResponse(Call<QuoteType> call, Response<QuoteType> response) {
                if (callback != null) {
                    callback.success(response.body());
                }
            }

            @Override
            public void onFailure(Call<QuoteType> call, Throwable t) {
                if (callback != null) {
                    callback.failure();
                }
            }
        });
    }

    @Override
    public boolean isExecuted() {
        return call.isExecuted();
    }

    @Override
    public void cancel() {
        call.cancel();
    }

    @Override
    public boolean isCancelled() {
        return call.isCanceled();
    }
}
