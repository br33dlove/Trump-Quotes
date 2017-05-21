package com.davidcryer.trumpquotes.android.model.framework.localfiles.quotes.gump;

import com.davidcryer.trumpquotes.android.model.framework.network.quotes.Quote;

public class GumpQuote implements Quote {
    private final String quote;

    @SuppressWarnings("unused")
    public GumpQuote(String quote) {
        this.quote = quote;
    }

    public String quote() {
        return quote;
    }
}
