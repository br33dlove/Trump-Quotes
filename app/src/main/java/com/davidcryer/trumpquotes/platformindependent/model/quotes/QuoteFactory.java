package com.davidcryer.trumpquotes.platformindependent.model.quotes;

public class QuoteFactory {

    private QuoteFactory() {

    }

    public static QuoteFactory newInstance() {
        return new QuoteFactory();
    }

    public Quote create(final String id, final String text) {
        return Quote.newInstance(id, text);
    }
}
