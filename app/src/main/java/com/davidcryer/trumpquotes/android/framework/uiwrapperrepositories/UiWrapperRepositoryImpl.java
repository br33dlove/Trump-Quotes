package com.davidcryer.trumpquotes.android.framework.uiwrapperrepositories;

import android.os.Bundle;

import com.davidc.uiwrapper.UiWrapper;
import com.davidcryer.trumpquotes.android.view.ui.QuizUi;
import com.davidcryer.trumpquotes.android.view.uiwrapperfactories.UiWrapperFactory;

import java.util.HashMap;
import java.util.Map;

public class UiWrapperRepositoryImpl extends UiWrapperRepository {
    private final UiWrapperFactory uiWrapperFactory;
    private Map<String, UiWrapper<QuizUi, QuizUi.Listener>> quotesUiWrapperMap = new HashMap<>();

    UiWrapperRepositoryImpl(final UiWrapperFactory uiWrapperFactory) {
        this.uiWrapperFactory = uiWrapperFactory;
    }

    @Override
    public QuizUi.Listener bind(final QuizUi view, final String instanceId, final Bundle savedState) {
        return bind(view, instanceId, quotesUiWrapperMap, new UiWrapperProvider<QuizUi, QuizUi.Listener>() {
            @Override
            public UiWrapper<QuizUi, QuizUi.Listener> uiWrapper() {
                return savedState == null ? uiWrapperFactory.createSwipeQuoteViewWrapper() : uiWrapperFactory.createSwipeQuoteViewWrapper(savedState);
            }
        });
    }

    @Override
    public void unbind(final QuizUi view, final String instanceId, final Bundle outState, final boolean isConfigurationChange) {
        unbind(instanceId, quotesUiWrapperMap, outState, isConfigurationChange);
    }
}
