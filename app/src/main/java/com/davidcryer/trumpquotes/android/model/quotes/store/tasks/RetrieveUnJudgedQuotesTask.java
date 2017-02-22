package com.davidcryer.trumpquotes.android.model.quotes.store.tasks;

import com.davidcryer.trumpquotes.android.framework.tasks.Task;
import com.davidcryer.trumpquotes.android.framework.tasks.factories.TaskFactory;
import com.davidcryer.trumpquotes.platformindependent.model.quotes.Quote;
import com.davidcryer.trumpquotes.platformindependent.model.quotes.store.QuoteRepository;

public class RetrieveUnJudgedQuotesTask extends Task<Void, RetrieveUnJudgedQuotesTask.ResponseValues> {
    private final QuoteRepository quoteRepository;

    private RetrieveUnJudgedQuotesTask(Void requestValues, Callback<ResponseValues> callback, QuoteRepository quoteRepository) {
        super(requestValues, callback);
        this.quoteRepository = quoteRepository;
    }

    @Override
    protected void doTask(Void requestValues) {
        onSuccess(new ResponseValues(quoteRepository.unJudgedQuotes()));
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
        private final QuoteRepository quoteRepository;

        public Factory(QuoteRepository quoteRepository) {
            this.quoteRepository = quoteRepository;
        }

        @Override
        public Task<Void, RetrieveUnJudgedQuotesTask.ResponseValues> create(Void requestValues, Task.Callback<RetrieveUnJudgedQuotesTask.ResponseValues> callback) {
            return new RetrieveUnJudgedQuotesTask(requestValues, callback, quoteRepository);
        }
    }
}
