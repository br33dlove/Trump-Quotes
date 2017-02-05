package com.davidcryer.trumpquotes.platformindependent.presenter.factories;

import com.davidcryer.trumpquotes.platformindependent.presenter.presenters.Presenter;
import com.davidcryer.trumpquotes.platformindependent.view.TemplateView;

public interface PresenterFactory {
    Presenter<TemplateView.EventsListener> createAddressPresenter(final TemplateView viewWrapper);
}
