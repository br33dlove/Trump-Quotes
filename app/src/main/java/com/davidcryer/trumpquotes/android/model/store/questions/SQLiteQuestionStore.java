package com.davidcryer.trumpquotes.android.model.store.questions;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.davidcryer.trumpquotes.platformindependent.model.store.models.TrumpQuizQuestionStorageModel;
import com.davidcryer.trumpquotes.platformindependent.model.store.stores.TrumpQuizQuestionStore;

class SQLiteQuestionStore extends SQLiteOpenHelper implements TrumpQuizQuestionStore {
    private final QuestionContract questionContract;

    SQLiteQuestionStore(Context context, String name, SQLiteDatabase.CursorFactory factory, int version, final QuestionContract questionContract) {
        super(context, name, factory, version);
        this.questionContract = questionContract;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        questionContract.createTable(db);
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
    public boolean store(TrumpQuizQuestionStorageModel[] models) {
        return questionContract.store(getWritableDatabase(), models);
    }

    @Override
    public boolean clearAll() {
        return questionContract.clearAll(getWritableDatabase());
    }

    @Override
    public TrumpQuizQuestionStorageModel[] retrieve(int... ids) {
        return questionContract.questions(getReadableDatabase(), ids);
    }
}
