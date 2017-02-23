package com.davidcryer.trumpquotes.android.model.repository.quotes.tasks.factories;

import com.davidcryer.trumpquotes.android.model.repository.quotes.tasks.ClearQuotesTask;
import com.davidcryer.trumpquotes.android.model.repository.quotes.tasks.RetrieveJudgedQuotesTask;
import com.davidcryer.trumpquotes.android.model.repository.quotes.tasks.RetrieveUnJudgedQuotesTask;
import com.davidcryer.trumpquotes.android.model.repository.quotes.tasks.StoreQuotesTask;
import com.davidcryer.trumpquotes.android.model.repository.quotes.tasks.UpdateQuoteAsJudgedTask;

public interface QuoteStoreTasksFactoryFactory {
    ClearQuotesTask.Factory createClearQuotesTask();
    RetrieveJudgedQuotesTask.Factory createRetrieveJudgedQuotesTask();
    RetrieveUnJudgedQuotesTask.Factory createRetrieveUnJudgedQuotesTask();
    StoreQuotesTask.Factory createStoreQuotesTask();
    UpdateQuoteAsJudgedTask.Factory createUpdateQuoteAsJudgedTask();
}
