package com.davidcryer.trumpquotes.android.model.store.questions;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.davidcryer.trumpquotes.platformindependent.model.store.stores.TrumpQuizQuestionStore;
import com.davidcryer.trumpquotes.platformindependent.model.store.stores.TrumpQuizQuestionStoreFactory;

public class SQLiteQuestionStoreFactory implements TrumpQuizQuestionStoreFactory {
    private final static int DATABASE_VERSION = 1;
    private final static String DATABASE_NAME = "game.sqlite";
    private final Context context;
    private final SQLiteDatabase.CursorFactory cursorFactory;
    private final QuestionContract quoteContract;

    public SQLiteQuestionStoreFactory(Context context, SQLiteDatabase.CursorFactory cursorFactory, QuestionContract quoteContract) {
        this.context = context;
        this.cursorFactory = cursorFactory;
        this.quoteContract = quoteContract;
    }

    @Override
    public TrumpQuizQuestionStore create() {
        return new SQLiteQuestionStore(context, DATABASE_NAME, cursorFactory, DATABASE_VERSION, quoteContract);
    }
}
