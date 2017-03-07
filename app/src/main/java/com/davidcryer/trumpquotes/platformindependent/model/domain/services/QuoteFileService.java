package com.davidcryer.trumpquotes.platformindependent.model.domain.services;

import com.davidcryer.trumpquotes.platformindependent.model.network.quotes.Quote;

import java.io.IOException;

public interface QuoteFileService {
    Quote randomQuote() throws IOException;
    void clearCaches();
}
