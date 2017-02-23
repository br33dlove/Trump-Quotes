package com.davidcryer.trumpquotes.android.model.repository.quotes.tasks.factories;

import com.davidcryer.trumpquotes.android.model.repository.quotes.tasks.ClearQuotesTask;
import com.davidcryer.trumpquotes.android.model.repository.quotes.tasks.RetrieveJudgedQuotesTask;
import com.davidcryer.trumpquotes.android.model.repository.quotes.tasks.RetrieveUnJudgedQuotesTask;
import com.davidcryer.trumpquotes.android.model.repository.quotes.tasks.StoreQuotesTask;
import com.davidcryer.trumpquotes.android.model.repository.quotes.tasks.UpdateQuoteAsJudgedTask;
import com.davidcryer.trumpquotes.platformindependent.model.repository.quotes.QuoteRepository;

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
