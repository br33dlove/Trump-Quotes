package com.davidcryer.trumpquotes.android.model.network.retrofit.quotes;

import com.davidcryer.trumpquotes.platformindependent.model.framework.network.Request;
import com.davidcryer.trumpquotes.platformindependent.model.network.quotes.Quote;
import com.davidcryer.trumpquotes.platformindependent.model.network.quotes.QuoteRequestCallback;

import java.lang.ref.WeakReference;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RetrofitQuoteRequest<QuoteType extends Quote> implements Request {
    private final Call<QuoteType> call;
    private final WeakReference<QuoteRequestCallback> callback;

    public RetrofitQuoteRequest(Call<QuoteType> call, WeakReference<QuoteRequestCallback> callback) {
        this.call = call;
        this.callback = callback;
    }

    @Override
    public void enqueue() {
        call.enqueue(new Callback<QuoteType>() {
            @Override
            public void onResponse(Call<QuoteType> call, Response<QuoteType> response) {
                if (callback.get() != null) {
                    callback.get().success(response.body());
                }
            }

            @Override
            public void onFailure(Call<QuoteType> call, Throwable t) {
                if (callback.get() != null) {
                    callback.get().failure();
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
        callback.clear();
    }

    @Override
    public boolean isCancelled() {
        return call.isCanceled();
    }
}
