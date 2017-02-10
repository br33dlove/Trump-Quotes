package com.davidcryer.trumpquotes.platformindependent.model.quotes.factories;

import com.davidcryer.trumpquotes.platformindependent.model.quotes.network.QuoteRequester;

public interface QuoteRequesterFactory {
    QuoteRequester create();
}
