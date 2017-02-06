package com.davidcryer.trumpquotes.platformindependent.view.viewmodels.models;

public class ViewQuoteHelper {

    public static String[] ids(final ViewQuote[] quotes) {
        final String[] ids = new String[quotes.length];
        for (int i = 0; i < ids.length; i++) {
            ids[i] = quotes[i].id();
        }
        return ids;
    }
}
