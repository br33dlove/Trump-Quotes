package com.davidcryer.trumpquotes.android.view.uiwrapperfactories;

import android.os.Bundle;

import com.davidc.uiwrapper.UiWrapper;
import com.davidcryer.trumpquotes.android.view.QuizUiWrapper;
import com.davidcryer.trumpquotes.android.view.ui.QuizUi;
import com.davidcryer.trumpquotes.android.view.uimodels.QuizUiModel;
import com.davidcryer.trumpquotes.platformindependent.model.interactors.InitialiseGameInteractor;
import com.davidcryer.trumpquotes.platformindependent.model.interactors.InteractorFactory;
import com.davidcryer.trumpquotes.platformindependent.model.interactors.LoadGameInteractor;

public class UiWrapperFactoryImpl implements UiWrapperFactory {
    private final InteractorFactory interactorFactory;

    public UiWrapperFactoryImpl(final InteractorFactory interactorFactory) {
        this.interactorFactory = interactorFactory;
    }

    @Override
    public UiWrapper<QuizUi, QuizUi.Listener, QuizUiModel> createSwipeQuoteViewWrapper() {
        return QuizUiWrapper.newInstance(loadGameInteractor(), initialiseGameInteractor());
    }

    @Override
    public UiWrapper<QuizUi, QuizUi.Listener, QuizUiModel> createSwipeQuoteViewWrapper(Bundle savedState) {
        return QuizUiWrapper.retrieveInstanceOrGetNew(savedState, loadGameInteractor(), initialiseGameInteractor());
    }

    private LoadGameInteractor loadGameInteractor() {
        return interactorFactory.createLoadGameInteractor();
    }

    private InitialiseGameInteractor initialiseGameInteractor() {
        return interactorFactory.createInitialiseGameInteractor();
    }
}
