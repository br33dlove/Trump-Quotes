package com.davidcryer.trumpquotes.platformindependent.model.services;

import com.davidcryer.trumpquotes.platformindependent.model.framework.Cancelable;
import com.davidcryer.trumpquotes.platformindependent.model.framework.network.quotes.Quote;

public interface QuoteNetworkService {
    Cancelable randomQuote(final Callback callback);

    interface Callback {
        void onSuccess(final Quote quote);
        void onError();
    }
}
