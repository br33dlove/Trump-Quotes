package com.davidcryer.trumpquotes.android.view;

import android.os.Bundle;

import com.davidcryer.trumpquotes.android.view.viewmodels.QuizAndroidViewModel;
import com.davidcryer.trumpquotes.android.view.viewmodels.QuizAndroidViewModelFactory;
import com.davidcryer.trumpquotes.android.view.ui.QuizAndroidView;
import com.davidcryer.trumpquotes.android.view.viewmodels.models.AndroidViewQuestion;
import com.davidcryer.trumpquotes.platformindependent.presenter.presenters.QuizPresenterFactory;
import com.davidcryer.trumpquotes.platformindependent.view.QuizView;
import com.davidcryer.trumpquotes.platformindependent.view.viewmodels.QuizViewModel;

public class QuizViewWrapper extends ViewWrapper<QuizAndroidView, QuizAndroidView.EventsListener> {
    private final static String ARG_VIEW_MODEL = QuizViewWrapper.class.getSimpleName();
    private final QuizAndroidViewModel viewModel;
    private final QuizView.EventsListener wrapperEventsListener;

    private QuizViewWrapper(final QuizPresenterFactory<AndroidViewQuestion> presenterFactory, final QuizAndroidViewModel viewModel) {
        wrapperEventsListener = presenterFactory.create(viewWrapper).eventsListener();
        this.viewModel = viewModel;
    }

    public static ViewWrapper<QuizAndroidView, QuizAndroidView.EventsListener> newInstance(
            final QuizPresenterFactory<AndroidViewQuestion> presenterFactory,
            final QuizAndroidViewModelFactory viewModelFactory
    ) {
        return new QuizViewWrapper(presenterFactory, viewModelFactory.create());
    }

    public static ViewWrapper<QuizAndroidView, QuizAndroidView.EventsListener> retrieveInstanceOrGetNew(
            final Bundle savedState,
            final QuizPresenterFactory<AndroidViewQuestion> presenterFactory,
            final QuizAndroidViewModelFactory viewModelFactory
    ) {
        final QuizAndroidViewModel viewModel = savedState.getParcelable(ARG_VIEW_MODEL);
        return new QuizViewWrapper(presenterFactory, viewModel == null ? viewModelFactory.create() : viewModel);
    }

    private final QuizView<AndroidViewQuestion> viewWrapper = new QuizView<AndroidViewQuestion>() {
        @Override
        public void showScore(int correctAnswerCount, int questionCount) {
            viewModel.showScore(view(), correctAnswerCount, questionCount);
        }

        @Override
        public void showStartNewGameState() {
            viewModel.showStartNewGameState(view());
        }

        @Override
        public void showLoadingState() {
            viewModel.showLoadingState(view());
        }

        @Override
        public void showFailureToStartGameState() {
            viewModel.showFailureToStartGameState(view());
        }

        @Override
        public void showNewGameTutorial() {
            viewModel.showNewGameTutorial(view());
        }

        @Override
        public void dismissNewGameTutorial() {
            viewModel.dismissNewGameTutorial(view());
        }

        @Override
        public void showQuestionState(AndroidViewQuestion question) {
            viewModel.showQuestionState(view(), question);
        }

        @Override
        public void showFinishedGameState() {
            viewModel.showFinishedGameState(view());
        }

        @Override
        public QuizViewModel viewModel() {
            return viewModel;
        }

    };

    @Override
    public QuizAndroidView.EventsListener viewEventsListener() {
        return viewEventsListener;
    }

    private final QuizAndroidView.EventsListener viewEventsListener = new QuizAndroidView.EventsListener() {
        @Override
        public void onViewCreated() {
            wrapperEventsListener.onInitialise();
        }

        @Override
        public void onClickStartNewGame() {
            wrapperEventsListener.onClickStartNewGame();
        }

        @Override
        public void onDismissNewGameTutorial() {
            wrapperEventsListener.onDismissNewGameTutorial();
        }

        @Override
        public void onAnswerOptionA() {
            wrapperEventsListener.onAnswerOptionA();
        }

        @Override
        public void onAnswerOptionB() {
            wrapperEventsListener.onAnswerOptionB();
        }

        @Override
        public void onSaveInstance(final Bundle outState) {
            outState.putParcelable(ARG_VIEW_MODEL, viewModel);
        }
    };

    @Override
    protected void showCurrentState(final QuizAndroidView view, boolean setAllData) {
        viewModel.onto(view, setAllData);
    }

    @Override
    public void releaseResources(final boolean isFinishing) {
        wrapperEventsListener.onReleaseResources(isFinishing);
    }
}
