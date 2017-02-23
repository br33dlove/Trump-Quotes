package com.davidcryer.trumpquotes.android.model.repository.quotes;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.davidcryer.trumpquotes.platformindependent.model.quotes.Quote;
import com.davidcryer.trumpquotes.platformindependent.model.repository.quotes.QuoteRepository;

class SQLiteQuoteRepository extends SQLiteOpenHelper implements QuoteRepository {
    private final QuoteContract quoteContract;

    SQLiteQuoteRepository(Context context, String name, SQLiteDatabase.CursorFactory factory, int version, final QuoteContract quoteContract) {
        super(context, name, factory, version);
        this.quoteContract = quoteContract;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        quoteContract.createTable(db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    @Override
    public void onConfigure(SQLiteDatabase db) {
        super.onConfigure(db);
        db.enableWriteAheadLogging();
    }

    @Override
    public boolean store(Quote... quotes) {
        return quoteContract.store(getWritableDatabase(), quotes);
    }

    @Override
    public boolean clear(String... quoteIds) {
        return quoteContract.clear(getWritableDatabase(), quoteIds);
    }

    @Override
    public Quote[] judgedQuotes() {
        return quoteContract.judgedQuotes(getReadableDatabase());
    }

    @Override
    public Quote[] unJudgedQuotes() {
        return quoteContract.unJudgedQuotes(getReadableDatabase());
    }

    @Override
    public boolean updateQuoteAsJudged(String quoteId) {
        return quoteContract.updateQuoteAsJudged(getWritableDatabase(), quoteId);
    }
}
