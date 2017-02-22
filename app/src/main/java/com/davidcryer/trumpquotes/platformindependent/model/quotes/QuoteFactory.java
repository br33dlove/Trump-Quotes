package com.davidcryer.trumpquotes.platformindependent.model.quotes;

public class QuoteFactory {
    private final static String SOURCE_TRUMP = "Donald J. Trump";

    private QuoteFactory() {

    }

    public static Quote create(
            final String id,
            final String text,
            final String source,
            final boolean isTrumpQuote,
            final boolean judged,
            final long createdTimestamp
    ) {
        return new Quote(id, text, source, judged, isTrumpQuote, createdTimestamp);
    }

    public static Quote createTrumpQuote(
            final String id,
            final String text,
            final boolean judged,
            final long createdTimestamp
    ) {
        return new Quote(id, text, SOURCE_TRUMP, judged, true, createdTimestamp);
    }

    public static Quote createNonTrumpQuote(
            final String id,
            final String text,
            final String source,
            final boolean judged,
            final long createdTimestamp
    ) {
        return new Quote(id, text, source, judged, false, createdTimestamp);
    }
}
