package com.davidcryer.trumpquotes.android.view.ui;

import com.davidcryer.trumpquotes.android.view.viewmodels.models.AndroidViewQuote;

import java.util.List;

public interface SwipeQuoteAndroidView extends AndroidMvpView {
    void showQuote(final AndroidViewQuote quote);
    void showLoadingQuote();
    void hideLoadingQuote();
    void showFailureToGetQuote();
    void hideFailureToGetQuote();

    interface EventsListener extends AndroidMvpView.EventsListener {
        void onViewCreated();
        void onRetryQuoteRequestClicked();
        void onQuoteSwipedLeft();
        void onQuoteSwipedRight();
    }
}
