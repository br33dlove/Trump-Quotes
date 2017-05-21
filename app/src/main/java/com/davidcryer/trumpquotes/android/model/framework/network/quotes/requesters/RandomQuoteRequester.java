package com.davidcryer.trumpquotes.android.model.framework.network.quotes.requesters;

import com.davidcryer.trumpquotes.android.model.framework.store.Cancelable;
import com.davidcryer.trumpquotes.android.model.framework.network.RequestCallback;
import com.davidcryer.trumpquotes.android.model.framework.network.quotes.Quote;

public interface RandomQuoteRequester {
    Cancelable randomQuoteRequest(final RequestCallback<Quote> callback);
}
