package com.davidcryer.trumpquotes.android.model.repository.quotes;

import com.davidcryer.trumpquotes.android.model.repository.quotes.tasks.ClearQuotesTask;
import com.davidcryer.trumpquotes.android.model.repository.quotes.tasks.factories.QuoteStoreTasksFactoryFactory;
import com.davidcryer.trumpquotes.android.model.repository.quotes.tasks.RetrieveJudgedQuotesTask;
import com.davidcryer.trumpquotes.android.model.repository.quotes.tasks.RetrieveUnJudgedQuotesTask;
import com.davidcryer.trumpquotes.android.model.repository.quotes.tasks.StoreQuotesTask;
import com.davidcryer.trumpquotes.android.model.repository.quotes.tasks.UpdateQuoteAsJudgedTask;
import com.davidcryer.trumpquotes.android.framework.tasks.Task;
import com.davidcryer.trumpquotes.android.framework.tasks.TaskHandler;
import com.davidcryer.trumpquotes.platformindependent.javahelpers.ListHelper;
import com.davidcryer.trumpquotes.platformindependent.model.quotes.Quote;
import com.davidcryer.trumpquotes.platformindependent.model.repository.quotes.QuoteRepositoryHandler;

import java.util.ArrayList;

class AndroidQuoteRepositoryTaskHandler implements QuoteRepositoryHandler {
    private final QuoteStoreTasksFactoryFactory quoteStoreTasksFactoryFactory;
    private final TaskHandler readTaskHandler;
    private final TaskHandler writeTaskHandler;

    AndroidQuoteRepositoryTaskHandler(QuoteStoreTasksFactoryFactory quoteStoreTasksFactoryFactory, TaskHandler readTaskHandler, TaskHandler writeTaskHandler) {
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
                callback.onReturn(ListHelper.asArrayList(response.quotes()));
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
                callback.onReturn(ListHelper.asArrayList(response.quotes()));
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
