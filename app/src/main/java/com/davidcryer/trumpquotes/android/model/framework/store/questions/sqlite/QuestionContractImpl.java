package com.davidcryer.trumpquotes.android.model.framework.store.questions.sqlite;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.provider.BaseColumns;

import com.davidcryer.trumpquotes.android.model.framework.store.helpers.sqlite.QueryHelper;
import com.davidcryer.trumpquotes.platformindependent.javahelpers.ArrayHelper;
import com.davidcryer.trumpquotes.platformindependent.model.framework.store.models.TrumpQuizQuestionStorageModel;

import static com.davidcryer.trumpquotes.android.model.framework.store.helpers.sqlite.SQLHelper.CLOSE_BRACKET;
import static com.davidcryer.trumpquotes.android.model.framework.store.helpers.sqlite.SQLHelper.COMMA;
import static com.davidcryer.trumpquotes.android.model.framework.store.helpers.sqlite.SQLHelper.CREATE_TABLE;
import static com.davidcryer.trumpquotes.android.model.framework.store.helpers.sqlite.SQLHelper.OPEN_BRACKET;
import static com.davidcryer.trumpquotes.android.model.framework.store.helpers.sqlite.SQLHelper.PRIMARY_KEY_TYPE;
import static com.davidcryer.trumpquotes.android.model.framework.store.helpers.sqlite.SQLHelper.SPACE;
import static com.davidcryer.trumpquotes.android.model.framework.store.helpers.sqlite.SQLHelper.TEXT_TYPE;

public class QuestionContractImpl implements QuestionContract {

    @Override
    public void createTable(final SQLiteDatabase database) {
        database.execSQL(Table.SQL_CREATE_ENTRIES);
    }

    @Override
    public boolean store(SQLiteDatabase database, TrumpQuizQuestionStorageModel... questions) {
        database.beginTransaction();
        try {
            for (final TrumpQuizQuestionStorageModel question : questions) {
                database.insert(Table.TABLE_NAME, null, insertContentValues(question));
            }
            database.setTransactionSuccessful();
            return true;
        } catch (SQLiteException sqle) {
            return false;
        } finally {
            database.endTransaction();
        }
    }

    private static ContentValues insertContentValues(final TrumpQuizQuestionStorageModel question) {
        final ContentValues values =  new ContentValues();
        values.put(Table._ID, question.id);
        values.put(Table.COLUMN_ITEM_QUOTE, question.quote);
        values.put(Table.COLUMN_ITEM_OPTION_A, question.optionA);
        values.put(Table.COLUMN_ITEM_OPTION_B, question.optionB);
        values.put(Table.COLUMN_ITEM_ANSWER_TYPE, question.answerType);
        return values;
    }

    @Override
    public boolean clearAll(SQLiteDatabase database) {
        database.beginTransaction();
        try {
            database.delete(Table.TABLE_NAME, null, null);
            database.setTransactionSuccessful();
            return true;
        } catch (SQLiteException sqle) {
            return false;
        } finally {
            database.endTransaction();
        }
    }

    @Override
    public TrumpQuizQuestionStorageModel[] questions(SQLiteDatabase database, int... ids) {
        return retrieveQuestions(database, ids);
    }

    private TrumpQuizQuestionStorageModel[] retrieveQuestions(final SQLiteDatabase database, final int... ids) {
        final Cursor cursor = database.query(
                Table.TABLE_NAME,
                null,
                QueryHelper.createSelectionIn(Table._ID, ids.length),
                QueryHelper.createSelectionArgs(ArrayHelper.toStringArray(ids)),
                null,
                null,
                null
        );
        final TrumpQuizQuestionStorageModel[] questions = new TrumpQuizQuestionStorageModel[cursor.getCount()];
        int i = 0;
        while (cursor.moveToNext()) {
            questions[i] = question(cursor);
            i++;
        }
        cursor.close();
        return questions;
    }

    private TrumpQuizQuestionStorageModel question(final Cursor cursor) {
        return new TrumpQuizQuestionStorageModel(
                Integer.parseInt(cursor.getString(cursor.getColumnIndex(Table._ID))),
                cursor.getString(cursor.getColumnIndex(Table.COLUMN_ITEM_QUOTE)),
                cursor.getString(cursor.getColumnIndex(Table.COLUMN_ITEM_OPTION_A)),
                cursor.getString(cursor.getColumnIndex(Table.COLUMN_ITEM_OPTION_B)),
                cursor.getString(cursor.getColumnIndex(Table.COLUMN_ITEM_ANSWER_TYPE))
        );
    }

    private static class Table implements BaseColumns {
        private final static String TABLE_NAME = "question";
        private final static String COLUMN_ITEM_QUOTE = "quizQuestion";
        private final static String COLUMN_ITEM_OPTION_A = "optionA";
        private final static String COLUMN_ITEM_OPTION_B = "optionB";
        private final static String COLUMN_ITEM_ANSWER_TYPE = "answerType";
        private static final String SQL_CREATE_ENTRIES =
                CREATE_TABLE + Table.TABLE_NAME + SPACE
                        + OPEN_BRACKET
                        + Table._ID + TEXT_TYPE + PRIMARY_KEY_TYPE + COMMA
                        + Table.COLUMN_ITEM_QUOTE + TEXT_TYPE + COMMA
                        + Table.COLUMN_ITEM_OPTION_A + TEXT_TYPE + COMMA
                        + Table.COLUMN_ITEM_OPTION_B + TEXT_TYPE + COMMA
                        + Table.COLUMN_ITEM_ANSWER_TYPE + TEXT_TYPE
                        + CLOSE_BRACKET;
    }
}
