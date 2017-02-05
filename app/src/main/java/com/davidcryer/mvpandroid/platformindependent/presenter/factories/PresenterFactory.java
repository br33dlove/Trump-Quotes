package com.davidcryer.mvpandroid.platformindependent.presenter.factories;

import com.davidcryer.mvpandroid.platformindependent.presenter.presenters.Presenter;
import com.davidcryer.mvpandroid.platformindependent.view.TemplateView;

public interface PresenterFactory {
    Presenter<TemplateView.EventsListener> createAddressPresenter(final TemplateView viewWrapper);
}
