package com.davidcryer.trumpquotes.android.model.repository.quotes;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.davidcryer.trumpquotes.platformindependent.model.repository.quotes.QuoteRepository;
import com.davidcryer.trumpquotes.platformindependent.model.repository.quotes.QuoteRepositoryFactory;

public class SQLiteQuoteRepositoryFactory implements QuoteRepositoryFactory {
    private final static int DATABASE_VERSION = 1;
    private final static String DATABASE_NAME = "QuoteRepository.sqlite";
    private final Context context;
    private final SQLiteDatabase.CursorFactory cursorFactory;
    private final QuoteContract quoteContract;

    public SQLiteQuoteRepositoryFactory(Context context, SQLiteDatabase.CursorFactory cursorFactory, QuoteContract quoteContract) {
        this.context = context;
        this.cursorFactory = cursorFactory;
        this.quoteContract = quoteContract;
    }

    @Override
    public QuoteRepository create() {
        return new SQLiteQuoteRepository(context, DATABASE_NAME, cursorFactory, DATABASE_VERSION, quoteContract);
    }
}
