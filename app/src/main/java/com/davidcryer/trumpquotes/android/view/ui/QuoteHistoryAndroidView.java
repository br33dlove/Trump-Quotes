package com.davidcryer.trumpquotes.android.view.ui;

import com.davidcryer.trumpquotes.android.view.viewmodels.models.AndroidViewQuestion;

import java.util.List;

public interface QuoteHistoryAndroidView extends AndroidMvpView {
    void showQuoteHistory(final List<AndroidViewQuestion> quotes);
    void updateQuoteHistoryForInsert(final int index);
    void updateQuoteHistoryForRemoval(final int index);
    void removeAllQuotesInHistory();

    interface EventsListener extends AndroidMvpView.EventsListener {
        void onViewCreated();
        void onDeleteQuoteInHistoryClicked(final AndroidViewQuestion quote);
        void onDeleteAllQuotesClicked();
    }
}
