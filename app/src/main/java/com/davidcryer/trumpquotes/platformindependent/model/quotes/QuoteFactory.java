package com.davidcryer.trumpquotes.platformindependent.model.quotes;

public class QuoteFactory {

    public Quote create(final String id, final String text, long createdTimestamp) {
        return new Quote(id, text, createdTimestamp);
    }
}
