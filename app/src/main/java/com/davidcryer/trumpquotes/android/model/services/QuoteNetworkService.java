package com.davidcryer.trumpquotes.android.model.services;

import com.davidcryer.trumpquotes.android.model.framework.store.Cancelable;
import com.davidcryer.trumpquotes.android.model.framework.network.quotes.Quote;

public interface QuoteNetworkService {
    Cancelable randomQuote(final Callback callback);

    interface Callback {
        void onSuccess(final Quote quote);
        void onError();
    }
}
