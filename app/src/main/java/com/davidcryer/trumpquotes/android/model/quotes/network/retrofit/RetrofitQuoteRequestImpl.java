package com.davidcryer.trumpquotes.android.model.quotes.network.retrofit;

import com.davidcryer.trumpquotes.platformindependent.model.quotes.Quote;
import com.davidcryer.trumpquotes.platformindependent.model.quotes.network.QuoteRequest;
import com.davidcryer.trumpquotes.platformindependent.model.quotes.network.QuoteRequestCallback;

import java.util.Collections;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

class RetrofitQuoteRequestImpl implements QuoteRequest {
    private final Call<Quote> call;
    private final List<QuoteRequestCallback> callbacks;

    RetrofitQuoteRequestImpl(Call<Quote> call, final List<QuoteRequestCallback> callbacks) {
        this.call = call;
        this.callbacks = callbacks;
    }

    @Override
    public void executeAsync() {
        call.enqueue(new Callback<Quote>() {
            @Override
            public void onResponse(Call<Quote> call, Response<Quote> response) {
                for (final QuoteRequestCallback callback : callbacks) {
                    if (callback != null) {
                        callback.success(response.body());
                    }
                }
            }

            @Override
            public void onFailure(Call<Quote> call, Throwable t) {
                for (final QuoteRequestCallback callback : callbacks) {
                    if (callback != null) {
                        callback.failure();
                    }
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
        callbacks.clear();
    }

    @Override
    public boolean isCancelled() {
        return call.isCanceled();
    }
}
