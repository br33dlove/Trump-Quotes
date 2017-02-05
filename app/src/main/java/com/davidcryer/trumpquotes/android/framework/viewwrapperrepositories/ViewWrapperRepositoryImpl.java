package com.davidcryer.trumpquotes.android.framework.viewwrapperrepositories;

import android.os.Bundle;

import com.davidcryer.trumpquotes.android.view.ViewWrapper;
import com.davidcryer.trumpquotes.android.view.ui.QuotesAndroidView;
import com.davidcryer.trumpquotes.android.view.viewwrapperfactories.ViewWrapperFactory;

class ViewWrapperRepositoryImpl implements ViewWrapperRepository {
    private final ViewWrapperFactory viewWrapperFactory;
    private ViewWrapper<QuotesAndroidView, QuotesAndroidView.EventsListener> templateViewWrapper;

    private ViewWrapperRepositoryImpl(final ViewWrapperFactory viewWrapperFactory) {
        this.viewWrapperFactory = viewWrapperFactory;
    }

    static ViewWrapperRepository newInstance(final ViewWrapperFactory viewWrapperFactory) {
        return new ViewWrapperRepositoryImpl(viewWrapperFactory);
    }

    @Override
    public QuotesAndroidView.EventsListener bind(QuotesAndroidView view, Bundle savedState) {
        android.util.Log.v(ViewWrapperRepositoryImpl.class.getSimpleName(), "bind, savedState is null: " + (savedState == null ? "true" : "false"));
        if (templateViewWrapper == null) {
            templateViewWrapper = savedState == null ? viewWrapperFactory.createTemplateViewWrapper() : viewWrapperFactory.createTemplateViewWrapper(savedState);
        }
        templateViewWrapper.register(view);
        return templateViewWrapper.viewEventsListener();
    }

    @Override
    public void unbind(QuotesAndroidView view, ViewUnbindType unbindType) {
        android.util.Log.v(ViewWrapperRepositoryImpl.class.getSimpleName(), "unbind, type: " + unbindType.name());
        if (templateViewWrapper != null) {
            templateViewWrapper.unregister();
            if (!unbindType.equals(ViewUnbindType.CONFIG_CHANGE)) {
                templateViewWrapper.releaseResources();
                if (unbindType.equals(ViewUnbindType.FINISH)) {
                    templateViewWrapper = null;
                }
            }
        }
    }
}
