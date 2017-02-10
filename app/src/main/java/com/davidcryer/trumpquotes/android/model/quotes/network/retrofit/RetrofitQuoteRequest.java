package com.davidcryer.trumpquotes.android.model.quotes.network.retrofit;

import com.davidcryer.trumpquotes.platformindependent.model.quotes.Quote;
import com.davidcryer.trumpquotes.platformindependent.model.quotes.network.QuoteRequest;
import com.davidcryer.trumpquotes.platformindependent.model.quotes.network.QuoteRequestCallback;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RetrofitQuoteRequest implements QuoteRequest {
    private final Call<Quote> call;
    private final List<QuoteRequestCallback> callbacks;

    public RetrofitQuoteRequest(Call<Quote> call, final List<QuoteRequestCallback> callbacks) {
        this.call = call;
        call.enqueue(new Callback<Quote>() {
            @Override
            public void onResponse(Call<Quote> call, Response<Quote> response) {
                for (final QuoteRequestCallback callback : callbacks) {
                    callback.success(response.body());
                }
            }

            @Override
            public void onFailure(Call<Quote> call, Throwable t) {
                for (final QuoteRequestCallback callback : callbacks) {
                    callback.failure();
                }
            }
        });
        this.callbacks = callbacks;
    }

    @Override
    public void cancel() {
        call.cancel();
    }

    @Override
    public boolean isCancelled() {
        return call.isCanceled();
    }

    @Override
    public void remove(QuoteRequestCallback requestCallback) {
        callbacks.remove(requestCallback);
        if (callbacks.isEmpty()) {
            cancel();
        }
    }

    public void execute();
}
