package com.davidcryer.trumpquotes.platformindependent.model.network.quotes.requesters;

import com.davidcryer.trumpquotes.platformindependent.model.network.quotes.requesters.PersonalisedQuoteRequester;
import com.davidcryer.trumpquotes.platformindependent.model.network.quotes.requesters.RandomQuoteRequester;

public interface QuoteRequesterFactory {
    PersonalisedQuoteRequester createPersonalisedQuoteRequester();
    RandomQuoteRequester createRandomQuoteRequester();
}
