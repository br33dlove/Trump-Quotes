package com.davidcryer.trumpquotes.platformindependent.model.network.quotes.trumpapi;

import com.davidcryer.trumpquotes.platformindependent.model.network.quotes.Quote;

public class TrumpQuote implements Quote {
    private String quote;

    public TrumpQuote(String quote) {
        this.quote = quote;
    }

    @Override
    public String quote() {
        return quote;
    }
}
