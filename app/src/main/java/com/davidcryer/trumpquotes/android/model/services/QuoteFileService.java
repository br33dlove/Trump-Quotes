package com.davidcryer.trumpquotes.android.model.services;

import com.davidcryer.trumpquotes.android.model.framework.network.quotes.Quote;

import java.io.IOException;

public interface QuoteFileService {
    Quote randomQuote() throws IOException;
    void clearCaches();
}
