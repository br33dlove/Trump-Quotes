package com.davidcryer.trumpquotes.android.view.viewwrapperfactories;

import android.os.Bundle;

import com.davidcryer.trumpquotes.android.presenter.presenters.PresenterFactoryFactory;
import com.davidcryer.trumpquotes.android.view.QuizViewWrapper;
import com.davidcryer.trumpquotes.android.view.ViewWrapper;
import com.davidcryer.trumpquotes.android.view.viewmodels.QuizAndroidViewModelFactory;
import com.davidcryer.trumpquotes.android.view.ui.QuizAndroidView;

public class ViewWrapperFactoryImpl implements ViewWrapperFactory {
    private final PresenterFactoryFactory presenterFactoryFactory;
    private final QuizAndroidViewModelFactory templateViewModelFactory;

    public ViewWrapperFactoryImpl(final PresenterFactoryFactory presenterFactoryFactory, final QuizAndroidViewModelFactory templateViewModelFactory) {
        this.presenterFactoryFactory = presenterFactoryFactory;
        this.templateViewModelFactory = templateViewModelFactory;
    }

    @Override
    public ViewWrapper<QuizAndroidView, QuizAndroidView.EventsListener> createSwipeQuoteViewWrapper() {
        return QuizViewWrapper.newInstance(presenterFactoryFactory.createQuizPresenterFactory(), templateViewModelFactory);
    }

    @Override
    public ViewWrapper<QuizAndroidView, QuizAndroidView.EventsListener> createSwipeQuoteViewWrapper(Bundle savedState) {
        return QuizViewWrapper.retrieveInstanceOrGetNew(savedState, presenterFactoryFactory.createQuizPresenterFactory(), templateViewModelFactory);
    }
}
