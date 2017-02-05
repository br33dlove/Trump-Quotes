package com.davidcryer.trumpquotes.android.view;

import android.os.Bundle;

import com.davidcryer.trumpquotes.android.view.models.QuotesAndroidViewModel;
import com.davidcryer.trumpquotes.android.view.models.factories.QuotesAndroidViewModelFactory;
import com.davidcryer.trumpquotes.android.view.ui.QuotesAndroidView;
import com.davidcryer.trumpquotes.platformindependent.presenter.factories.PresenterFactory;
import com.davidcryer.trumpquotes.platformindependent.view.QuotesView;
import com.davidcryer.trumpquotes.platformindependent.view.models.QuotesMvpViewModel;

public class QuotesViewWrapper extends ViewWrapper<QuotesAndroidView, QuotesAndroidView.EventsListener> {
    private final static String ARG_VIEW_MODEL = QuotesViewWrapper.class.getSimpleName();
    private final QuotesAndroidViewModel viewModel;
    private final QuotesView.EventsListener wrapperEventsListener;

    private QuotesViewWrapper(final PresenterFactory presenterFactory, final QuotesAndroidViewModel viewModel) {
        wrapperEventsListener = presenterFactory.createQuotesPresenter(viewWrapper()).eventsListener();
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

    private QuotesView viewWrapper() {
        return new QuotesView() {

            @Override
            public void someScreenChange() {
                viewModel.screenChanged(view());
            }

            @Override
            public QuotesMvpViewModel viewModel() {
                return viewModel;
            }
        };
    }

    @Override
    public QuotesAndroidView.EventsListener viewEventsListener() {
        return new QuotesAndroidView.EventsListener() {

            @Override
            public void onSomeEvent() {
                wrapperEventsListener.onSomeEvent();
            }

            @Override
            public void onSaveInstance(final Bundle outState) {
                outState.putParcelable(ARG_VIEW_MODEL, viewModel);
            }
        };
    }

    @Override
    protected void showCurrentState(final QuotesAndroidView view) {
        viewModel.onto(view);
    }

    @Override
    public void releaseResources() {
        wrapperEventsListener.onReleaseResources();
    }
}
