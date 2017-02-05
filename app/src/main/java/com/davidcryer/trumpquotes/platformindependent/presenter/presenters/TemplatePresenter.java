package com.davidcryer.trumpquotes.platformindependent.presenter.presenters;

import com.davidcryer.trumpquotes.platformindependent.view.TemplateView;

public class TemplatePresenter extends Presenter<TemplateView.EventsListener> {
    private final TemplateView viewWrapper;

    private TemplatePresenter(final TemplateView viewWrapper) {
        this.viewWrapper = viewWrapper;
    }

    public static Presenter<TemplateView.EventsListener> newInstance(final TemplateView viewWrapper) {
        return new TemplatePresenter(viewWrapper);
    }

    @Override
    public TemplateView.EventsListener eventsListener() {
        return new TemplateView.EventsListener() {

            @Override
            public void onSomeEvent() {
                android.util.Log.v(TemplatePresenter.class.getSimpleName(), "onSomeEvent");
                viewWrapper.someScreenChange();
            }

            @Override
            public void onReleaseResources() {
                android.util.Log.v(TemplatePresenter.class.getSimpleName(), "onReleaseResources");
            }
        };
    }
}
