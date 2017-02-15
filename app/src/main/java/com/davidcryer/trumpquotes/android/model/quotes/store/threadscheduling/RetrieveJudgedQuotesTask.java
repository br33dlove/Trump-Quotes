package com.davidcryer.trumpquotes.android.model.quotes.store.threadscheduling;

import com.davidcryer.trumpquotes.android.framework.threadscheduling.Task;
import com.davidcryer.trumpquotes.android.framework.threadscheduling.TaskFactory;
import com.davidcryer.trumpquotes.platformindependent.model.quotes.Quote;
import com.davidcryer.trumpquotes.platformindependent.model.quotes.store.QuoteStore;

public class RetrieveJudgedQuotesTask extends Task<Void, RetrieveJudgedQuotesTask.ResponseValues> {
    private final QuoteStore quoteStore;

    RetrieveJudgedQuotesTask(Void requestValues, Callback<ResponseValues> callback, QuoteStore quoteStore) {
        super(requestValues, callback);
        this.quoteStore = quoteStore;
    }

    @Override
    protected void doTask(Void requestValues) {
        onSuccess(new ResponseValues(quoteStore.judgedQuotes()));
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

    static class Factory implements TaskFactory<Void, RetrieveJudgedQuotesTask.ResponseValues> {
        private final QuoteStore quoteStore;

        Factory(QuoteStore quoteStore) {
            this.quoteStore = quoteStore;
        }

        @Override
        public Task<Void, RetrieveJudgedQuotesTask.ResponseValues> create(Void requestValues, Task.Callback<RetrieveJudgedQuotesTask.ResponseValues> callback) {
            return new RetrieveJudgedQuotesTask(requestValues, callback, quoteStore);
        }
    }
}
