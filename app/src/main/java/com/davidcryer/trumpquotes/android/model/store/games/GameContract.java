package com.davidcryer.trumpquotes.android.model.store.games;

import android.database.sqlite.SQLiteDatabase;

import com.davidcryer.trumpquotes.platformindependent.model.store.models.TrumpQuizGameStorageModel;

public interface GameContract {
    void createTable(final SQLiteDatabase database);
    boolean store(final SQLiteDatabase database, final TrumpQuizGameStorageModel game);
    boolean clear(final SQLiteDatabase database);
    TrumpQuizGameStorageModel game(final SQLiteDatabase database);
}
