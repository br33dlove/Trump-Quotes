package com.davidcryer.trumpquotes.android.model.quotes.store.tasks.factories;

import com.davidcryer.trumpquotes.android.model.quotes.store.tasks.ClearQuotesTask;
import com.davidcryer.trumpquotes.android.model.quotes.store.tasks.RetrieveJudgedQuotesTask;
import com.davidcryer.trumpquotes.android.model.quotes.store.tasks.RetrieveUnJudgedQuotesTask;
import com.davidcryer.trumpquotes.android.model.quotes.store.tasks.StoreQuotesTask;
import com.davidcryer.trumpquotes.android.model.quotes.store.tasks.UpdateQuoteAsJudgedTask;
import com.davidcryer.trumpquotes.platformindependent.model.quotes.store.QuoteRepository;

public class QuoteStoreTasksFactoryFactoryImpl implements QuoteStoreTasksFactoryFactory {
    private final QuoteRepository quoteRepository;

    public QuoteStoreTasksFactoryFactoryImpl(QuoteRepository quoteRepository) {
        this.quoteRepository = quoteRepository;
    }

    @Override
    public ClearQuotesTask.Factory createClearQuotesTask() {
        return new ClearQuotesTask.Factory(quoteRepository);
    }

    @Override
    public RetrieveJudgedQuotesTask.Factory createRetrieveJudgedQuotesTask() {
        return new RetrieveJudgedQuotesTask.Factory(quoteRepository);
    }

    @Override
    public RetrieveUnJudgedQuotesTask.Factory createRetrieveUnJudgedQuotesTask() {
        return new RetrieveUnJudgedQuotesTask.Factory(quoteRepository);
    }

    @Override
    public StoreQuotesTask.Factory createStoreQuotesTask() {
        return new StoreQuotesTask.Factory(quoteRepository);
    }

    @Override
    public UpdateQuoteAsJudgedTask.Factory createUpdateQuoteAsJudgedTask() {
        return new UpdateQuoteAsJudgedTask.Factory(quoteRepository);
    }
}
