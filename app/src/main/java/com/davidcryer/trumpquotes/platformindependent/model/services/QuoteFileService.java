package com.davidcryer.trumpquotes.platformindependent.model.services;

import com.davidcryer.trumpquotes.platformindependent.model.network.quotes.Quote;

import java.io.IOException;

public interface QuoteFileService {
    Quote randomQuote() throws IOException;
    void clearCaches();
}
