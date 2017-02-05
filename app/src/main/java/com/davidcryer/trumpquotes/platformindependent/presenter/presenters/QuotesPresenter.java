package com.davidcryer.trumpquotes.platformindependent.presenter.presenters;

import com.davidcryer.trumpquotes.platformindependent.view.QuotesView;

public class QuotesPresenter extends Presenter<QuotesView.EventsListener> {
    private final QuotesView viewWrapper;

    private QuotesPresenter(final QuotesView viewWrapper) {
        this.viewWrapper = viewWrapper;
    }

    public static Presenter<QuotesView.EventsListener> newInstance(final QuotesView viewWrapper) {
        return new QuotesPresenter(viewWrapper);
    }

    @Override
    public QuotesView.EventsListener eventsListener() {
        return new QuotesView.EventsListener() {

            @Override
            public void onSomeEvent() {
                viewWrapper.someScreenChange();
            }

            @Override
            public void onReleaseResources() {
            }
        };
    }
}
