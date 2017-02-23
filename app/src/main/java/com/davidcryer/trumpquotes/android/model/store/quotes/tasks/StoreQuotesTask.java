package com.davidcryer.trumpquotes.android.model.store.quotes.tasks;

import com.davidcryer.trumpquotes.android.framework.tasks.Task;
import com.davidcryer.trumpquotes.android.framework.tasks.TaskFactory;
import com.davidcryer.trumpquotes.platformindependent.model.quotes.Quote;
import com.davidcryer.trumpquotes.platformindependent.model.store.quotes.QuoteRepository;

public class StoreQuotesTask extends Task<StoreQuotesTask.RequestValues, Void> {
    private final QuoteRepository quoteRepository;

    private StoreQuotesTask(RequestValues requestValues, Callback<Void> callback, QuoteRepository quoteRepository) {
        super(requestValues, callback);
        this.quoteRepository = quoteRepository;
    }

    @Override
    protected void doTask(RequestValues requestValues) {
        if (quoteRepository.store(requestValues.quotes)) {
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
        private final QuoteRepository quoteRepository;

        public Factory(QuoteRepository quoteRepository) {
            this.quoteRepository = quoteRepository;
        }

        @Override
        public Task<StoreQuotesTask.RequestValues, Void> create(StoreQuotesTask.RequestValues requestValues, Task.Callback<Void> callback) {
            return new StoreQuotesTask(requestValues, callback, quoteRepository);
        }
    }
}
