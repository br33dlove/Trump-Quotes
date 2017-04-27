package com.davidcryer.trumpquotes.android.view;

import android.os.Bundle;

import com.davidc.uiwrapper.UiWrapper;
import com.davidcryer.trumpquotes.android.view.uimodels.QuizUiModel;
import com.davidcryer.trumpquotes.android.view.uimodels.QuizUiModelFactory;
import com.davidcryer.trumpquotes.android.view.ui.QuizUi;
import com.davidcryer.trumpquotes.android.view.uimodels.models.AndroidViewQuestion;
import com.davidcryer.trumpquotes.platformindependent.presenter.presenters.QuizPresenterFactory;
import com.davidcryer.trumpquotes.platformindependent.view.QuizView;
import com.davidcryer.trumpquotes.platformindependent.view.viewmodels.QuizViewModel;

public class QuizUiWrapper extends UiWrapper<QuizUi, QuizUi.Listener, QuizUiModel> {
    private final static String ARG_UI_MODEL = QuizUiWrapper.class.getSimpleName();
    private final QuizView.EventsListener viewEventsListener;

    private QuizUiWrapper(final QuizPresenterFactory<AndroidViewQuestion> presenterFactory, final QuizUiModel uiModel) {
        super(uiModel);
        viewEventsListener = presenterFactory.create(view).eventsListener();
    }

    public static UiWrapper<QuizUi, QuizUi.Listener, QuizUiModel> newInstance(
            final QuizPresenterFactory<AndroidViewQuestion> presenterFactory,
            final QuizUiModelFactory uiModelFactory
    ) {
        return new QuizUiWrapper(presenterFactory, uiModelFactory.create());
    }

    public static UiWrapper<QuizUi, QuizUi.Listener, QuizUiModel> retrieveInstanceOrGetNew(
            final Bundle savedState,
            final QuizPresenterFactory<AndroidViewQuestion> presenterFactory,
            final QuizUiModelFactory uiModelFactory
    ) {
        final QuizUiModel viewModel = savedState.getParcelable(ARG_UI_MODEL);
        return new QuizUiWrapper(presenterFactory, viewModel == null ? uiModelFactory.create() : viewModel);
    }

    private final QuizView<AndroidViewQuestion> view = new QuizView<AndroidViewQuestion>() {
        @Override
        public void showScore(int correctAnswerCount, int questionCount) {
            uiModel().showScore(ui(), correctAnswerCount, questionCount);
        }

        @Override
        public void showStartNewGame() {
            uiModel().showStartNewGame(ui());
        }

        @Override
        public void showLoadingGame() {
            uiModel().showLoadingGame(ui());
        }

        @Override
        public void showFailureToLoadGame() {
            uiModel().showFailureToLoadGame(ui());
        }

        @Override
        public void showQuestion(AndroidViewQuestion question) {
            uiModel().showQuestion(ui(), question);
        }

        @Override
        public void showFinishedGame() {
            uiModel().showFinishedGameState(ui());
        }

        @Override
        public QuizViewModel viewModel() {
            return uiModel();
        }

    };

    @Override
    protected QuizUi.Listener uiListener() {
        return uiListener;
    }

    private final QuizUi.Listener uiListener = new QuizUi.Listener() {
        @Override
        public void onViewCreated() {
            viewEventsListener.onInitialise();
        }

        @Override
        public void onClickStartNewGame() {
            viewEventsListener.onClickStartNewGame();
        }

        @Override
        public void onAnswerOptionA() {
            viewEventsListener.onAnswerOptionA();
        }

        @Override
        public void onAnswerOptionB() {
            viewEventsListener.onAnswerOptionB();
        }
    };
}
