package com.davidcryer.trumpquotes.android.model.quotes.store;

import com.davidcryer.trumpquotes.android.model.threadscheduling.Task;
import com.davidcryer.trumpquotes.platformindependent.model.quotes.Quote;
import com.davidcryer.trumpquotes.platformindependent.model.quotes.store.QuoteStore;

public class StoreQuotesTask extends Task<StoreQuotesTask.RequestValues, Void> {
    private final QuoteStore quoteStore;

    public StoreQuotesTask(RequestValues requestValues, Callback<Void> callback, QuoteStore quoteStore) {
        super(requestValues, callback);
        this.quoteStore = quoteStore;
    }

    @Override
    protected void doTask(RequestValues requestValues) {
        if (quoteStore.store(requestValues.quotes)) {
            onSuccess(null);
        } else {
            onError();
        }
    }

    public class RequestValues {
        private final Quote[] quotes;

        public RequestValues(Quote[] quotes) {
            this.quotes = quotes;
        }
    }
}
