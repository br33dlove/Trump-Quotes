package com.davidcryer.trumpquotes.android.model.store.quotes.tasks.factories;

import com.davidcryer.trumpquotes.android.model.store.quotes.tasks.ClearQuotesTask;
import com.davidcryer.trumpquotes.android.model.store.quotes.tasks.RetrieveJudgedQuotesTask;
import com.davidcryer.trumpquotes.android.model.store.quotes.tasks.RetrieveUnJudgedQuotesTask;
import com.davidcryer.trumpquotes.android.model.store.quotes.tasks.StoreQuotesTask;
import com.davidcryer.trumpquotes.android.model.store.quotes.tasks.UpdateQuoteAsJudgedTask;

public interface QuoteStoreTasksFactoryFactory {
    ClearQuotesTask.Factory createClearQuotesTask();
    RetrieveJudgedQuotesTask.Factory createRetrieveJudgedQuotesTask();
    RetrieveUnJudgedQuotesTask.Factory createRetrieveUnJudgedQuotesTask();
    StoreQuotesTask.Factory createStoreQuotesTask();
    UpdateQuoteAsJudgedTask.Factory createUpdateQuoteAsJudgedTask();
}
