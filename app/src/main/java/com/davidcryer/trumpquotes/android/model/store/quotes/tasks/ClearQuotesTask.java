package com.davidcryer.trumpquotes.android.model.store.quotes.tasks;

import com.davidcryer.trumpquotes.android.framework.tasks.Task;
import com.davidcryer.trumpquotes.android.framework.tasks.TaskFactory;
import com.davidcryer.trumpquotes.platformindependent.model.store.quotes.QuoteRepository;

public class ClearQuotesTask extends Task<ClearQuotesTask.RequestValues, Void> {
    private final QuoteRepository quoteRepository;

    private ClearQuotesTask(RequestValues requestValues, Callback<Void> callback, QuoteRepository quoteRepository) {
        super(requestValues, callback);
        this.quoteRepository = quoteRepository;
    }

    @Override
    protected void doTask(RequestValues requestValues) {
        if (quoteRepository.clear(requestValues.quoteIds)) {
            onSuccess(null);
        } else {
            onError();
        }
    }

    public final static class RequestValues {
        private final String[] quoteIds;

        public RequestValues(String[] quoteIds) {
            this.quoteIds = quoteIds;
        }
    }

    public static class Factory implements TaskFactory<ClearQuotesTask.RequestValues, Void> {
        private final QuoteRepository quoteRepository;

        public Factory(QuoteRepository quoteRepository) {
            this.quoteRepository = quoteRepository;
        }

        @Override
        public Task<ClearQuotesTask.RequestValues, Void> create(ClearQuotesTask.RequestValues requestValues, Task.Callback<Void> callback) {
            return new ClearQuotesTask(requestValues, callback, quoteRepository);
        }
    }
}
