package com.davidcryer.trumpquotes.android.model.store.quotes;

import android.database.sqlite.SQLiteDatabase;

import com.davidcryer.trumpquotes.platformindependent.model.quotes.Quote;

public interface QuoteContract {
    void createTable(final SQLiteDatabase database);
    boolean store(final SQLiteDatabase database, final Quote... quotes);
    boolean clear(final SQLiteDatabase database, final String... quoteIds);
    Quote[] judgedQuotes(final SQLiteDatabase database);
    Quote[] unJudgedQuotes(final SQLiteDatabase database);
    boolean updateQuoteAsJudged(final SQLiteDatabase database, final String quoteId);
}
