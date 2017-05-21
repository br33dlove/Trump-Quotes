package com.davidcryer.trumpquotes.android.framework.uiwrapperrepositories;

import android.os.Bundle;
import android.support.annotation.NonNull;

import com.davidc.uiwrapper.UiWrapper;
import com.davidcryer.trumpquotes.android.presenter.uiwrapperfactories.UiWrapperFactory;
import com.davidcryer.trumpquotes.android.view.ui.QuizUi;
import com.davidcryer.trumpquotes.android.presenter.quiz.uimodels.QuizUiModel;

import java.util.HashMap;
import java.util.Map;

class UiWrapperRepositoryImpl extends UiWrapperRepository {
    private final UiWrapperFactory uiWrapperFactory;
    private Map<String, UiWrapper<QuizUi, QuizUi.Listener, QuizUiModel>> quotesUiWrapperMap = new HashMap<>();

    UiWrapperRepositoryImpl(final UiWrapperFactory uiWrapperFactory) {
        this.uiWrapperFactory = uiWrapperFactory;
    }

    @Override
    public QuizUi.Listener bind(@NonNull final QuizUi view, @NonNull final String instanceId, final Bundle savedState) {
        return bind(view, instanceId, quotesUiWrapperMap, new UiWrapperProvider<QuizUi, QuizUi.Listener, QuizUiModel>() {
            @Override
            @NonNull
            public UiWrapper<QuizUi, QuizUi.Listener, QuizUiModel> uiWrapper() {
                return savedState == null ? uiWrapperFactory.createSwipeQuoteViewWrapper() : uiWrapperFactory.createSwipeQuoteViewWrapper(savedState);
            }
        });
    }

    @Override
    public void unbind(@NonNull final QuizUi view, @NonNull final String instanceId, final Bundle outState, final boolean isConfigurationChange) {
        unbind(instanceId, quotesUiWrapperMap, outState, isConfigurationChange);
    }
}
