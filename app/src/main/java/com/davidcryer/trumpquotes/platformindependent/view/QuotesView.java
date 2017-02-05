package com.davidcryer.trumpquotes.platformindependent.view;

import com.davidcryer.trumpquotes.platformindependent.view.models.QuotesMvpViewModel;

public interface QuotesView extends MvpView<QuotesMvpViewModel> {
    void someScreenChange();

    interface EventsListener extends MvpView.EventsListener {
        void onSomeEvent();
    }
}
