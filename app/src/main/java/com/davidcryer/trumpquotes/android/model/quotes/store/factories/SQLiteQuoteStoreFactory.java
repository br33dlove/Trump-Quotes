package com.davidcryer.trumpquotes.android.model.quotes.store.factories;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.davidcryer.trumpquotes.android.model.quotes.store.QuoteContract;
import com.davidcryer.trumpquotes.android.model.quotes.store.SQLiteQuoteStore;
import com.davidcryer.trumpquotes.platformindependent.model.quotes.store.QuoteStore;
import com.davidcryer.trumpquotes.platformindependent.model.quotes.store.factories.QuoteStoreFactory;

public class SQLiteQuoteStoreFactory implements QuoteStoreFactory {
    private final static int DATABASE_VERSION = 1;
    private final static String DATABASE_NAME = "QuoteStore.sqlite";
    private final Context context;
    private final SQLiteDatabase.CursorFactory cursorFactory;
    private final QuoteContract quoteContract;

    public SQLiteQuoteStoreFactory(Context context, SQLiteDatabase.CursorFactory cursorFactory, QuoteContract quoteContract) {
        this.context = context;
        this.cursorFactory = cursorFactory;
        this.quoteContract = quoteContract;
    }

    @Override
    public QuoteStore create() {
        return new SQLiteQuoteStore(context, DATABASE_NAME, cursorFactory, DATABASE_VERSION, quoteContract);
    }
}
