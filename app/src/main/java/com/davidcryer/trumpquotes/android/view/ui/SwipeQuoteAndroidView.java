package com.davidcryer.trumpquotes.android.view.ui;

import com.davidcryer.trumpquotes.android.view.viewmodels.models.AndroidViewQuote;

import java.util.List;

public interface SwipeQuoteAndroidView extends AndroidMvpView {
    void showFailureToGetQuote();
    void showLoadingQuote();
    void hideLoadingQuote();
    void showQuote(final AndroidViewQuote quote);

    interface EventsListener extends AndroidMvpView.EventsListener {
        void onViewCreated();
        void onRetryQuoteRequestClicked();
        void onQuoteSwipedLeft();
        void onQuoteSwipedRight();
    }
}
