package com.davidcryer.trumpquotes.platformindependent.model.quotes;

public interface QuoteStore {
    void store(final Quote... quotes);
    void clear(final String... quoteIds);
    Quote[] judgedQuotes();
    Quote[] unJudgedQuotes();
    void updateQuoteAsJudged(final String quoteId);
}
