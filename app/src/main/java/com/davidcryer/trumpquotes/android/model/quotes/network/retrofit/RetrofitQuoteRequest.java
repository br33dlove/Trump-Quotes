package com.davidcryer.trumpquotes.android.model.quotes.network.retrofit;

import com.davidcryer.trumpquotes.platformindependent.model.quotes.network.QuoteRequest;
import com.davidcryer.trumpquotes.platformindependent.model.quotes.network.QuoteRequestCallback;
import com.davidcryer.trumpquotes.platformindependent.model.quotes.network.trumpapi.TrumpQuote;
import com.davidcryer.trumpquotes.platformindependent.model.quotes.network.trumpapi.TrumpQuoteToQuoteAdapter;

import java.util.Collection;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

class RetrofitQuoteRequest implements QuoteRequest {
    private final TrumpQuoteToQuoteAdapter trumpQuoteToQuoteAdapter;
    private final Call<TrumpQuote> call;
    private final Collection<QuoteRequestCallback> callbacks;

    public RetrofitQuoteRequest(TrumpQuoteToQuoteAdapter trumpQuoteToQuoteAdapter, Call<TrumpQuote> call, Collection<QuoteRequestCallback> callbacks) {
        this.trumpQuoteToQuoteAdapter = trumpQuoteToQuoteAdapter;
        this.call = call;
        this.callbacks = callbacks;
    }

    @Override
    public void executeAsync() {
        call.enqueue(new Callback<TrumpQuote>() {
            @Override
            public void onResponse(Call<TrumpQuote> call, Response<TrumpQuote> response) {
                for (final QuoteRequestCallback callback : callbacks) {
                    if (callback != null) {
                        callback.success(trumpQuoteToQuoteAdapter.quote(response.body()));
                    }
                }
            }

            @Override
            public void onFailure(Call<TrumpQuote> call, Throwable t) {
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
