package com.davidcryer.trumpquotes.platformindependent.presenter;

import android.support.annotation.NonNull;

import com.davidcryer.trumpquotes.platformindependent.model.quotes.Quote;

import java.util.List;

public class QuoteHelper {

    public static Quote removeMostRecent(@NonNull final List<Quote> quotes) {
        final int quotesSize = quotes.size();
        if (quotesSize > 0) {
            Quote mostRecentQuote = quotes.get(0);
            for (int i = 1; i < quotesSize; i++) {
                final Quote quote = quotes.get(i);
                if (quote.newerThan(mostRecentQuote)) {
                    mostRecentQuote = quote;
                }
            }
            quotes.remove(mostRecentQuote);
            return mostRecentQuote;
        }
        return null;
    }

    public static String[] ids(final List<Quote> quotes) {
        final String[] ids = new String[quotes.size()];
        for (int i = 0; i < ids.length; i++) {
            ids[i] = quotes.get(i).id();
        }
        return ids;
    }
}
