package com.davidcryer.trumpquotes.android.framework.viewwrapperrepositories;

import android.os.Bundle;

import com.davidcryer.trumpquotes.android.view.ViewWrapper;
import com.davidcryer.trumpquotes.android.view.ui.AndroidMvpView;
import com.davidcryer.trumpquotes.android.view.ui.QuizAndroidView;
import com.davidcryer.trumpquotes.android.view.viewwrapperfactories.ViewWrapperFactory;

import java.util.HashMap;
import java.util.Map;

class ViewWrapperRepositoryImpl implements ViewWrapperRepository {
    private final ViewWrapperFactory viewWrapperFactory;
    private Map<String, ViewWrapper<QuizAndroidView, QuizAndroidView.EventsListener>> quotesViewWrapperMap = new HashMap<>();

    ViewWrapperRepositoryImpl(final ViewWrapperFactory viewWrapperFactory) {
        this.viewWrapperFactory = viewWrapperFactory;
    }

    @Override
    public QuizAndroidView.EventsListener bind(QuizAndroidView view, String instanceId, final Bundle savedState) {
        return bind(view, instanceId, quotesViewWrapperMap, new ViewWrapperProvider<QuizAndroidView, QuizAndroidView.EventsListener>() {
            @Override
            public ViewWrapper<QuizAndroidView, QuizAndroidView.EventsListener> viewWrapper() {
                return savedState == null ? viewWrapperFactory.createSwipeQuoteViewWrapper() : viewWrapperFactory.createSwipeQuoteViewWrapper(savedState);
            }
        });
    }

    @Override
    public void unbind(QuizAndroidView view, String instanceId, ViewUnbindType unbindType) {
        unbind(instanceId, unbindType, quotesViewWrapperMap);
    }

    private static <ViewType extends AndroidMvpView, EventsListenerType extends AndroidMvpView.EventsListener> EventsListenerType bind(
            final ViewType view,
            final String instanceId,
            final Map<String, ViewWrapper<ViewType, EventsListenerType>> viewWrapperMap,
            final ViewWrapperProvider<ViewType, EventsListenerType> viewWrapperProvider
    ) {
        ViewWrapper<ViewType, EventsListenerType> viewWrapper = viewWrapperMap.get(instanceId);
        if (viewWrapper == null) {
            viewWrapper = viewWrapperProvider.viewWrapper();
            viewWrapperMap.put(instanceId, viewWrapper);
        }
        return viewWrapper.register(view);
    }

    private static <ViewType extends AndroidMvpView, EventsListenerType extends AndroidMvpView.EventsListener> void unbind(
            final String instanceId,
            final ViewUnbindType unbindType,
            final Map<String, ViewWrapper<ViewType, EventsListenerType>> viewWrapperMap
    ) {
        final ViewWrapper<ViewType, EventsListenerType> viewWrapper = viewWrapperMap.get(instanceId);
        if (viewWrapper != null) {
            viewWrapper.unregister();
            if (!unbindType.equals(ViewUnbindType.CONFIG_CHANGE)) {
                final boolean isFinishing = unbindType.equals(ViewUnbindType.FINISH);
                viewWrapper.releaseResources(isFinishing);
                if (isFinishing) {
                    viewWrapperMap.remove(instanceId);
                }
            }
        }
    }

    private interface ViewWrapperProvider<ViewType extends AndroidMvpView, EventsListenerType extends AndroidMvpView.EventsListener> {
        ViewWrapper<ViewType, EventsListenerType> viewWrapper();
    }
}
