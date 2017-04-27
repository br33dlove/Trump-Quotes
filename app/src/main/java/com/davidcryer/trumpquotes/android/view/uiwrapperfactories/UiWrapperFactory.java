package com.davidcryer.trumpquotes.android.view.uiwrapperfactories;

import android.os.Bundle;

import com.davidc.uiwrapper.UiWrapper;
import com.davidcryer.trumpquotes.android.view.ui.QuizUi;
import com.davidcryer.trumpquotes.android.view.uimodels.QuizUiModel;

public interface UiWrapperFactory {
    UiWrapper<QuizUi, QuizUi.Listener, QuizUiModel> createSwipeQuoteViewWrapper();
    UiWrapper<QuizUi, QuizUi.Listener, QuizUiModel> createSwipeQuoteViewWrapper(final Bundle savedState);
}
