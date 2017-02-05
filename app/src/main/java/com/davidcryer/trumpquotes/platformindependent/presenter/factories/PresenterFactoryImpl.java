package com.davidcryer.trumpquotes.platformindependent.presenter.factories;

import com.davidcryer.trumpquotes.platformindependent.presenter.presenters.Presenter;
import com.davidcryer.trumpquotes.platformindependent.presenter.presenters.QuotesPresenter;
import com.davidcryer.trumpquotes.platformindependent.view.QuotesView;

public class PresenterFactoryImpl implements PresenterFactory {

    private PresenterFactoryImpl() {

    }

    public static PresenterFactory newInstance() {
        return new PresenterFactoryImpl();
    }

    @Override
    public Presenter<QuotesView.EventsListener> createQuotesPresenter(final QuotesView viewWrapper) {
        return QuotesPresenter.newInstance(viewWrapper);
    }
}
