package com.davidcryer.trumpquotes.platformindependent.model.quotes.network.trumpapi;

import com.davidcryer.trumpquotes.platformindependent.model.quotes.Quote;
import com.davidcryer.trumpquotes.platformindependent.model.quotes.QuoteFactory;

import java.util.UUID;

public class TrumpQuoteToQuoteAdapter {

    private TrumpQuoteToQuoteAdapter() {

    }

    public static Quote quote(final TrumpQuote trumpQuote) {
        return QuoteFactory.createTrumpQuote(UUID.randomUUID().toString(), trumpQuote.message, false, System.currentTimeMillis());
    }
}
