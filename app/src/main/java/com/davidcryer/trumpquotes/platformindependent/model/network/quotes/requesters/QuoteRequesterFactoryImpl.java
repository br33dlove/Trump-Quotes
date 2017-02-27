package com.davidcryer.trumpquotes.platformindependent.model.network.quotes.requesters;

import com.davidcryer.trumpquotes.platformindependent.model.network.quotes.QuoteRequestFactory;

public class QuoteRequesterFactoryImpl implements QuoteRequesterFactory {
    private final QuoteRequestFactory quoteRequestFactory;

    public QuoteRequesterFactoryImpl(QuoteRequestFactory quoteRequestFactory) {
        this.quoteRequestFactory = quoteRequestFactory;
    }

    @Override
    public RandomQuoteRequester createRandomQuoteRequester() {
        return new RandomQuoteRequesterImpl(quoteRequestFactory);
    }
}
