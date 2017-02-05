package com.davidcryer.trumpquotes.android.view;

import com.davidcryer.trumpquotes.android.view.ui.AndroidMvpView;

public abstract class ViewWrapper<ViewType extends AndroidMvpView, ViewEventsListenerType extends AndroidMvpView.EventsListener> {
    private ViewType view;

    public void register(final ViewType view, final boolean setAllData) {
        this.view = view;
        showCurrentState(view, setAllData);
    }

    abstract void showCurrentState(final ViewType view, final boolean setAllData);

    ViewType view() {
        return view;
    }

    public void unregister() {
        this.view = null;
    }

    public abstract void releaseResources();

    public abstract ViewEventsListenerType viewEventsListener();
}
