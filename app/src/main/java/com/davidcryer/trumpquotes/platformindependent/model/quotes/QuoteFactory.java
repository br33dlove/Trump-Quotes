package com.davidcryer.trumpquotes.platformindependent.model.quotes;

public class QuoteFactory {

    public Quote create(final String id, final String text, final long createdTimestamp, final boolean judged) {
        return new Quote(id, text, createdTimestamp, judged);
    }
}
