package com.davidcryer.trumpquotes.platformindependent.model.quotes.network.factories;

import com.davidcryer.trumpquotes.platformindependent.model.quotes.network.QuoteResponseHandler;

public interface QuoteResponseHandlerFactory {
    QuoteResponseHandler create();
}
