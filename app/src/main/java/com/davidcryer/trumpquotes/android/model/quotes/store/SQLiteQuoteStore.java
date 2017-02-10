package com.davidcryer.trumpquotes.android.model.quotes.store;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.davidcryer.trumpquotes.platformindependent.model.quotes.Quote;
import com.davidcryer.trumpquotes.platformindependent.model.quotes.store.QuoteStore;

class SQLiteQuoteStore extends SQLiteOpenHelper implements QuoteStore {//TODO have an sqliteopenhelper instead, and use this class to manage handlers and callbacks

    SQLiteQuoteStore(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //TODO createQuotesPresenterFactory database
        db.enableWriteAheadLogging();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    @Override
    public void store(Quote... quotes) {
        final SQLiteDatabase db = getWritableDatabase();
        db.beginTransaction();
        try {
//            db.upsert()//TODO try update, else insert
            db.setTransactionSuccessful();
        } finally {
            db.endTransaction();
        }
    }

    @Override
    public void clear(String... quoteIds) {
        final SQLiteDatabase db = getWritableDatabase();
        db.beginTransaction();
        try {
//            db.delete()//TODO
            db.setTransactionSuccessful();
        } finally {
            db.endTransaction();
        }
    }

    @Override
    public Quote[] judgedQuotes() {
        return new Quote[0];//TODO
    }

    @Override
    public Quote[] unJudgedQuotes() {
        return new Quote[0];//TODO
    }

    @Override
    public void updateQuoteAsJudged(String quoteId) {
        //TODO
    }
}
