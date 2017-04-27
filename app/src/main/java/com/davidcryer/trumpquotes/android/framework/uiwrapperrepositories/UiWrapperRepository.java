package com.davidcryer.trumpquotes.android.framework.uiwrapperrepositories;

import android.os.Bundle;
import android.support.annotation.NonNull;

import com.davidc.uiwrapper.BaseUiWrapperRepository;
import com.davidcryer.trumpquotes.android.view.ui.QuizUi;

public abstract class UiWrapperRepository extends BaseUiWrapperRepository {
    public abstract QuizUi.Listener bind(@NonNull final QuizUi view, @NonNull final String instanceId, final Bundle savedState);
    public abstract void unbind(@NonNull final QuizUi view, @NonNull final String instanceId, final Bundle outState, final boolean isConfigurationChange);
}
