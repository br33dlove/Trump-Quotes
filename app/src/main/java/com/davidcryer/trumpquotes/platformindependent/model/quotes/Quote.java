package com.davidcryer.trumpquotes.platformindependent.model.quotes;

public class Quote {
    private final String id;
    private final String text;
    private final long createdTimestamp;
    private final boolean judged;

    Quote(String id, String text, long createdTimestamp, boolean judged) {
        this.id = id;
        this.text = text;
        this.createdTimestamp = createdTimestamp;
        this.judged = judged;
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

    public boolean newerThan(final Quote quote) {
        return createdTimestamp > quote.createdTimestamp;
    }
}
