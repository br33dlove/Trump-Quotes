package com.davidcryer.trumpquotes.android.model.quotes.store.tasks;

import com.davidcryer.trumpquotes.android.framework.tasks.Task;
import com.davidcryer.trumpquotes.android.framework.tasks.factories.TaskFactory;
import com.davidcryer.trumpquotes.platformindependent.model.quotes.Quote;
import com.davidcryer.trumpquotes.platformindependent.model.quotes.store.QuoteStore;

public class RetrieveJudgedQuotesTask extends Task<Void, RetrieveJudgedQuotesTask.ResponseValues> {
    private final QuoteStore quoteStore;

    private RetrieveJudgedQuotesTask(Void requestValues, Callback<ResponseValues> callback, QuoteStore quoteStore) {
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

    public static class Factory implements TaskFactory<Void, RetrieveJudgedQuotesTask.ResponseValues> {
        private final QuoteStore quoteStore;

        public Factory(QuoteStore quoteStore) {
            this.quoteStore = quoteStore;
        }

        @Override
        public Task<Void, RetrieveJudgedQuotesTask.ResponseValues> create(Void requestValues, Task.Callback<RetrieveJudgedQuotesTask.ResponseValues> callback) {
            return new RetrieveJudgedQuotesTask(requestValues, callback, quoteStore);
        }
    }
}
