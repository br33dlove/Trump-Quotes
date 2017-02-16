package com.davidcryer.trumpquotes.android.view;

import android.os.Bundle;

import com.davidcryer.trumpquotes.android.view.viewmodels.SwipeQuoteAndroidViewModel;
import com.davidcryer.trumpquotes.android.view.viewmodels.factories.SwipeQuoteAndroidViewModelFactory;
import com.davidcryer.trumpquotes.android.view.ui.SwipeQuoteAndroidView;
import com.davidcryer.trumpquotes.android.view.viewmodels.models.AndroidViewQuote;
import com.davidcryer.trumpquotes.platformindependent.presenter.presenters.factories.SwipeQuotePresenterFactory;
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
        public void showFailureToGetQuote() {
            viewModel.showFailureToGetNewQuote(view());
        }

        @Override
        public void showLoadingQuote() {
            viewModel.showLoadingNewQuote(view());
        }

        @Override
        public void hideLoadingQuote() {
            viewModel.hideLoadingNewQuote(view());
        }

        @Override
        public void showNewQuote(AndroidViewQuote quote) {
            viewModel.showNewQuote(view(), quote);
        }

        @Override
        public SwipeQuoteMvpViewModel<AndroidViewQuote> viewModel() {
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
            wrapperEventsListener.onRequestFirstQuote();
        }

        @Override
        public void onRetryQuoteRequestClicked() {
            wrapperEventsListener.onRetryQuoteRequest();
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
