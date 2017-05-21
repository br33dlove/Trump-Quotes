package com.davidcryer.trumpquotes.android.model.framework.store.games.sqlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.davidcryer.trumpquotes.android.model.framework.store.stores.TrumpQuizGameStore;
import com.davidcryer.trumpquotes.android.model.framework.store.stores.TrumpQuizGameStoreFactory;

public class SQLiteGameStoreFactory implements TrumpQuizGameStoreFactory {
    private final static int DATABASE_VERSION = 1;
    private final static String DATABASE_NAME = "game.sqlite";
    private final Context context;
    private final SQLiteDatabase.CursorFactory cursorFactory;
    private final GameContract gameContract;

    public SQLiteGameStoreFactory(Context context, SQLiteDatabase.CursorFactory cursorFactory, GameContract gameContract) {
        this.context = context;
        this.cursorFactory = cursorFactory;
        this.gameContract = gameContract;
    }

    @Override
    public TrumpQuizGameStore create() {
        return new SQLiteGameStore(context, DATABASE_NAME, cursorFactory, DATABASE_VERSION, gameContract);
    }
}
