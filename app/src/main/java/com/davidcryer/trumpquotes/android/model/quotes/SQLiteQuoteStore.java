package com.davidcryer.trumpquotes.android.model.quotes;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.davidcryer.trumpquotes.platformindependent.model.quotes.Quote;
import com.davidcryer.trumpquotes.platformindependent.model.quotes.QuoteStore;

class SQLiteQuoteStore extends SQLiteOpenHelper implements QuoteStore {

    SQLiteQuoteStore(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //TODO create database
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
    public Quote[] retrieve() {
        return new Quote[0];//TODO
    }
}
