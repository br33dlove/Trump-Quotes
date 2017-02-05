package com.davidcryer.trumpquotes.platformindependent.model.quotes;

public interface QuoteResponseHandler {

    interface Callback {
        void success(final Quote quote);
        void failure();
    }
}
