package com.davidcryer.trumpquotes.android.view.ui;

import com.davidcryer.trumpquotes.android.view.viewmodels.models.AndroidViewQuote;

public interface SwipeQuoteAndroidView extends AndroidMvpView {
    void showQuoteState(final AndroidViewQuote quote);
    void showLoadingQuotesState();
    void showFailureToGetQuotesState();
    void showScore(final int correctAnswerCount, final int questionCount);

    interface EventsListener extends AndroidMvpView.EventsListener {
        void onViewCreated();
        void onRetryQuotesRequest();
        void onQuoteSwipedLeft();
        void onQuoteSwipedRight();
    }
}
