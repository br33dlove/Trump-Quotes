package com.davidcryer.trumpquotes.android.view.uiwrapperfactories;

import android.os.Bundle;

import com.davidc.uiwrapper.UiWrapper;
import com.davidcryer.trumpquotes.android.presenter.presenters.PresenterFactoryFactory;
import com.davidcryer.trumpquotes.android.view.QuizUiWrapper;
import com.davidcryer.trumpquotes.android.view.uimodels.QuizUiModel;
import com.davidcryer.trumpquotes.android.view.uimodels.QuizUiModelFactory;
import com.davidcryer.trumpquotes.android.view.ui.QuizUi;

public class UiWrapperFactoryImpl implements UiWrapperFactory {
    private final PresenterFactoryFactory presenterFactoryFactory;
    private final QuizUiModelFactory quizUiModelFactory;

    public UiWrapperFactoryImpl(final PresenterFactoryFactory presenterFactoryFactory, final QuizUiModelFactory quizUiModelFactory) {
        this.presenterFactoryFactory = presenterFactoryFactory;
        this.quizUiModelFactory = quizUiModelFactory;
    }

    @Override
    public UiWrapper<QuizUi, QuizUi.Listener, QuizUiModel> createSwipeQuoteViewWrapper() {
        return QuizUiWrapper.newInstance(presenterFactoryFactory.createQuizPresenterFactory(), quizUiModelFactory);
    }

    @Override
    public UiWrapper<QuizUi, QuizUi.Listener, QuizUiModel> createSwipeQuoteViewWrapper(Bundle savedState) {
        return QuizUiWrapper.retrieveInstanceOrGetNew(savedState, presenterFactoryFactory.createQuizPresenterFactory(), quizUiModelFactory);
    }
}
