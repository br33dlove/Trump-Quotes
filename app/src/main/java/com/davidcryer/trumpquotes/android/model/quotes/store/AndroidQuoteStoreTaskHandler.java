package com.davidcryer.trumpquotes.android.model.quotes.store;

import com.davidcryer.trumpquotes.android.model.quotes.store.tasks.ClearQuotesTask;
import com.davidcryer.trumpquotes.android.model.quotes.store.tasks.QuoteStoreTasksFactoryFactory;
import com.davidcryer.trumpquotes.android.model.quotes.store.tasks.RetrieveJudgedQuotesTask;
import com.davidcryer.trumpquotes.android.model.quotes.store.tasks.RetrieveUnJudgedQuotesTask;
import com.davidcryer.trumpquotes.android.model.quotes.store.tasks.StoreQuotesTask;
import com.davidcryer.trumpquotes.android.model.quotes.store.tasks.UpdateQuoteAsJudgedTask;
import com.davidcryer.trumpquotes.android.framework.tasks.Task;
import com.davidcryer.trumpquotes.android.framework.tasks.TaskHandler;
import com.davidcryer.trumpquotes.platformindependent.model.quotes.Quote;
import com.davidcryer.trumpquotes.platformindependent.model.quotes.store.QuoteStoreHandler;

import java.util.ArrayList;
import java.util.Arrays;

public class AndroidQuoteStoreTaskHandler implements QuoteStoreHandler {
    private final QuoteStoreTasksFactoryFactory quoteStoreTasksFactoryFactory;
    private final TaskHandler readTaskHandler;
    private final TaskHandler writeTaskHandler;

    public AndroidQuoteStoreTaskHandler(QuoteStoreTasksFactoryFactory quoteStoreTasksFactoryFactory, TaskHandler readTaskHandler, TaskHandler writeTaskHandler) {
        this.quoteStoreTasksFactoryFactory = quoteStoreTasksFactoryFactory;
        this.readTaskHandler = readTaskHandler;
        this.writeTaskHandler = writeTaskHandler;
    }

    @Override
    public void store(final Quote... quotes) {
        writeTaskHandler.executeTask(quoteStoreTasksFactoryFactory.createStoreQuotesTask(), new StoreQuotesTask.RequestValues(quotes), null);
    }

    @Override
    public void clear(String... quoteIds) {
        writeTaskHandler.executeTask(quoteStoreTasksFactoryFactory.createClearQuotesTask(), new ClearQuotesTask.RequestValues(quoteIds), null);
    }

    @Override
    public void retrieveJudgedQuotes(final RetrieveCallback callback) {
        readTaskHandler.executeTask(quoteStoreTasksFactoryFactory.createRetrieveJudgedQuotesTask(), null, new Task.Callback<RetrieveJudgedQuotesTask.ResponseValues>() {
            @Override
            public void onSuccess(RetrieveJudgedQuotesTask.ResponseValues response) {
                callback.onReturn(Arrays.asList(response.quotes()));
            }

            @Override
            public void onError() {
                callback.onReturn(new ArrayList<Quote>());
            }
        });
    }

    @Override
    public void retrieveUnJudgedQuotes(final RetrieveCallback callback) {
        readTaskHandler.executeTask(quoteStoreTasksFactoryFactory.createRetrieveUnJudgedQuotesTask(), null, new Task.Callback<RetrieveUnJudgedQuotesTask.ResponseValues>() {
            @Override
            public void onSuccess(RetrieveUnJudgedQuotesTask.ResponseValues response) {
                callback.onReturn(Arrays.asList(response.quotes()));
            }

            @Override
            public void onError() {
                callback.onReturn(new ArrayList<Quote>());
            }
        });
    }

    @Override
    public void updateQuoteAsJudged(String quoteId) {
        writeTaskHandler.executeTask(quoteStoreTasksFactoryFactory.createUpdateQuoteAsJudgedTask(), new UpdateQuoteAsJudgedTask.RequestValues(quoteId), null);
    }
}
