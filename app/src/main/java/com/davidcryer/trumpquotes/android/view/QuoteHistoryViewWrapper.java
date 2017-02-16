package com.davidcryer.trumpquotes.android.view;

import android.os.Bundle;

import com.davidcryer.trumpquotes.android.view.ui.QuoteHistoryAndroidView;
import com.davidcryer.trumpquotes.android.view.viewmodels.QuoteHistoryAndroidViewModel;
import com.davidcryer.trumpquotes.android.view.viewmodels.factories.QuoteHistoryAndroidViewModelFactory;
import com.davidcryer.trumpquotes.android.view.viewmodels.models.AndroidViewQuote;
import com.davidcryer.trumpquotes.platformindependent.presenter.presenters.factories.QuoteHistoryPresenterFactory;
import com.davidcryer.trumpquotes.platformindependent.view.QuoteHistoryView;
import com.davidcryer.trumpquotes.platformindependent.view.viewmodels.QuoteHistoryMvpViewModel;

import java.util.List;

public class QuoteHistoryViewWrapper extends ViewWrapper<QuoteHistoryAndroidView, QuoteHistoryAndroidView.EventsListener> {
    private final static String ARG_VIEW_MODEL = QuoteHistoryViewWrapper.class.getSimpleName();
    private final QuoteHistoryView.EventsListener wrapperEventsListener;
    private final QuoteHistoryAndroidViewModel viewModel;

    public QuoteHistoryViewWrapper(final QuoteHistoryPresenterFactory<AndroidViewQuote> presenterFactory, final QuoteHistoryAndroidViewModel viewModel) {
        wrapperEventsListener = presenterFactory.create(viewWrapper).eventsListener();
        this.viewModel = viewModel;
    }

    public static ViewWrapper<QuoteHistoryAndroidView, QuoteHistoryAndroidView.EventsListener> newInstance(
            final QuoteHistoryPresenterFactory<AndroidViewQuote> presenterFactory,
            final QuoteHistoryAndroidViewModelFactory viewModelFactory
    ) {
        return new QuoteHistoryViewWrapper(presenterFactory, viewModelFactory.create());
    }

    public static ViewWrapper<QuoteHistoryAndroidView, QuoteHistoryAndroidView.EventsListener> retrieveInstanceOrGetNew(
            final Bundle savedState,
            final QuoteHistoryPresenterFactory<AndroidViewQuote> presenterFactory,
            final QuoteHistoryAndroidViewModelFactory viewModelFactory
    ) {
        final QuoteHistoryAndroidViewModel viewModel = savedState.getParcelable(ARG_VIEW_MODEL);
        return new QuoteHistoryViewWrapper(presenterFactory, viewModel == null ? viewModelFactory.create() : viewModel);
    }

    private final QuoteHistoryView<AndroidViewQuote> viewWrapper = new QuoteHistoryView<AndroidViewQuote>() {
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
        public QuoteHistoryMvpViewModel<AndroidViewQuote> viewModel() {
            return viewModel;
        }
    };

    @Override
    void showCurrentState(QuoteHistoryAndroidView view, boolean setAllData) {

    }

    @Override
    public void releaseResources(boolean isFinishing) {

    }

    @Override
    public QuoteHistoryAndroidView.EventsListener viewEventsListener() {
        return new QuoteHistoryAndroidView.EventsListener() {
            @Override
            public void onViewCreated() {
                wrapperEventsListener.onRequestQuoteHistory();
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
            public void onSaveInstance(Bundle outState) {

            }
        };
    }
}
