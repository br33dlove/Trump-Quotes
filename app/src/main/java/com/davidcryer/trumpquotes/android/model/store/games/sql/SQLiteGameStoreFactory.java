package com.davidcryer.trumpquotes.android.model.store.games.sql;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.davidcryer.trumpquotes.platformindependent.model.store.stores.TrumpQuizGameStore;
import com.davidcryer.trumpquotes.platformindependent.model.store.stores.TrumpQuizGameStoreFactory;

public class SQLiteGameStoreFactory implements TrumpQuizGameStoreFactory {
    private final static int DATABASE_VERSION = 1;
    private final static String DATABASE_NAME = "games.sqlite";
    private final Context context;
    private final SQLiteDatabase.CursorFactory cursorFactory;
    private final GameContract quoteContract;

    public SQLiteGameStoreFactory(Context context, SQLiteDatabase.CursorFactory cursorFactory, GameContract quoteContract) {
        this.context = context;
        this.cursorFactory = cursorFactory;
        this.quoteContract = quoteContract;
    }

    @Override
    public TrumpQuizGameStore create() {
        return new SQLiteGameStore(context, DATABASE_NAME, cursorFactory, DATABASE_VERSION, quoteContract);
    }
}
