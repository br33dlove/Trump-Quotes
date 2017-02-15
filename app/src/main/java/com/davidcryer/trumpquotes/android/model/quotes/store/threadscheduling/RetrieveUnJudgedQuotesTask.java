package com.davidcryer.trumpquotes.android.model.quotes.store.threadscheduling;

import com.davidcryer.trumpquotes.android.model.threadscheduling.Task;
import com.davidcryer.trumpquotes.android.model.threadscheduling.TaskFactory;
import com.davidcryer.trumpquotes.platformindependent.model.quotes.Quote;
import com.davidcryer.trumpquotes.platformindependent.model.quotes.store.QuoteStore;

public class RetrieveUnJudgedQuotesTask extends Task<Void, RetrieveUnJudgedQuotesTask.ResponseValues> {
    private final QuoteStore quoteStore;

    public RetrieveUnJudgedQuotesTask(Void requestValues, Callback<ResponseValues> callback, QuoteStore quoteStore) {
        super(requestValues, callback);
        this.quoteStore = quoteStore;
    }

    @Override
    protected void doTask(Void requestValues) {
        onSuccess(new ResponseValues(quoteStore.unJudgedQuotes()));
    }

    public static class ResponseValues {
        private final Quote[] quotes;

        private ResponseValues(Quote[] quotes) {
            this.quotes = quotes;
        }

        public Quote[] quotes() {
            return quotes;
        }
    }

    public static class Factory implements TaskFactory<Void, RetrieveUnJudgedQuotesTask.ResponseValues> {
        private final QuoteStore quoteStore;

        public Factory(QuoteStore quoteStore) {
            this.quoteStore = quoteStore;
        }

        @Override
        public Task<Void, RetrieveUnJudgedQuotesTask.ResponseValues> create(Void requestValues, Task.Callback<RetrieveUnJudgedQuotesTask.ResponseValues> callback) {
            return new RetrieveUnJudgedQuotesTask(requestValues, callback, quoteStore);
        }
    }
}
