package com.davidcryer.trumpquotes.platformindependent.model.quotes;

public class Quote {
    private final String id;
    private final String text;
    private final long createdTimestamp;
    private final boolean judged;
    private final boolean isTrumpQuote;
    private final String source;

    Quote(String id, String text, long createdTimestamp, boolean judged, boolean isTrumpQuote, String source) {
        this.id = id;
        this.text = text;
        this.createdTimestamp = createdTimestamp;
        this.judged = judged;
        this.isTrumpQuote = isTrumpQuote;
        this.source = source;
    }

    public String id() {
        return id;
    }

    public String text() {
        return text;
    }

    public boolean judged() {
        return judged;
    }

    public long createdTimestamp() {
        return createdTimestamp;
    }

    public boolean isTrumpQuote() {
        return isTrumpQuote;
    }

    public String source() {
        return source;
    }

    boolean newerThan(final Quote quote) {
        return createdTimestamp > quote.createdTimestamp;
    }
}
