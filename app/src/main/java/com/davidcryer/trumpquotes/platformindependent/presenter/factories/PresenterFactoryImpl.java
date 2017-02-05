package com.davidcryer.trumpquotes.platformindependent.presenter.factories;

import com.davidcryer.trumpquotes.platformindependent.presenter.presenters.Presenter;
import com.davidcryer.trumpquotes.platformindependent.presenter.presenters.TemplatePresenter;
import com.davidcryer.trumpquotes.platformindependent.view.TemplateView;

public class PresenterFactoryImpl implements PresenterFactory {

    private PresenterFactoryImpl() {

    }

    public static PresenterFactory newInstance() {
        return new PresenterFactoryImpl();
    }

    @Override
    public Presenter<TemplateView.EventsListener> createAddressPresenter(final TemplateView viewWrapper) {
        return TemplatePresenter.newInstance(viewWrapper);
    }
}
