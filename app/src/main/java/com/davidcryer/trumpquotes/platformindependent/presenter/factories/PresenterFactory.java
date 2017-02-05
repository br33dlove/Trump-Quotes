package com.davidcryer.trumpquotes.platformindependent.presenter.factories;

import com.davidcryer.trumpquotes.platformindependent.presenter.presenters.Presenter;
import com.davidcryer.trumpquotes.platformindependent.view.QuotesView;

public interface PresenterFactory {
    Presenter<QuotesView.EventsListener> createAddressPresenter(final QuotesView viewWrapper);
}
