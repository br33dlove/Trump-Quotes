package com.davidcryer.trumpquotes.android.model.quotes.store.threadscheduling;

import com.davidcryer.trumpquotes.android.framework.threadscheduling.Task;
import com.davidcryer.trumpquotes.android.framework.threadscheduling.TaskFactory;
import com.davidcryer.trumpquotes.platformindependent.model.quotes.store.QuoteStore;

public class UpdateQuoteAsJudgedTask extends Task<UpdateQuoteAsJudgedTask.RequestValues, Void> {
    private final QuoteStore quoteStore;

    private UpdateQuoteAsJudgedTask(RequestValues requestValues, Callback<Void> callback, QuoteStore quoteStore) {
        super(requestValues, callback);
        this.quoteStore = quoteStore;
    }

    @Override
    protected void doTask(RequestValues requestValues) {
        if (quoteStore.updateQuoteAsJudged(requestValues.quoteId)) {
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

    static class Factory implements TaskFactory<UpdateQuoteAsJudgedTask.RequestValues, Void> {
        private final QuoteStore quoteStore;

        Factory(QuoteStore quoteStore) {
            this.quoteStore = quoteStore;
        }

        @Override
        public Task<UpdateQuoteAsJudgedTask.RequestValues, Void> create(UpdateQuoteAsJudgedTask.RequestValues requestValues, Task.Callback<Void> callback) {
            return new UpdateQuoteAsJudgedTask(requestValues, callback, quoteStore);
        }
    }
}
