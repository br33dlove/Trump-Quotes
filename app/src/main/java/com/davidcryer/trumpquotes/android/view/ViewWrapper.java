package com.davidcryer.trumpquotes.android.view;

import com.davidcryer.trumpquotes.android.view.ui.AndroidMvpView;

public abstract class ViewWrapper<ViewType extends AndroidMvpView, ViewEventsListenerType extends AndroidMvpView.EventsListener> {
    private ViewType view;

    public ViewEventsListenerType register(final ViewType view) {
        this.view = view;
        showCurrentState(view);
        return viewEventsListener();
    }

    abstract void showCurrentState(final ViewType view);

    ViewType view() {
        return view;
    }

    public void unregister() {
        this.view = null;
    }

    public abstract void releaseResources(final boolean isFinishing);

    abstract ViewEventsListenerType viewEventsListener();
}
