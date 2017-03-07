package com.davidcryer.trumpquotes.platformindependent.model.framework.localfiles;

import com.davidcryer.trumpquotes.platformindependent.model.network.quotes.Quote;

import java.io.IOException;

public interface QuoteFile {
    Quote[] quotes() throws IOException;
}
