package com.davidcryer.mvpandroid.platformindependent.view;

import com.davidcryer.mvpandroid.platformindependent.view.models.MvpViewModel;

public interface MvpView<ViewModelType extends MvpViewModel> {
    ViewModelType viewModel();

    interface EventsListener {
        void onReleaseResources();
    }
}
