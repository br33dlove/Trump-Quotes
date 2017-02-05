package com.davidcryer.trumpquotes.platformindependent.view;

import com.davidcryer.trumpquotes.platformindependent.view.models.MvpViewModel;

public interface MvpView<ViewModelType extends MvpViewModel> {
    ViewModelType viewModel();

    interface EventsListener {
        void onReleaseResources();
    }
}
