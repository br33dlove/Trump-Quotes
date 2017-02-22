package com.davidcryer.trumpquotes.platformindependent.model.quotes;

public class Quote {
    private final String id;
    private final String text;
    private final String source;
    private final boolean judged;
    private final boolean isTrumpQuote;
    private final long createdTimestamp;

    Quote(String id, String text, String source, boolean judged, boolean isTrumpQuote, long createdTimestamp) {
        this.id = id;
        this.text = text;
        this.source = source;
        this.judged = judged;
        this.isTrumpQuote = isTrumpQuote;
        this.createdTimestamp = createdTimestamp;
    }

    public String id() {
        return id;
    }

    public String text() {
        return text;
    }

    public String source() {
        return source;
    }

    public boolean judged() {
        return judged;
    }

    public boolean isTrumpQuote() {
        return isTrumpQuote;
    }

    public long createdTimestamp() {
        return createdTimestamp;
    }

    boolean newerThan(final Quote quote) {
        return createdTimestamp > quote.createdTimestamp;
    }
}
