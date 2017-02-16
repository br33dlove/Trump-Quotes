package com.davidcryer.trumpquotes.android.view.viewwrapperfactories;

import android.os.Bundle;

import com.davidcryer.trumpquotes.android.view.ViewWrapper;
import com.davidcryer.trumpquotes.android.view.ui.SwipeQuoteAndroidView;

public interface ViewWrapperFactory {
    ViewWrapper<SwipeQuoteAndroidView, SwipeQuoteAndroidView.EventsListener> createSwipeQuoteViewWrapper();
    ViewWrapper<SwipeQuoteAndroidView, SwipeQuoteAndroidView.EventsListener> createSwipeQuoteViewWrapper(final Bundle savedState);
}
