package com.davidcryer.trumpquotes.android.view;

import android.os.Bundle;

import com.davidcryer.trumpquotes.android.view.viewmodels.QuotesAndroidViewModel;
import com.davidcryer.trumpquotes.android.view.viewmodels.factories.QuotesAndroidViewModelFactory;
import com.davidcryer.trumpquotes.android.view.ui.QuotesAndroidView;
import com.davidcryer.trumpquotes.android.view.viewmodels.models.AndroidViewQuote;
import com.davidcryer.trumpquotes.platformindependent.presenter.factories.PresenterFactory;
import com.davidcryer.trumpquotes.platformindependent.view.QuotesView;
import com.davidcryer.trumpquotes.platformindependent.view.viewmodels.QuotesMvpViewModel;
import com.davidcryer.trumpquotes.platformindependent.view.viewmodels.models.ViewQuote;

import java.util.List;

public class QuotesViewWrapper extends ViewWrapper<QuotesAndroidView, QuotesAndroidView.EventsListener> {
    private final static String ARG_VIEW_MODEL = QuotesViewWrapper.class.getSimpleName();
    private final QuotesAndroidViewModel viewModel;
    private final QuotesView.EventsListener wrapperEventsListener;

    private QuotesViewWrapper(final PresenterFactory presenterFactory, final QuotesAndroidViewModel viewModel) {
        wrapperEventsListener = presenterFactory.createQuotesPresenter(viewWrapper).eventsListener();
        this.viewModel = viewModel;
    }

    public static ViewWrapper<QuotesAndroidView, QuotesAndroidView.EventsListener> newInstance(
            final PresenterFactory presenterFactory,
            final QuotesAndroidViewModelFactory viewModelFactory
    ) {
        return new QuotesViewWrapper(presenterFactory, viewModelFactory.create());
    }

    public static ViewWrapper<QuotesAndroidView, QuotesAndroidView.EventsListener> retrieveInstanceOrGetNew(
            final Bundle savedState,
            final PresenterFactory presenterFactory,
            final QuotesAndroidViewModelFactory viewModelFactory
    ) {
        final QuotesAndroidViewModel viewModel = savedState.getParcelable(ARG_VIEW_MODEL);
        return new QuotesViewWrapper(presenterFactory, viewModel == null ? viewModelFactory.create() : viewModel);
    }

    private final QuotesView<AndroidViewQuote> viewWrapper = new QuotesView<AndroidViewQuote>() {

        @Override
        public void showLoadingNewQuote() {
            viewModel.showLoadingNewQuote(view());
        }

        @Override
        public void hideLoadingNewQuote() {
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
        public QuotesMvpViewModel<AndroidViewQuote> viewModel() {
            return viewModel;
        }
    };

    @Override
    public QuotesAndroidView.EventsListener viewEventsListener() {
        return viewEventsListener;
    }

    private final QuotesAndroidView.EventsListener viewEventsListener = new QuotesAndroidView.EventsListener() {

        @Override
        public void onCreate() {
            wrapperEventsListener.onCreate();
        }

        @Override
        public void onNewQuoteSwipedLeft() {
            wrapperEventsListener.onNewQuoteSwipedLeft();
        }

        @Override
        public void onNewQuoteSwipedRight() {
            wrapperEventsListener.onNewQuoteSwipedRight();
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
    protected void showCurrentState(final QuotesAndroidView view, boolean setAllData) {
        viewModel.onto(view, setAllData);
    }

    @Override
    public void releaseResources() {
        wrapperEventsListener.onReleaseResources();
    }
}
