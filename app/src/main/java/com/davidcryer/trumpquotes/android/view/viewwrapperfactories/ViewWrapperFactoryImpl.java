package com.davidcryer.trumpquotes.android.view.viewwrapperfactories;

import android.os.Bundle;

import com.davidcryer.trumpquotes.android.view.QuotesViewWrapper;
import com.davidcryer.trumpquotes.android.view.ViewWrapper;
import com.davidcryer.trumpquotes.android.view.viewmodels.factories.QuotesAndroidViewModelFactory;
import com.davidcryer.trumpquotes.android.view.ui.QuotesAndroidView;
import com.davidcryer.trumpquotes.platformindependent.presenter.factories.PresenterFactory;

public class ViewWrapperFactoryImpl implements ViewWrapperFactory {
    private final PresenterFactory presenterFactory;
    private final QuotesAndroidViewModelFactory templateViewModelFactory;

    private ViewWrapperFactoryImpl(
            final PresenterFactory presenterFactory,
            final QuotesAndroidViewModelFactory templateViewModelFactory
    ) {
        this.presenterFactory = presenterFactory;
        this.templateViewModelFactory = templateViewModelFactory;
    }

    public static ViewWrapperFactoryImpl newInstance(
            final PresenterFactory presenterFactory,
            final QuotesAndroidViewModelFactory templateViewModelFactory
    ) {
        return new ViewWrapperFactoryImpl(presenterFactory, templateViewModelFactory);
    }

    @Override
    public ViewWrapper<QuotesAndroidView, QuotesAndroidView.EventsListener> createQuotesViewWrapper() {
        return QuotesViewWrapper.newInstance(presenterFactory, templateViewModelFactory);
    }

    @Override
    public ViewWrapper<QuotesAndroidView, QuotesAndroidView.EventsListener> createQuotesViewWrapper(Bundle savedState) {
        return QuotesViewWrapper.retrieveInstanceOrGetNew(savedState, presenterFactory, templateViewModelFactory);
    }
}
