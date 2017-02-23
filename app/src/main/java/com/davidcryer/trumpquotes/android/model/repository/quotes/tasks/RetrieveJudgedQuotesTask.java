package com.davidcryer.trumpquotes.android.model.repository.quotes.tasks;

import com.davidcryer.trumpquotes.android.framework.tasks.Task;
import com.davidcryer.trumpquotes.android.framework.tasks.TaskFactory;
import com.davidcryer.trumpquotes.platformindependent.model.quotes.Quote;
import com.davidcryer.trumpquotes.platformindependent.model.repository.quotes.QuoteRepository;

public class RetrieveJudgedQuotesTask extends Task<Void, RetrieveJudgedQuotesTask.ResponseValues> {
    private final QuoteRepository quoteRepository;

    private RetrieveJudgedQuotesTask(Void requestValues, Callback<ResponseValues> callback, QuoteRepository quoteRepository) {
        super(requestValues, callback);
        this.quoteRepository = quoteRepository;
    }

    @Override
    protected void doTask(Void requestValues) {
        onSuccess(new ResponseValues(quoteRepository.judgedQuotes()));
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
        private final QuoteRepository quoteRepository;

        public Factory(QuoteRepository quoteRepository) {
            this.quoteRepository = quoteRepository;
        }

        @Override
        public Task<Void, RetrieveJudgedQuotesTask.ResponseValues> create(Void requestValues, Task.Callback<RetrieveJudgedQuotesTask.ResponseValues> callback) {
            return new RetrieveJudgedQuotesTask(requestValues, callback, quoteRepository);
        }
    }
}
