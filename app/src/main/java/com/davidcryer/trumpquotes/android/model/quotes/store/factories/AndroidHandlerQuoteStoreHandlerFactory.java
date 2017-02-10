package com.davidcryer.trumpquotes.android.model.quotes.store.factories;

import com.davidcryer.trumpquotes.android.model.quotes.store.AndroidHandlerQuoteStoreHandler;
import com.davidcryer.trumpquotes.platformindependent.model.quotes.store.QuoteStore;
import com.davidcryer.trumpquotes.platformindependent.model.quotes.store.QuoteStoreHandler;
import com.davidcryer.trumpquotes.platformindependent.model.quotes.store.factories.QuoteStoreHandlerFactory;

public class AndroidHandlerQuoteStoreHandlerFactory implements QuoteStoreHandlerFactory {
    private final QuoteStore quoteStore;

    public AndroidHandlerQuoteStoreHandlerFactory(QuoteStore quoteStore) {
        this.quoteStore = quoteStore;
    }

    @Override
    public QuoteStoreHandler create() {
        return new AndroidHandlerQuoteStoreHandler(quoteStore);
    }
}
