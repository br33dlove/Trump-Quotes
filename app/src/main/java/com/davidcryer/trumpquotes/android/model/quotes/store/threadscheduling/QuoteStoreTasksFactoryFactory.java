package com.davidcryer.trumpquotes.android.model.quotes.store.threadscheduling;

public interface QuoteStoreTasksFactoryFactory {
    ClearQuotesTask.Factory createClearQuotesTask();
    RetrieveJudgedQuotesTask.Factory createRetrieveJudgedQuotesTask();
    RetrieveUnJudgedQuotesTask.Factory createRetrieveUnJudgedQuotesTask();
    StoreQuotesTask.Factory createStoreQuotesTask();
    UpdateQuoteAsJudgedTask.Factory createUpdateQuoteAsJudgedTask();
}
