package com.davidcryer.trumpquotes.platformindependent.model.network.quotes.gumpfile;

import com.davidcryer.trumpquotes.platformindependent.model.network.quotes.Quote;

public class GumpQuote implements Quote {
    private final String quote;

    public GumpQuote(String quote) {
        this.quote = quote;
    }

    public String quote() {
        return quote;
    }
}
