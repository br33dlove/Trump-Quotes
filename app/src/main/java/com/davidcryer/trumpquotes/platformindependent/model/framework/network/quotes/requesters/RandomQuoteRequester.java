package com.davidcryer.trumpquotes.platformindependent.model.framework.network.quotes.requesters;

import com.davidcryer.trumpquotes.platformindependent.model.framework.Cancelable;
import com.davidcryer.trumpquotes.platformindependent.model.framework.network.RequestCallback;
import com.davidcryer.trumpquotes.platformindependent.model.framework.network.quotes.Quote;

public interface RandomQuoteRequester {
    Cancelable randomQuoteRequest(final RequestCallback<Quote> callback);
}
