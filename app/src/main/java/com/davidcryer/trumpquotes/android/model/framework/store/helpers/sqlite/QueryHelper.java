package com.davidcryer.trumpquotes.android.model.framework.store.helpers.sqlite;

public class QueryHelper {

    public static String createSelectionIn(final String column, final int arguments) {
        String bindArgsString = "";
        for (int i = 0; i < arguments; i++) {
            if (i != 0) {
                bindArgsString += ", ";
            }
            bindArgsString += "?";
        }
        return column + " in (" + bindArgsString + ")";
    }

    public static String[] createSelectionArgs(final String... columns) {
        return columns;
    }
}
