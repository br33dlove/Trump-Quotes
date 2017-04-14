package com.davidcryer.trumpquotes.android.framework.uiwrapperrepositories;

import android.os.Bundle;

import com.davidcryer.trumpquotes.android.view.ui.QuizUi;
import com.davidcryer.trumpquotes.android.view.uiwrapperfactories.UiWrapperFactory;
import com.example.davidc.uiwrapper.BaseUiWrapperRepository;
import com.example.davidc.uiwrapper.UiWrapper;
import com.example.davidc.uiwrapper.UiWrapperRepository;

import java.util.HashMap;
import java.util.Map;

public class UiWrapperRepositoryImpl extends BaseUiWrapperRepository implements UiWrapperRepository {
    private final UiWrapperFactory uiWrapperFactory;
    private Map<String, UiWrapper<QuizUi, QuizUi.EventsListener>> quotesUiWrapperMap = new HashMap<>();

    UiWrapperRepositoryImpl(final UiWrapperFactory uiWrapperFactory) {
        this.uiWrapperFactory = uiWrapperFactory;
    }

    public QuizUi.EventsListener bind(final QuizUi view, final String instanceId, final Bundle savedState) {
        return bind(view, instanceId, quotesUiWrapperMap, new UiWrapperProvider<QuizUi, QuizUi.EventsListener>() {
            @Override
            public UiWrapper<QuizUi, QuizUi.EventsListener> uiWrapper() {
                return savedState == null ? uiWrapperFactory.createSwipeQuoteViewWrapper() : uiWrapperFactory.createSwipeQuoteViewWrapper(savedState);
            }
        });
    }

    public void unbind(final QuizUi view, final String instanceId, final Bundle outState, final boolean isConfigurationChange) {
        unbind(instanceId, quotesUiWrapperMap, outState, isConfigurationChange);
    }
}
