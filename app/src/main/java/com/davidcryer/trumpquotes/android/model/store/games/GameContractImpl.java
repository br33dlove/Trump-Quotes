package com.davidcryer.trumpquotes.android.model.store.games;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.provider.BaseColumns;

import com.davidcryer.trumpquotes.android.model.helpers.store.QueryHelper;
import com.davidcryer.trumpquotes.platformindependent.javahelpers.ArrayHelper;
import com.davidcryer.trumpquotes.platformindependent.javahelpers.StringHelper;
import com.davidcryer.trumpquotes.platformindependent.model.store.models.TrumpQuizGameStorageModel;

import static com.davidcryer.trumpquotes.android.model.helpers.store.SQLHelper.CLOSE_BRACKET;
import static com.davidcryer.trumpquotes.android.model.helpers.store.SQLHelper.COMMA;
import static com.davidcryer.trumpquotes.android.model.helpers.store.SQLHelper.CREATE_TABLE;
import static com.davidcryer.trumpquotes.android.model.helpers.store.SQLHelper.DELIMITER_COMMA;
import static com.davidcryer.trumpquotes.android.model.helpers.store.SQLHelper.INTEGER_TYPE;
import static com.davidcryer.trumpquotes.android.model.helpers.store.SQLHelper.INT_VALUE_FALSE;
import static com.davidcryer.trumpquotes.android.model.helpers.store.SQLHelper.INT_VALUE_TRUE;
import static com.davidcryer.trumpquotes.android.model.helpers.store.SQLHelper.OPEN_BRACKET;
import static com.davidcryer.trumpquotes.android.model.helpers.store.SQLHelper.PRIMARY_KEY_TYPE;
import static com.davidcryer.trumpquotes.android.model.helpers.store.SQLHelper.SPACE;
import static com.davidcryer.trumpquotes.android.model.helpers.store.SQLHelper.TEXT_TYPE;

public class GameContractImpl implements GameContract {

    @Override
    public void createTable(final SQLiteDatabase database) {
        database.execSQL(Table.SQL_CREATE_ENTRIES);
    }

    @Override
    public boolean store(SQLiteDatabase database, TrumpQuizGameStorageModel game) {
        database.beginTransaction();
        try {
            database.insert(Table.TABLE_NAME, null, insertContentValues(game));
            database.setTransactionSuccessful();
            return true;
        } catch (SQLiteException sqle) {
            return false;
        } finally {
            database.endTransaction();
        }
    }

    private static ContentValues insertContentValues(final TrumpQuizGameStorageModel game) {
        final ContentValues values =  new ContentValues();
        values.put(Table.COLUMN_ITEM_QUESTION_IDS, ArrayHelper.toString(game.questionIds, DELIMITER_COMMA));
        values.put(Table.COLUMN_ITEM_QUESTIONS_ANSWERED, game.questionsAnswered);
        values.put(Table.COLUMN_ITEM_CORRECT_ANSWERS, game.correctAnswers);
        values.put(Table.COLUMN_ITEM_IS_FINISHED, game.isFinished ? INT_VALUE_TRUE : INT_VALUE_FALSE);
        values.put(Table.COLUMN_ITEM_CURRENT_QUESTION_INDEX, game.currentQuestionIndex);
        values.put(Table.COLUMN_ITEM_IS_FINISHED, game.isCurrentQuestionAnswered ? INT_VALUE_TRUE : INT_VALUE_FALSE);
        return values;
    }

    @Override
    public boolean clear(SQLiteDatabase database) {
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
    public TrumpQuizGameStorageModel game(SQLiteDatabase database) {
        return retrieveGame(database);
    }

    private TrumpQuizGameStorageModel retrieveGame(final SQLiteDatabase database) {
        final Cursor cursor = database.query(
                Table.TABLE_NAME,
                null,
                null,
                null,
                null,
                null,
                null
        );
        TrumpQuizGameStorageModel game = null;
        if (cursor.moveToFirst()) {
            game = game(cursor);
        }
        cursor.close();
        return game;
    }

    private TrumpQuizGameStorageModel game(final Cursor cursor) {
        return new TrumpQuizGameStorageModel(
                StringHelper.splitDelimitedInts(cursor.getString(cursor.getColumnIndex(Table.COLUMN_ITEM_QUESTION_IDS)), DELIMITER_COMMA),
                cursor.getInt(cursor.getColumnIndex(Table.COLUMN_ITEM_QUESTIONS_ANSWERED)),
                cursor.getInt(cursor.getColumnIndex(Table.COLUMN_ITEM_CORRECT_ANSWERS)),
                cursor.getInt(cursor.getColumnIndex(Table.COLUMN_ITEM_IS_FINISHED)) == INT_VALUE_TRUE,
                cursor.getInt(cursor.getColumnIndex(Table.COLUMN_ITEM_CURRENT_QUESTION_INDEX)),
                cursor.getInt(cursor.getColumnIndex(Table.COLUMN_ITEM_IS_CURRENT_QUESTION_ANSWERED)) == INT_VALUE_TRUE
        );
    }

    private static class Table implements BaseColumns {
        private final static String TABLE_NAME = "game";
        private final static String COLUMN_ITEM_QUESTION_IDS = "questionIds";
        private final static String COLUMN_ITEM_QUESTIONS_ANSWERED = "questionsAnswered";
        private final static String COLUMN_ITEM_CORRECT_ANSWERS = "correctAnswers";
        private final static String COLUMN_ITEM_IS_FINISHED = "isFinished";
        private final static String COLUMN_ITEM_CURRENT_QUESTION_INDEX = "currentQuestionIndex";
        private final static String COLUMN_ITEM_IS_CURRENT_QUESTION_ANSWERED = "isCurrentQuestionAnswered";
        private static final String SQL_CREATE_ENTRIES =
                CREATE_TABLE + Table.TABLE_NAME + SPACE
                        + OPEN_BRACKET
                        + Table._ID + TEXT_TYPE + PRIMARY_KEY_TYPE + COMMA
                        + Table.COLUMN_ITEM_QUESTION_IDS + TEXT_TYPE + COMMA
                        + Table.COLUMN_ITEM_QUESTIONS_ANSWERED + INTEGER_TYPE + COMMA
                        + Table.COLUMN_ITEM_CORRECT_ANSWERS + INTEGER_TYPE + COMMA
                        + Table.COLUMN_ITEM_IS_FINISHED + INTEGER_TYPE + COMMA
                        + Table.COLUMN_ITEM_CURRENT_QUESTION_INDEX + INTEGER_TYPE + COMMA
                        + Table.COLUMN_ITEM_IS_CURRENT_QUESTION_ANSWERED + INTEGER_TYPE
                        + CLOSE_BRACKET;
    }
}
