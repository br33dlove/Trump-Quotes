package com.davidcryer.trumpquotes.platformindependent.model.quotes.store;

import com.davidcryer.trumpquotes.platformindependent.model.quotes.Quote;

public interface QuoteStore {
    void store(final Quote... quotes);
    void clear(final String... quoteIds);
    Quote[] judgedQuotes();
    Quote[] unJudgedQuotes();
    void updateQuoteAsJudged(final String quoteId);
}
