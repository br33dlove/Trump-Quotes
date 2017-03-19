package com.davidcryer.trumpquotes.platformindependent.model.framework.network.quotes.trumpapi;

import com.davidcryer.trumpquotes.platformindependent.model.framework.network.quotes.Quote;

public class TrumpQuote implements Quote {
    private String message;

    public TrumpQuote(String message) {
        this.message = message;
    }

    @Override
    public String quote() {
        return message;
    }
}
