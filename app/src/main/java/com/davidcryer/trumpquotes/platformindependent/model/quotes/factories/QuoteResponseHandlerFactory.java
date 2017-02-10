package com.davidcryer.trumpquotes.platformindependent.model.quotes.factories;

import com.davidcryer.trumpquotes.platformindependent.model.quotes.network.QuoteResponseHandler;

public interface QuoteResponseHandlerFactory {
    QuoteResponseHandler create();
}
