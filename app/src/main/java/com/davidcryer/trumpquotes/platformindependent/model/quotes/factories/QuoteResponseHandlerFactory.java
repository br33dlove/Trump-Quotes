package com.davidcryer.trumpquotes.platformindependent.model.quotes.factories;

import com.davidcryer.trumpquotes.platformindependent.model.quotes.QuoteResponseHandler;

public interface QuoteResponseHandlerFactory {
    QuoteResponseHandler create(final QuoteResponseHandler.Callback callback);
}
