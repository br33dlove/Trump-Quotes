package com.davidcryer.trumpquotes.android.model.quotes;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.davidcryer.trumpquotes.platformindependent.model.quotes.QuoteStore;
import com.davidcryer.trumpquotes.platformindependent.model.quotes.factories.QuoteStoreFactory;

public class SQLiteQuoteStoreFactory implements QuoteStoreFactory {
    private final static int DATABASE_VERSION = 1;
    private final static String DATABASE_NAME = "QuoteStore.sqlite";
    private final Context context;
    private final SQLiteDatabase.CursorFactory cursorFactory;

    public SQLiteQuoteStoreFactory(Context context, SQLiteDatabase.CursorFactory cursorFactory) {
        this.context = context;
        this.cursorFactory = cursorFactory;
    }

    @Override
    public QuoteStore create() {
        return new SQLiteQuoteStore(context, DATABASE_NAME, cursorFactory, DATABASE_VERSION);
    }
}
