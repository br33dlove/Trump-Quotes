package com.davidcryer.trumpquotes.android.model.store.quotes.tasks;

import com.davidcryer.trumpquotes.android.framework.tasks.Task;
import com.davidcryer.trumpquotes.android.framework.tasks.TaskFactory;
import com.davidcryer.trumpquotes.platformindependent.model.store.quotes.QuoteRepository;

public class UpdateQuoteAsJudgedTask extends Task<UpdateQuoteAsJudgedTask.RequestValues, Void> {
    private final QuoteRepository quoteRepository;

    private UpdateQuoteAsJudgedTask(RequestValues requestValues, Callback<Void> callback, QuoteRepository quoteRepository) {
        super(requestValues, callback);
        this.quoteRepository = quoteRepository;
    }

    @Override
    protected void doTask(RequestValues requestValues) {
        if (quoteRepository.updateQuoteAsJudged(requestValues.quoteId)) {
            onSuccess(null);
        } else {
            onError();
        }
    }

    public static class RequestValues {
        private final String quoteId;

        public RequestValues(String quoteId) {
            this.quoteId = quoteId;
        }
    }

    public static class Factory implements TaskFactory<UpdateQuoteAsJudgedTask.RequestValues, Void> {
        private final QuoteRepository quoteRepository;

        public Factory(QuoteRepository quoteRepository) {
            this.quoteRepository = quoteRepository;
        }

        @Override
        public Task<UpdateQuoteAsJudgedTask.RequestValues, Void> create(UpdateQuoteAsJudgedTask.RequestValues requestValues, Task.Callback<Void> callback) {
            return new UpdateQuoteAsJudgedTask(requestValues, callback, quoteRepository);
        }
    }
}
