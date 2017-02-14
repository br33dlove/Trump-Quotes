package com.davidcryer.trumpquotes.platformindependent.model.quotes.network.factories;

import com.davidcryer.trumpquotes.platformindependent.model.quotes.network.PersonalisedQuoteRequester;

public interface QuoteRequesterFactory {
    PersonalisedQuoteRequester create();
}
