package com.davidcryer.mvpandroid.android.view;

import com.davidcryer.mvpandroid.android.view.ui.AndroidMvpView;

public abstract class ViewWrapper<ViewType extends AndroidMvpView, ViewEventsListenerType extends AndroidMvpView.EventsListener> {
    private ViewType view;

    public void register(final ViewType view) {
        this.view = view;
        showCurrentState(view);
    }

    abstract void showCurrentState(final ViewType view);

    ViewType view() {
        return view;
    }

    public void unregister() {
        this.view = null;
    }

    public abstract void releaseResources();

    public abstract ViewEventsListenerType viewEventsListener();
}
