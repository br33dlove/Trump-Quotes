package com.davidcryer.trumpquotes.android.view.viewwrapperfactories;

import android.os.Bundle;

import com.davidcryer.trumpquotes.android.presenter.presenters.factories.PresenterFactoryFactory;
import com.davidcryer.trumpquotes.android.view.SwipeQuoteViewWrapper;
import com.davidcryer.trumpquotes.android.view.ViewWrapper;
import com.davidcryer.trumpquotes.android.view.viewmodels.factories.SwipeQuoteAndroidViewModelFactory;
import com.davidcryer.trumpquotes.android.view.ui.SwipeQuoteAndroidView;

public class ViewWrapperFactoryImpl implements ViewWrapperFactory {
    private final PresenterFactoryFactory presenterFactoryFactory;
    private final SwipeQuoteAndroidViewModelFactory templateViewModelFactory;

    public ViewWrapperFactoryImpl(final PresenterFactoryFactory presenterFactoryFactory, final SwipeQuoteAndroidViewModelFactory templateViewModelFactory) {
        this.presenterFactoryFactory = presenterFactoryFactory;
        this.templateViewModelFactory = templateViewModelFactory;
    }

    @Override
    public ViewWrapper<SwipeQuoteAndroidView, SwipeQuoteAndroidView.EventsListener> createSwipeQuoteViewWrapper() {
        return SwipeQuoteViewWrapper.newInstance(presenterFactoryFactory.createSwipeQuotePresenterFactory(), templateViewModelFactory);
    }

    @Override
    public ViewWrapper<SwipeQuoteAndroidView, SwipeQuoteAndroidView.EventsListener> createSwipeQuoteViewWrapper(Bundle savedState) {
        return SwipeQuoteViewWrapper.retrieveInstanceOrGetNew(savedState, presenterFactoryFactory.createSwipeQuotePresenterFactory(), templateViewModelFactory);
    }
}
