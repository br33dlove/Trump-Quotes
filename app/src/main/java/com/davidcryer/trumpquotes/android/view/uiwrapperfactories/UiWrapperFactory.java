package com.davidcryer.trumpquotes.android.view.uiwrapperfactories;

import android.os.Bundle;

import com.davidc.uiwrapper.UiWrapper;
import com.davidcryer.trumpquotes.android.view.ui.QuizUi;

public interface UiWrapperFactory {
    UiWrapper<QuizUi, QuizUi.Listener> createSwipeQuoteViewWrapper();
    UiWrapper<QuizUi, QuizUi.Listener> createSwipeQuoteViewWrapper(final Bundle savedState);
}
