package com.davidcryer.trumpquotes.android.view;

import android.os.Bundle;

import com.davidcryer.trumpquotes.android.view.viewmodels.SwipeQuoteAndroidViewModel;
import com.davidcryer.trumpquotes.android.view.viewmodels.factories.QuotesAndroidViewModelFactory;
import com.davidcryer.trumpquotes.android.view.ui.SwipeQuoteAndroidView;
import com.davidcryer.trumpquotes.android.view.viewmodels.models.AndroidViewQuote;
import com.davidcryer.trumpquotes.platformindependent.presenter.presenters.factories.QuotePresenterFactory;
import com.davidcryer.trumpquotes.platformindependent.view.SwipeQuoteView;
import com.davidcryer.trumpquotes.platformindependent.view.viewmodels.SwipeQuoteMvpViewModel;

import java.util.List;

public class SwipeQuoteViewWrapper extends ViewWrapper<SwipeQuoteAndroidView, SwipeQuoteAndroidView.EventsListener> {
    private final static String ARG_VIEW_MODEL = SwipeQuoteViewWrapper.class.getSimpleName();
    private final SwipeQuoteAndroidViewModel viewModel;
    private final SwipeQuoteView.EventsListener wrapperEventsListener;

    private SwipeQuoteViewWrapper(final QuotePresenterFactory<AndroidViewQuote> presenterFactory, final SwipeQuoteAndroidViewModel viewModel) {
        wrapperEventsListener = presenterFactory.create(viewWrapper).eventsListener();
        this.viewModel = viewModel;
    }

    public static ViewWrapper<SwipeQuoteAndroidView, SwipeQuoteAndroidView.EventsListener> newInstance(
            final QuotePresenterFactory<AndroidViewQuote> presenterFactory,
            final QuotesAndroidViewModelFactory viewModelFactory
    ) {
        return new SwipeQuoteViewWrapper(presenterFactory, viewModelFactory.create());
    }

    public static ViewWrapper<SwipeQuoteAndroidView, SwipeQuoteAndroidView.EventsListener> retrieveInstanceOrGetNew(
            final Bundle savedState,
            final QuotePresenterFactory<AndroidViewQuote> presenterFactory,
            final QuotesAndroidViewModelFactory viewModelFactory
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
        public void addNewQuoteToHistory(AndroidViewQuote quote) {
            viewModel.addNewQuoteToHistory(view(), quote);
        }

        @Override
        public void showQuoteHistory(List<AndroidViewQuote> quotes) {
            viewModel.showQuoteHistory(view(), quotes);
        }

        @Override
        public void removeQuoteInHistory(AndroidViewQuote quote) {
            viewModel.removeQuoteInHistory(view(), quote);
        }

        @Override
        public void removeAllQuotesInHistory() {
            viewModel.removeAllQuotesInHistory(view());
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
            wrapperEventsListener.onRequestQuoteHistory();
        }

        @Override
        public void onRetryNewQuoteRequestClicked() {
            wrapperEventsListener.onRetryQuoteRequest();
        }

        @Override
        public void onNewQuoteSwipedLeft() {
            wrapperEventsListener.onQuoteSwipedLeft();
        }

        @Override
        public void onNewQuoteSwipedRight() {
            wrapperEventsListener.onQuoteSwipedRight();
        }

        @Override
        public void onDeleteQuoteInHistoryClicked(AndroidViewQuote quote) {
            final int indexOfQuoteInHistory = viewModel.indexOfQuoteInHistory(quote);
            wrapperEventsListener.onDeleteQuoteInHistoryClicked(indexOfQuoteInHistory);
        }

        @Override
        public void onDeleteAllQuotesClicked() {
            wrapperEventsListener.onDeleteAllQuotesClicked();
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
