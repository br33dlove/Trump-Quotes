package com.davidcryer.mvpandroid.platformindependent.presenter.presenters;

import com.davidcryer.mvpandroid.platformindependent.view.MvpView;

public abstract class Presenter<EventsListenerType extends MvpView.EventsListener> {

    public abstract EventsListenerType eventsListener();
}
