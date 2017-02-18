package com.davidcryer.trumpquotes.platformindependent.model.quotes.network.trumpapi;

import com.davidcryer.trumpquotes.platformindependent.model.quotes.Quote;
import com.davidcryer.trumpquotes.platformindependent.model.quotes.QuoteFactory;

import java.util.UUID;

public class TrumpQuoteToQuoteAdapter {
    private final QuoteFactory quoteFactory;

    public TrumpQuoteToQuoteAdapter(QuoteFactory quoteFactory) {
        this.quoteFactory = quoteFactory;
    }

    public Quote quote(final TrumpQuote trumpQuote) {
        return quoteFactory.create(UUID.randomUUID().toString(), trumpQuote.message, System.currentTimeMillis(), false);
    }
}
