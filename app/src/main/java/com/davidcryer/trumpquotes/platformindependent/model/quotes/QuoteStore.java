package com.davidcryer.trumpquotes.platformindependent.model.quotes;

import java.util.List;

public interface QuoteStore {
    void store(final Quote... quotes);
    void clear(final String... quoteIds);
    void retrieveUnJudgedQuotes(final RetrieveCallback callback);
    void retrieveJudgedQuotes(final RetrieveCallback callback);

    interface RetrieveCallback {
        void onReturn(final List<Quote> quotes);
    }
}
