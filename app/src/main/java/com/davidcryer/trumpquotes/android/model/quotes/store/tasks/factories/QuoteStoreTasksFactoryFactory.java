package com.davidcryer.trumpquotes.android.model.quotes.store.tasks.factories;

import com.davidcryer.trumpquotes.android.model.quotes.store.tasks.ClearQuotesTask;
import com.davidcryer.trumpquotes.android.model.quotes.store.tasks.RetrieveJudgedQuotesTask;
import com.davidcryer.trumpquotes.android.model.quotes.store.tasks.RetrieveUnJudgedQuotesTask;
import com.davidcryer.trumpquotes.android.model.quotes.store.tasks.StoreQuotesTask;
import com.davidcryer.trumpquotes.android.model.quotes.store.tasks.UpdateQuoteAsJudgedTask;

public interface QuoteStoreTasksFactoryFactory {
    ClearQuotesTask.Factory createClearQuotesTask();
    RetrieveJudgedQuotesTask.Factory createRetrieveJudgedQuotesTask();
    RetrieveUnJudgedQuotesTask.Factory createRetrieveUnJudgedQuotesTask();
    StoreQuotesTask.Factory createStoreQuotesTask();
    UpdateQuoteAsJudgedTask.Factory createUpdateQuoteAsJudgedTask();
}
