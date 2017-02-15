package com.davidcryer.trumpquotes.platformindependent.model.quotes.network.requesters.factories;

import com.davidcryer.trumpquotes.platformindependent.model.quotes.network.QuoteRequestCallback;
import com.davidcryer.trumpquotes.platformindependent.model.quotes.network.QuoteRequestFactory;
import com.davidcryer.trumpquotes.platformindependent.model.quotes.network.requesters.PersonalisedQuoteRequester;
import com.davidcryer.trumpquotes.platformindependent.model.quotes.network.requesters.PersonalisedQuoteRequesterImpl;
import com.davidcryer.trumpquotes.platformindependent.model.quotes.network.requesters.RandomQuoteRequester;
import com.davidcryer.trumpquotes.platformindependent.model.quotes.network.requesters.RandomQuoteRequesterImpl;

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
