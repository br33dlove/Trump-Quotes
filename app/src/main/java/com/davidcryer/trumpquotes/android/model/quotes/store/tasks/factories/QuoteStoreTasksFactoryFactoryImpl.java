package com.davidcryer.trumpquotes.android.model.quotes.store.tasks.factories;

import com.davidcryer.trumpquotes.android.model.quotes.store.tasks.ClearQuotesTask;
import com.davidcryer.trumpquotes.android.model.quotes.store.tasks.RetrieveJudgedQuotesTask;
import com.davidcryer.trumpquotes.android.model.quotes.store.tasks.RetrieveUnJudgedQuotesTask;
import com.davidcryer.trumpquotes.android.model.quotes.store.tasks.StoreQuotesTask;
import com.davidcryer.trumpquotes.android.model.quotes.store.tasks.UpdateQuoteAsJudgedTask;
import com.davidcryer.trumpquotes.platformindependent.model.quotes.store.QuoteStore;

public class QuoteStoreTasksFactoryFactoryImpl implements QuoteStoreTasksFactoryFactory {
    private final QuoteStore quoteStore;

    public QuoteStoreTasksFactoryFactoryImpl(QuoteStore quoteStore) {
        this.quoteStore = quoteStore;
    }

    @Override
    public ClearQuotesTask.Factory createClearQuotesTask() {
        return new ClearQuotesTask.Factory(quoteStore);
    }

    @Override
    public RetrieveJudgedQuotesTask.Factory createRetrieveJudgedQuotesTask() {
        return new RetrieveJudgedQuotesTask.Factory(quoteStore);
    }

    @Override
    public RetrieveUnJudgedQuotesTask.Factory createRetrieveUnJudgedQuotesTask() {
        return new RetrieveUnJudgedQuotesTask.Factory(quoteStore);
    }

    @Override
    public StoreQuotesTask.Factory createStoreQuotesTask() {
        return new StoreQuotesTask.Factory(quoteStore);
    }

    @Override
    public UpdateQuoteAsJudgedTask.Factory createUpdateQuoteAsJudgedTask() {
        return new UpdateQuoteAsJudgedTask.Factory(quoteStore);
    }
}
