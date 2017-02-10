package com.davidcryer.trumpquotes.platformindependent.model.quotes.network;

import com.davidcryer.trumpquotes.platformindependent.model.quotes.Quote;

public interface QuoteRequestCallback {
    void success(final Quote quote);
    void failure();
}
