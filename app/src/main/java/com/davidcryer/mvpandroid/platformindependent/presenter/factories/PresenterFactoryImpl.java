package com.davidcryer.mvpandroid.platformindependent.presenter.factories;

import com.davidcryer.mvpandroid.platformindependent.presenter.presenters.Presenter;
import com.davidcryer.mvpandroid.platformindependent.presenter.presenters.TemplatePresenter;
import com.davidcryer.mvpandroid.platformindependent.view.TemplateView;

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
