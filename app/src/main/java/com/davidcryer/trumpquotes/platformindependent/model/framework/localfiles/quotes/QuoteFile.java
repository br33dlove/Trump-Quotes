package com.davidcryer.trumpquotes.platformindependent.model.framework.localfiles.quotes;

import com.davidcryer.trumpquotes.platformindependent.model.framework.network.quotes.Quote;

import java.io.IOException;

public interface QuoteFile {
    Quote[] quotes() throws IOException;
}
