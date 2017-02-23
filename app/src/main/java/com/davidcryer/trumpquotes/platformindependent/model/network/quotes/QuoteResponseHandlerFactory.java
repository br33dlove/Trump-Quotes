package com.davidcryer.trumpquotes.platformindependent.model.network.quotes;

import com.davidcryer.trumpquotes.platformindependent.model.network.quotes.QuoteResponseHandler;

public interface QuoteResponseHandlerFactory {
    QuoteResponseHandler create();
}
