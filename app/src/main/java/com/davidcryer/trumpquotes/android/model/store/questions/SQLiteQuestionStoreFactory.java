package com.davidcryer.trumpquotes.android.model.store.questions;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.davidcryer.trumpquotes.platformindependent.model.framework.store.stores.TrumpQuizQuestionStore;
import com.davidcryer.trumpquotes.platformindependent.model.framework.store.stores.TrumpQuizQuestionStoreFactory;

public class SQLiteQuestionStoreFactory implements TrumpQuizQuestionStoreFactory {
    private final static int DATABASE_VERSION = 1;
    private final static String DATABASE_NAME = "question.sqlite";
    private final Context context;
    private final SQLiteDatabase.CursorFactory cursorFactory;
    private final QuestionContract questionContract;

    public SQLiteQuestionStoreFactory(Context context, SQLiteDatabase.CursorFactory cursorFactory, QuestionContract questionContract) {
        this.context = context;
        this.cursorFactory = cursorFactory;
        this.questionContract = questionContract;
    }

    @Override
    public TrumpQuizQuestionStore create() {
        return new SQLiteQuestionStore(context, DATABASE_NAME, cursorFactory, DATABASE_VERSION, questionContract);
    }
}
