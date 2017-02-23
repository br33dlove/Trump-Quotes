package com.davidcryer.trumpquotes.platformindependent.model.network.quotes.requesters;

import com.davidcryer.trumpquotes.platformindependent.model.network.quotes.QuoteRequestCallback;
import com.davidcryer.trumpquotes.platformindependent.model.network.quotes.QuoteRequestFactory;
import com.davidcryer.trumpquotes.platformindependent.model.network.quotes.requesters.PersonalisedQuoteRequester;
import com.davidcryer.trumpquotes.platformindependent.model.network.quotes.requesters.PersonalisedQuoteRequesterImpl;
import com.davidcryer.trumpquotes.platformindependent.model.network.quotes.requesters.RandomQuoteRequester;
import com.davidcryer.trumpquotes.platformindependent.model.network.quotes.requesters.RandomQuoteRequesterImpl;

import java.util.HashSet;

public class QuoteRequesterFactoryImpl implements QuoteRequesterFactory {
    private final QuoteRequestFactory quoteRequestFactory;

    public QuoteRequesterFactoryImpl(QuoteRequestFactory quoteRequestFactory) {
        this.quoteRequestFactory = quoteRequestFactory;
    }

    @Override
    public PersonalisedQuoteRequester createPersonalisedQuoteRequester() {
        return new PersonalisedQuoteRequesterImpl(quoteRequestFactory, new HashSet<QuoteRequestCallback>());
    }

    @Override
    public RandomQuoteRequester createRandomQuoteRequester() {
        return new RandomQuoteRequesterImpl(quoteRequestFactory, new HashSet<QuoteRequestCallback>());
    }
}
