package com.davidcryer.trumpquotes.android.view.viewwrapperfactories;

import android.os.Bundle;

import com.davidcryer.trumpquotes.android.presenter.factories.PresenterFactoryFactory;
import com.davidcryer.trumpquotes.android.view.QuotesViewWrapper;
import com.davidcryer.trumpquotes.android.view.ViewWrapper;
import com.davidcryer.trumpquotes.android.view.viewmodels.factories.QuotesAndroidViewModelFactory;
import com.davidcryer.trumpquotes.android.view.ui.QuotesAndroidView;

public class ViewWrapperFactoryImpl implements ViewWrapperFactory {
    private final PresenterFactoryFactory presenterFactoryFactory;
    private final QuotesAndroidViewModelFactory templateViewModelFactory;

    public ViewWrapperFactoryImpl(final PresenterFactoryFactory presenterFactoryFactory, final QuotesAndroidViewModelFactory templateViewModelFactory) {
        this.presenterFactoryFactory = presenterFactoryFactory;
        this.templateViewModelFactory = templateViewModelFactory;
    }

    @Override
    public ViewWrapper<QuotesAndroidView, QuotesAndroidView.EventsListener> createQuotesViewWrapper() {
        return QuotesViewWrapper.newInstance(presenterFactoryFactory.createQuotesPresenterFactory(), templateViewModelFactory);
    }

    @Override
    public ViewWrapper<QuotesAndroidView, QuotesAndroidView.EventsListener> createQuotesViewWrapper(Bundle savedState) {
        return QuotesViewWrapper.retrieveInstanceOrGetNew(savedState, presenterFactoryFactory.createQuotesPresenterFactory(), templateViewModelFactory);
    }
}
