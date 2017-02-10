package com.davidcryer.trumpquotes.platformindependent.model.quotes;

import java.util.List;

public interface QuoteStoreHandler {
    void store(final Quote... quotes);
    void clear(final String... quoteIds);
    void retrieveJudgedQuotes(final RetrieveCallback callback);
    void retrieveUnJudgedQuotes(final RetrieveCallback callback);
    void updateQuoteAsJudged(final String quoteId);

    interface RetrieveCallback {
        void onReturn(final List<Quote> quotes);
    }
}
