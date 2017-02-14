package com.davidcryer.trumpquotes.android.model.quotes.store.threadscheduling;

import com.davidcryer.trumpquotes.android.model.threadscheduling.Task;
import com.davidcryer.trumpquotes.android.model.threadscheduling.TaskFactory;
import com.davidcryer.trumpquotes.platformindependent.model.quotes.store.QuoteStore;

public class StoreQuotesTaskFactory implements TaskFactory<StoreQuotesTask.RequestValues, Void> {
    private final QuoteStore quoteStore;

    public StoreQuotesTaskFactory(QuoteStore quoteStore) {
        this.quoteStore = quoteStore;
    }

    @Override
    public Task<StoreQuotesTask.RequestValues, Void> create(StoreQuotesTask.RequestValues requestValues, Task.Callback<Void> callback) {
        return new StoreQuotesTask(requestValues, callback, quoteStore);
    }
}
