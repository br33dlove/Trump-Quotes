package com.davidcryer.trumpquotes.android.model.quotes.store;

import com.davidcryer.trumpquotes.android.model.quotes.store.threadscheduling.ClearQuotesTask;
import com.davidcryer.trumpquotes.android.model.quotes.store.threadscheduling.QuoteStoreTasksFactoryFactory;
import com.davidcryer.trumpquotes.android.model.quotes.store.threadscheduling.RetrieveJudgedQuotesTask;
import com.davidcryer.trumpquotes.android.model.quotes.store.threadscheduling.RetrieveUnJudgedQuotesTask;
import com.davidcryer.trumpquotes.android.model.quotes.store.threadscheduling.StoreQuotesTask;
import com.davidcryer.trumpquotes.android.model.quotes.store.threadscheduling.UpdateQuoteAsJudgedTask;
import com.davidcryer.trumpquotes.android.model.threadscheduling.Task;
import com.davidcryer.trumpquotes.android.model.threadscheduling.TaskHandler;
import com.davidcryer.trumpquotes.platformindependent.model.quotes.Quote;
import com.davidcryer.trumpquotes.platformindependent.model.quotes.store.QuoteStoreHandler;

import java.util.ArrayList;
import java.util.Arrays;

public class AndroidHandlerQuoteStoreHandler implements QuoteStoreHandler {
    private final QuoteStoreTasksFactoryFactory quoteStoreTasksFactoryFactory;
    private final TaskHandler readTaskHandler;
    private final TaskHandler writeTaskHandler;

    public AndroidHandlerQuoteStoreHandler(QuoteStoreTasksFactoryFactory quoteStoreTasksFactoryFactory, TaskHandler readTaskHandler, TaskHandler writeTaskHandler) {
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
