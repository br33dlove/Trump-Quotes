package com.davidcryer.trumpquotes.platformindependent.view;

import com.davidcryer.trumpquotes.platformindependent.view.models.TemplateMvpViewModel;

public interface TemplateView extends MvpView<TemplateMvpViewModel> {
    void someScreenChange();

    interface EventsListener extends MvpView.EventsListener {
        void onSomeEvent();
    }
}
