package com.davidcryer.trumpquotes.android.model.framework.store.questions.sqlite;

import android.database.sqlite.SQLiteDatabase;

import com.davidcryer.trumpquotes.android.model.framework.store.models.TrumpQuizQuestionStorageModel;

public interface QuestionContract {
    void createTable(final SQLiteDatabase database);
    boolean store(final SQLiteDatabase database, final TrumpQuizQuestionStorageModel... questions);
    boolean clearAll(final SQLiteDatabase database);
    TrumpQuizQuestionStorageModel[] questions(final SQLiteDatabase database, final int... ids);
}
