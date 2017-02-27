package com.davidcryer.trumpquotes.android.model.network.retrofit.quotes;

import com.davidcryer.trumpquotes.android.model.network.retrofit.quotes.trumpapi.RetrofitTrumpQuoteService;

public interface RetrofitQuoteServiceFactory {
    RetrofitTrumpQuoteService create();
}
