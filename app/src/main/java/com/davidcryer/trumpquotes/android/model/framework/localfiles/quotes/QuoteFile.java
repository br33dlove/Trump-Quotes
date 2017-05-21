package com.davidcryer.trumpquotes.android.model.framework.localfiles.quotes;

import com.davidcryer.trumpquotes.android.model.framework.network.quotes.Quote;

import java.io.IOException;

public interface QuoteFile {
    Quote[] quotes() throws IOException;
}
