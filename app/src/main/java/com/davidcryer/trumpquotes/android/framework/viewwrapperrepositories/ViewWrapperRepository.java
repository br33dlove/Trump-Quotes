package com.davidcryer.trumpquotes.android.framework.viewwrapperrepositories;

import android.os.Bundle;

import com.davidcryer.trumpquotes.android.view.ui.QuizAndroidView;

public interface ViewWrapperRepository {
    QuizAndroidView.EventsListener bind(final QuizAndroidView view, final Bundle savedState);
    void unbind(final QuizAndroidView view, final ViewUnbindType unbindType);
}
