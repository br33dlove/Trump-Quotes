package com.davidcryer.trumpquotes.android.model.quotes.store.threadscheduling;

import com.davidcryer.trumpquotes.android.model.threadscheduling.Task;
import com.davidcryer.trumpquotes.android.model.threadscheduling.TaskFactory;
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

    public static class RequestValues {
        private final Quote[] quotes;

        public RequestValues(Quote[] quotes) {
            this.quotes = quotes;
        }
    }

    public static class Factory implements TaskFactory<StoreQuotesTask.RequestValues, Void> {
        private final QuoteStore quoteStore;

        public Factory(QuoteStore quoteStore) {
            this.quoteStore = quoteStore;
        }

        @Override
        public Task<StoreQuotesTask.RequestValues, Void> create(StoreQuotesTask.RequestValues requestValues, Task.Callback<Void> callback) {
            return new StoreQuotesTask(requestValues, callback, quoteStore);
        }
    }
}
