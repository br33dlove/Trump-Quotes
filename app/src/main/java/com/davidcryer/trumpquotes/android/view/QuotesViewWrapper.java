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
        android.util.Log.v(QuotesViewWrapper.class.getSimpleName(), "newInstance");
        return new QuotesViewWrapper(presenterFactory, viewModelFactory.create());
    }

    public static ViewWrapper<QuotesAndroidView, QuotesAndroidView.EventsListener> retrieveInstanceOrGetNew(
            final Bundle savedState,
            final PresenterFactory presenterFactory,
            final QuotesAndroidViewModelFactory viewModelFactory
    ) {
        final QuotesAndroidViewModel viewModel = savedState.getParcelable(ARG_VIEW_MODEL);
        android.util.Log.v(QuotesViewWrapper.class.getSimpleName(), "retrieveInstanceOrGetNew: " + ((viewModel == null) ? "get new" : "retrieve"));
        return new QuotesViewWrapper(presenterFactory, viewModel == null ? viewModelFactory.create() : viewModel);
    }

    private QuotesView viewWrapper() {
        return new QuotesView() {

            @Override
            public void someScreenChange() {
                android.util.Log.v(QuotesViewWrapper.class.getSimpleName(), "someScreenChange");
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
                android.util.Log.v(QuotesViewWrapper.class.getSimpleName(), "onSomeEvent");
                wrapperEventsListener.onSomeEvent();
            }

            @Override
            public void onSaveInstance(final Bundle outState) {
                android.util.Log.v(QuotesViewWrapper.class.getSimpleName(), "onSaveInstance");
                outState.putParcelable(ARG_VIEW_MODEL, viewModel);
            }
        };
    }

    @Override
    protected void showCurrentState(final QuotesAndroidView view) {
        android.util.Log.v(QuotesViewWrapper.class.getSimpleName(), "showCurrentState");
        viewModel.onto(view);
    }

    @Override
    public void releaseResources() {
        android.util.Log.v(QuotesViewWrapper.class.getSimpleName(), "releaseResources");
        wrapperEventsListener.onReleaseResources();
    }
}
