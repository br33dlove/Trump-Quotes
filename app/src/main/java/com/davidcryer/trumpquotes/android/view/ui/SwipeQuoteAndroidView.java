package com.davidcryer.trumpquotes.android.view.ui;

import com.davidcryer.trumpquotes.android.view.viewmodels.models.AndroidViewQuote;

public interface SwipeQuoteAndroidView extends AndroidMvpView {
    void showQuoteState(final AndroidViewQuote quote);
    void showLoadingQuoteState();
    void showFailureToGetQuoteState();

    interface EventsListener extends AndroidMvpView.EventsListener {
        void onViewCreated();
        void onRetryQuoteRequest();
        void onQuoteSwipedLeft();
        void onQuoteSwipedRight();
    }
}
