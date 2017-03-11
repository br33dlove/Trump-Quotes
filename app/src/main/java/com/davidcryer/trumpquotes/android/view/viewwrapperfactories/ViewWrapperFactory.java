package com.davidcryer.trumpquotes.android.view.viewwrapperfactories;

import android.os.Bundle;

import com.davidcryer.trumpquotes.android.view.ViewWrapper;
import com.davidcryer.trumpquotes.android.view.ui.QuizAndroidView;

public interface ViewWrapperFactory {
    ViewWrapper<QuizAndroidView, QuizAndroidView.EventsListener> createSwipeQuoteViewWrapper();
    ViewWrapper<QuizAndroidView, QuizAndroidView.EventsListener> createSwipeQuoteViewWrapper(final Bundle savedState);
}
