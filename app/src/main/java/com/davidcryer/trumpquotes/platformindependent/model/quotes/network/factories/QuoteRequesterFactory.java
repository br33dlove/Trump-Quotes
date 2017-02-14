package com.davidcryer.trumpquotes.platformindependent.model.quotes.network.factories;

import com.davidcryer.trumpquotes.platformindependent.model.quotes.network.requesters.PersonalisedQuoteRequester;

public interface QuoteRequesterFactory {
    PersonalisedQuoteRequester create();
}
