package com.davidcryer.trumpquotes.android.model.framework.network.quotes;

import com.davidcryer.trumpquotes.android.model.framework.network.Request;
import com.davidcryer.trumpquotes.android.model.framework.network.RequestCallback;

public interface QuoteRequestFactory {
    Request randomQuoteRequest(final RequestCallback<Quote> requestCallback);
}
