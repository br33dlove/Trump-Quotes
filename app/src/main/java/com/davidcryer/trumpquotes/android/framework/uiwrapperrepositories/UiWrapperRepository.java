package com.davidcryer.trumpquotes.android.framework.uiwrapperrepositories;

import android.os.Bundle;

import com.davidc.uiwrapper.BaseUiWrapperRepository;
import com.davidcryer.trumpquotes.android.view.ui.QuizUi;

public abstract class UiWrapperRepository extends BaseUiWrapperRepository {
    public abstract QuizUi.Listener bind(final QuizUi view, final String instanceId, final Bundle savedState);
    public abstract void unbind(final QuizUi view, final String instanceId, final Bundle outState, final boolean isConfigurationChange);
}
