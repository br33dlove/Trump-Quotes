package com.davidcryer.trumpquotes.platformindependent.model.store.quotes;

import com.davidcryer.trumpquotes.platformindependent.model.quotes.Quote;

public interface QuoteRepository {
    boolean store(final Quote... quotes);
    boolean clear(final String... quoteIds);
    Quote[] judgedQuotes();
    Quote[] unJudgedQuotes();
    boolean updateQuoteAsJudged(final String quoteId);
}
