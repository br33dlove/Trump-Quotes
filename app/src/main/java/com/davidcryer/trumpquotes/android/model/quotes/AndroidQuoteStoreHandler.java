package com.davidcryer.trumpquotes.android.model.quotes;

import com.davidcryer.trumpquotes.platformindependent.model.quotes.Quote;
import com.davidcryer.trumpquotes.platformindependent.model.quotes.QuoteStore;
import com.davidcryer.trumpquotes.platformindependent.model.quotes.QuoteStoreHandler;

public class AndroidQuoteStoreHandler implements QuoteStoreHandler {
    private final QuoteStore quoteStore;

    public AndroidQuoteStoreHandler(QuoteStore quoteStore) {
        this.quoteStore = quoteStore;
    }

    @Override
    public void store(Quote... quotes) {

    }

    @Override
    public void clear(String... quoteIds) {

    }

    @Override
    public void retrieveJudgedQuotes(RetrieveCallback callback) {

    }

    @Override
    public void retrieveUnJudgedQuotes(RetrieveCallback callback) {

    }

    @Override
    public void updateQuoteAsJudged(String quoteId) {

    }
}
