package com.davidcryer.trumpquotes.platformindependent.model.framework.localfiles.quotes.gump;

import com.davidcryer.trumpquotes.platformindependent.model.framework.network.quotes.Quote;

public class GumpQuote implements Quote {
    private final String quote;

    public GumpQuote(String quote) {
        this.quote = quote;
    }

    public String quote() {
        return quote;
    }
}
