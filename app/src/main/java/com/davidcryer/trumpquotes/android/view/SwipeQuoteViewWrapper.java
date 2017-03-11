package com.davidcryer.trumpquotes.android.view;

import android.os.Bundle;

import com.davidcryer.trumpquotes.android.view.viewmodels.SwipeQuoteAndroidViewModel;
import com.davidcryer.trumpquotes.android.view.viewmodels.SwipeQuoteAndroidViewModelFactory;
import com.davidcryer.trumpquotes.android.view.ui.SwipeQuoteAndroidView;
import com.davidcryer.trumpquotes.android.view.viewmodels.models.AndroidViewQuestion;
import com.davidcryer.trumpquotes.platformindependent.presenter.presenters.SwipeQuotePresenterFactory;
import com.davidcryer.trumpquotes.platformindependent.view.SwipeQuestionView;
import com.davidcryer.trumpquotes.platformindependent.view.viewmodels.SwipeQuoteMvpViewModel;

public class SwipeQuoteViewWrapper extends ViewWrapper<SwipeQuoteAndroidView, SwipeQuoteAndroidView.EventsListener> {
    private final static String ARG_VIEW_MODEL = SwipeQuoteViewWrapper.class.getSimpleName();
    private final SwipeQuoteAndroidViewModel viewModel;
    private final SwipeQuestionView.EventsListener wrapperEventsListener;

    private SwipeQuoteViewWrapper(final SwipeQuotePresenterFactory<AndroidViewQuestion> presenterFactory, final SwipeQuoteAndroidViewModel viewModel) {
        wrapperEventsListener = presenterFactory.create(viewWrapper).eventsListener();
        this.viewModel = viewModel;
    }

    public static ViewWrapper<SwipeQuoteAndroidView, SwipeQuoteAndroidView.EventsListener> newInstance(
            final SwipeQuotePresenterFactory<AndroidViewQuestion> presenterFactory,
            final SwipeQuoteAndroidViewModelFactory viewModelFactory
    ) {
        return new SwipeQuoteViewWrapper(presenterFactory, viewModelFactory.create());
    }

    public static ViewWrapper<SwipeQuoteAndroidView, SwipeQuoteAndroidView.EventsListener> retrieveInstanceOrGetNew(
            final Bundle savedState,
            final SwipeQuotePresenterFactory<AndroidViewQuestion> presenterFactory,
            final SwipeQuoteAndroidViewModelFactory viewModelFactory
    ) {
        final SwipeQuoteAndroidViewModel viewModel = savedState.getParcelable(ARG_VIEW_MODEL);
        return new SwipeQuoteViewWrapper(presenterFactory, viewModel == null ? viewModelFactory.create() : viewModel);
    }

    private final SwipeQuestionView<AndroidViewQuestion> viewWrapper = new SwipeQuestionView<AndroidViewQuestion>() {
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
        public SwipeQuoteMvpViewModel viewModel() {
            return viewModel;
        }

    };

    @Override
    public SwipeQuoteAndroidView.EventsListener viewEventsListener() {
        return viewEventsListener;
    }

    private final SwipeQuoteAndroidView.EventsListener viewEventsListener = new SwipeQuoteAndroidView.EventsListener() {
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
    protected void showCurrentState(final SwipeQuoteAndroidView view, boolean setAllData) {
        viewModel.onto(view, setAllData);
    }

    @Override
    public void releaseResources(final boolean isFinishing) {
        wrapperEventsListener.onReleaseResources(isFinishing);
    }
}
