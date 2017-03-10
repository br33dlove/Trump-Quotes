package com.davidcryer.trumpquotes.android.view;

import android.os.Bundle;

import com.davidcryer.trumpquotes.android.view.viewmodels.SwipeQuoteAndroidViewModel;
import com.davidcryer.trumpquotes.android.view.viewmodels.SwipeQuoteAndroidViewModelFactory;
import com.davidcryer.trumpquotes.android.view.ui.SwipeQuoteAndroidView;
import com.davidcryer.trumpquotes.android.view.viewmodels.models.AndroidViewQuote;
import com.davidcryer.trumpquotes.platformindependent.presenter.presenters.SwipeQuotePresenterFactory;
import com.davidcryer.trumpquotes.platformindependent.view.SwipeQuoteView;
import com.davidcryer.trumpquotes.platformindependent.view.viewmodels.SwipeQuoteMvpViewModel;

public class SwipeQuoteViewWrapper extends ViewWrapper<SwipeQuoteAndroidView, SwipeQuoteAndroidView.EventsListener> {
    private final static String ARG_VIEW_MODEL = SwipeQuoteViewWrapper.class.getSimpleName();
    private final SwipeQuoteAndroidViewModel viewModel;
    private final SwipeQuoteView.EventsListener wrapperEventsListener;

    private SwipeQuoteViewWrapper(final SwipeQuotePresenterFactory<AndroidViewQuote> presenterFactory, final SwipeQuoteAndroidViewModel viewModel) {
        wrapperEventsListener = presenterFactory.create(viewWrapper).eventsListener();
        this.viewModel = viewModel;
    }

    public static ViewWrapper<SwipeQuoteAndroidView, SwipeQuoteAndroidView.EventsListener> newInstance(
            final SwipeQuotePresenterFactory<AndroidViewQuote> presenterFactory,
            final SwipeQuoteAndroidViewModelFactory viewModelFactory
    ) {
        return new SwipeQuoteViewWrapper(presenterFactory, viewModelFactory.create());
    }

    public static ViewWrapper<SwipeQuoteAndroidView, SwipeQuoteAndroidView.EventsListener> retrieveInstanceOrGetNew(
            final Bundle savedState,
            final SwipeQuotePresenterFactory<AndroidViewQuote> presenterFactory,
            final SwipeQuoteAndroidViewModelFactory viewModelFactory
    ) {
        final SwipeQuoteAndroidViewModel viewModel = savedState.getParcelable(ARG_VIEW_MODEL);
        return new SwipeQuoteViewWrapper(presenterFactory, viewModel == null ? viewModelFactory.create() : viewModel);
    }

    private final SwipeQuoteView<AndroidViewQuote> viewWrapper = new SwipeQuoteView<AndroidViewQuote>() {

        @Override
        public void showQuoteState(AndroidViewQuote quote) {
            viewModel.showQuoteState(view(), quote);
        }

        @Override
        public void showLoadingQuotesState() {
            viewModel.showLoadingQuotesState(view());
        }

        @Override
        public void showFailureToGetQuotesState() {
            viewModel.showFailureToGetQuotesState(view());
        }

        @Override
        public SwipeQuoteMvpViewModel<AndroidViewQuote> viewModel() {
            return viewModel;
        }

        @Override
        public void showScore(int correctAnswersCount, int questionsCount) {
            viewModel.showScore(view(), correctAnswersCount, questionsCount);
        }
    };

    @Override
    public SwipeQuoteAndroidView.EventsListener viewEventsListener() {
        return viewEventsListener;
    }

    private final SwipeQuoteAndroidView.EventsListener viewEventsListener = new SwipeQuoteAndroidView.EventsListener() {

        @Override
        public void onViewCreated() {
            wrapperEventsListener.onStartGame();
        }

        @Override
        public void onRetryQuotesRequest() {
            wrapperEventsListener.onRetryStartGame();
        }

        @Override
        public void onQuoteSwipedLeft() {
            wrapperEventsListener.onQuoteSwipedLeft();
        }

        @Override
        public void onQuoteSwipedRight() {
            wrapperEventsListener.onQuoteSwipedRight();
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
