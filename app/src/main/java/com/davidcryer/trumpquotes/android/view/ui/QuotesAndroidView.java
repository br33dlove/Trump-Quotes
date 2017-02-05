package com.davidcryer.trumpquotes.android.view.ui;

public interface QuotesAndroidView extends AndroidMvpView {
    void someScreenChange();

    interface EventsListener extends AndroidMvpView.EventsListener {
        void onSomeEvent();
    }
}
