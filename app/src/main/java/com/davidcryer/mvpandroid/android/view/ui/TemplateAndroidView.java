package com.davidcryer.mvpandroid.android.view.ui;

public interface TemplateAndroidView extends AndroidMvpView {
    void someScreenChange();

    interface EventsListener extends AndroidMvpView.EventsListener {
        void onSomeEvent();
    }
}
