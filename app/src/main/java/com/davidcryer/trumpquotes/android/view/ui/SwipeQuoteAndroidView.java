package com.davidcryer.trumpquotes.android.view.ui;

import com.davidcryer.trumpquotes.android.view.viewmodels.models.AndroidViewQuestion;

public interface SwipeQuoteAndroidView extends AndroidMvpView {
    void showQuoteState(final AndroidViewQuestion quote);
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
