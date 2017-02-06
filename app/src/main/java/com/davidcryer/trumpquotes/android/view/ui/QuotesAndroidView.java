package com.davidcryer.trumpquotes.android.view.ui;

import com.davidcryer.trumpquotes.android.view.viewmodels.models.AndroidViewQuote;

import java.util.List;

public interface QuotesAndroidView extends AndroidMvpView {
    void showFailureToGetNewQuote();
    void showLoadingNewQuote();
    void hideLoadingNewQuote();
    void showNewQuote(final AndroidViewQuote quote);
    void showQuoteHistory(final List<AndroidViewQuote> quotes);
    void updateQuoteHistoryForInsert(final int index);
    void updateQuoteHistoryForRemoval(final int index);
    void removeAllQuotesInHistory();

    interface EventsListener extends AndroidMvpView.EventsListener {
        void onViewCreated();
        void onRetryNewQuoteRequestClicked();
        void onNewQuoteSwipedLeft();
        void onNewQuoteSwipedRight();
        void onDeleteQuoteInHistoryClicked(final AndroidViewQuote quote);
        void onDeleteAllQuotesClicked();
    }
}
