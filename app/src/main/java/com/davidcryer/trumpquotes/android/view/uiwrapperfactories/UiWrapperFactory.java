package com.davidcryer.trumpquotes.android.view.uiwrapperfactories;

import android.os.Bundle;

import com.davidcryer.trumpquotes.android.view.ui.QuizUi;
import com.example.davidc.uiwrapper.UiWrapper;

public interface UiWrapperFactory {
    UiWrapper<QuizUi, QuizUi.EventsListener> createSwipeQuoteViewWrapper();
    UiWrapper<QuizUi, QuizUi.EventsListener> createSwipeQuoteViewWrapper(final Bundle savedState);
}
