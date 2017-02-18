package com.davidcryer.trumpquotes.android.model.quotes.store;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.provider.BaseColumns;

import com.davidcryer.trumpquotes.android.model.helpers.store.QueryHelper;
import com.davidcryer.trumpquotes.platformindependent.model.quotes.Quote;
import com.davidcryer.trumpquotes.platformindependent.model.quotes.QuoteFactory;

import static com.davidcryer.trumpquotes.android.model.helpers.store.SQLHelper.CLOSE_BRACKET;
import static com.davidcryer.trumpquotes.android.model.helpers.store.SQLHelper.COMMA;
import static com.davidcryer.trumpquotes.android.model.helpers.store.SQLHelper.CREATE_TABLE;
import static com.davidcryer.trumpquotes.android.model.helpers.store.SQLHelper.INTEGER_TYPE;
import static com.davidcryer.trumpquotes.android.model.helpers.store.SQLHelper.OPEN_BRACKET;
import static com.davidcryer.trumpquotes.android.model.helpers.store.SQLHelper.PRIMARY_KEY_TYPE;
import static com.davidcryer.trumpquotes.android.model.helpers.store.SQLHelper.SPACE;
import static com.davidcryer.trumpquotes.android.model.helpers.store.SQLHelper.TEXT_TYPE;

public class QuoteContractImpl implements QuoteContract {
    private final static int VALUE_IS_JUDGED = 1;
    private final static int VALUE_NOT_JUDGED = 0;
    private final static String ARG_IS_JUDGED = String.valueOf(VALUE_IS_JUDGED);
    private final static String ARG_NOT_JUDGED = String.valueOf(VALUE_NOT_JUDGED);
    private final QuoteFactory quoteFactory;


    public QuoteContractImpl(QuoteFactory quoteFactory) {
        this.quoteFactory = quoteFactory;
    }

    @Override
    public void createTable(final SQLiteDatabase database) {
        database.execSQL(Table.SQL_CREATE_ENTRIES);
    }

    @Override
    public boolean store(SQLiteDatabase database, Quote... quotes) {
        database.beginTransaction();
        try {
            for (final Quote quote : quotes) {
                database.insert(Table.TABLE_NAME, null, insertContentValues(quote));
            }
            database.setTransactionSuccessful();
            return true;
        } catch (SQLiteException sqle) {
            return false;
        } finally {
            database.endTransaction();
        }
    }

    private ContentValues insertContentValues(final Quote quote) {
        final ContentValues values =  new ContentValues();
        values.put(Table._ID, quote.id());
        values.put(Table.COLUMN_ITEM_TEXT, quote.text());
        values.put(Table.COLUMN_ITEM_IS_JUDGED, quote.judged() ? VALUE_IS_JUDGED : VALUE_NOT_JUDGED);
        values.put(Table.COLUMN_ITEM_CREATED_TIMESTAMP, quote.createdTimestamp());
        return values;
    }

    @Override
    public boolean clear(SQLiteDatabase database, String... quoteIds) {
        database.beginTransaction();
        try {
            database.delete(Table.TABLE_NAME, QueryHelper.createSelectionIn(Table._ID, quoteIds.length), quoteIds);
            database.setTransactionSuccessful();
            return true;
        } catch (SQLiteException sqle) {
            return false;
        } finally {
            database.endTransaction();
        }
    }

    @Override
    public Quote[] judgedQuotes(SQLiteDatabase database) {
        return retrieveQuotesForJudgedValue(database, ARG_IS_JUDGED);
    }

    @Override
    public Quote[] unJudgedQuotes(SQLiteDatabase database) {
        return retrieveQuotesForJudgedValue(database, ARG_NOT_JUDGED);
    }

    private Quote[] retrieveQuotesForJudgedValue(final SQLiteDatabase database, final String judgedValue) {
        final Cursor cursor = database.query(
                Table.TABLE_NAME,
                null,
                QueryHelper.createSelectionEquals(Table.COLUMN_ITEM_IS_JUDGED),
                QueryHelper.createSelectionArgs(judgedValue),
                null,
                null,
                null
        );
        final Quote[] quotes = new Quote[cursor.getCount()];
        int i = 0;
        while (cursor.moveToNext()) {
            quotes[i] = quote(cursor);
            i++;
        }
        cursor.close();
        return quotes;
    }

    private Quote quote(final Cursor cursor) {
        return quoteFactory.create(
                cursor.getString(cursor.getColumnIndex(Table._ID)),
                cursor.getString(cursor.getColumnIndex(Table.COLUMN_ITEM_TEXT)),
                cursor.getLong(cursor.getColumnIndex(Table.COLUMN_ITEM_CREATED_TIMESTAMP)),
                cursor.getInt(cursor.getColumnIndex(Table.COLUMN_ITEM_IS_JUDGED)) == VALUE_IS_JUDGED
        );
    }

    @Override
    public boolean updateQuoteAsJudged(SQLiteDatabase database, String quoteId) {
        database.beginTransaction();
        try {
            database.update(
                    Table.TABLE_NAME,
                    updateForJudgedContentValues(),
                    QueryHelper.createSelectionEquals(Table._ID),
                    QueryHelper.createSelectionArgs(quoteId)
            );
            database.setTransactionSuccessful();
            return true;
        } catch (SQLiteException sqle) {
            return false;
        } finally {
            database.endTransaction();
        }
    }

    private ContentValues updateForJudgedContentValues() {
        final ContentValues values = new ContentValues();
        values.put(Table.COLUMN_ITEM_IS_JUDGED, VALUE_IS_JUDGED);
        return values;
    }

    private static class Table implements BaseColumns {
        private final static String TABLE_NAME = "quote";
        private final static String COLUMN_ITEM_TEXT = "text";
        private final static String COLUMN_ITEM_CREATED_TIMESTAMP = "createdTimestamp";
        private final static String COLUMN_ITEM_IS_JUDGED = "judged";
        private static final String SQL_CREATE_ENTRIES =
                CREATE_TABLE + Table.TABLE_NAME + SPACE
                        + OPEN_BRACKET
                        + Table._ID + TEXT_TYPE + PRIMARY_KEY_TYPE + COMMA
                        + Table.COLUMN_ITEM_TEXT + TEXT_TYPE + COMMA
                        + Table.COLUMN_ITEM_CREATED_TIMESTAMP + INTEGER_TYPE + COMMA
                        + Table.COLUMN_ITEM_IS_JUDGED + INTEGER_TYPE
                        + CLOSE_BRACKET;
    }
}
