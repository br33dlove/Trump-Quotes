package com.davidcryer.trumpquotes.platformindependent.model.store.quotes;

import com.davidcryer.trumpquotes.platformindependent.model.quotes.Quote;

import java.util.List;

public interface QuoteRepositoryHandler {
    void store(final Quote... quotes);
    void clear(final String... quoteIds);
    void retrieveJudgedQuotes(final RetrieveCallback callback);
    void retrieveUnJudgedQuotes(final RetrieveCallback callback);
    void updateQuoteAsJudged(final String quoteId);

    interface RetrieveCallback {
        void onReturn(final List<Quote> quotes);
    }
}
