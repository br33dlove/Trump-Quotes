package com.davidcryer.trumpquotes.platformindependent.model.quotes.network.requesters.factories;

import com.davidcryer.trumpquotes.platformindependent.model.quotes.network.requesters.PersonalisedQuoteRequester;
import com.davidcryer.trumpquotes.platformindependent.model.quotes.network.requesters.RandomQuoteRequester;

public interface QuoteRequesterFactory {
    PersonalisedQuoteRequester createPersonalisedQuoteRequester();
    RandomQuoteRequester createRandomQuoteRequester();
}
