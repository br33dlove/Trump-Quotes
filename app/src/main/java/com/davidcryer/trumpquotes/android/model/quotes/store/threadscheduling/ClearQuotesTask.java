package com.davidcryer.trumpquotes.android.model.quotes.store.threadscheduling;

import com.davidcryer.trumpquotes.android.model.threadscheduling.Task;
import com.davidcryer.trumpquotes.platformindependent.model.quotes.store.QuoteStore;

public class ClearQuotesTask extends Task<ClearQuotesTask.RequestValues, Void> {
    private final QuoteStore quoteStore;

    public ClearQuotesTask(RequestValues requestValues, Callback<Void> callback, QuoteStore quoteStore) {
        super(requestValues, callback);
        this.quoteStore = quoteStore;
    }

    @Override
    protected void doTask(RequestValues requestValues) {
        if (quoteStore.clear(requestValues.quoteIds)) {
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
}
