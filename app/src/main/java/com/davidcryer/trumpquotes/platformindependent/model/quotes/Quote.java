package com.davidcryer.trumpquotes.platformindependent.model.quotes;

public class Quote {
    private final String id;
    private final String text;
    private final long createdTimestamp;

    private Quote(String id, String text, long createdTimestamp) {
        this.id = id;
        this.text = text;
        this.createdTimestamp = createdTimestamp;
    }

    static Quote newInstance(final String id, final String text, final long createdTimestamp) {
        return new Quote(id, text, createdTimestamp);
    }

    public String id() {
        return id;
    }

    public String text() {
        return text;
    }

    public boolean newerThan(final Quote quote) {
        return createdTimestamp > quote.createdTimestamp;
    }
}
