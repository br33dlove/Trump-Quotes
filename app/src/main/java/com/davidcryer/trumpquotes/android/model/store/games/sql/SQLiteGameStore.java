package com.davidcryer.trumpquotes.android.model.store.games.sql;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.davidcryer.trumpquotes.platformindependent.model.store.models.TrumpQuizGameStorageModel;
import com.davidcryer.trumpquotes.platformindependent.model.store.stores.TrumpQuizGameStore;

class SQLiteGameStore extends SQLiteOpenHelper implements TrumpQuizGameStore {
    private final GameContract gameContract;

    SQLiteGameStore(Context context, String name, SQLiteDatabase.CursorFactory factory, int version, final GameContract gameContract) {
        super(context, name, factory, version);
        this.gameContract = gameContract;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        gameContract.createTable(db);
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
    public boolean store(TrumpQuizGameStorageModel model) {
        return gameContract.store(getWritableDatabase(), model);
    }

    @Override
    public boolean clear() {
        return gameContract.clear(getWritableDatabase());
    }

    @Override
    public TrumpQuizGameStorageModel retrieve() {
        return gameContract.game(getReadableDatabase());
    }
}
