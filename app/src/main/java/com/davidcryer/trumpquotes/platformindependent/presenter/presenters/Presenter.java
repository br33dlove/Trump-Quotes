package com.davidcryer.trumpquotes.platformindependent.presenter.presenters;

import com.davidcryer.trumpquotes.platformindependent.view.MvpView;

public abstract class Presenter<EventsListenerType extends MvpView.EventsListener> {

    public abstract EventsListenerType eventsListener();
}
