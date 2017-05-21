package com.davidcryer.trumpquotes.android.presenter.uiwrapperfactories;

import android.os.Bundle;

import com.davidc.uiwrapper.UiWrapper;
import com.davidcryer.trumpquotes.android.presenter.quiz.QuizUiWrapper;
import com.davidcryer.trumpquotes.android.view.ui.QuizUi;
import com.davidcryer.trumpquotes.android.presenter.quiz.uimodels.QuizUiModel;
import com.davidcryer.trumpquotes.platformindependent.model.interactors.InitialiseGameInteractor;
import com.davidcryer.trumpquotes.platformindependent.model.interactors.InteractorFactory;
import com.davidcryer.trumpquotes.platformindependent.model.interactors.LoadGameInteractor;

public class UiWrapperFactory {
    private final InteractorFactory interactorFactory;

    public UiWrapperFactory(final InteractorFactory interactorFactory) {
        this.interactorFactory = interactorFactory;
    }

    public UiWrapper<QuizUi, QuizUi.Listener, QuizUiModel> createSwipeQuoteViewWrapper() {
        return QuizUiWrapper.newInstance(loadGameInteractor(), initialiseGameInteractor());
    }

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
