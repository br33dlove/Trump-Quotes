package com.davidcryer.mvpandroid.android.framework.viewwrapperrepositories;

import android.os.Bundle;

import com.davidcryer.mvpandroid.android.view.ViewWrapper;
import com.davidcryer.mvpandroid.android.view.ui.TemplateAndroidView;
import com.davidcryer.mvpandroid.android.view.viewwrapperfactories.ViewWrapperFactory;

class ViewWrapperRepositoryImpl implements ViewWrapperRepository {
    private final ViewWrapperFactory viewWrapperFactory;
    private ViewWrapper<TemplateAndroidView, TemplateAndroidView.EventsListener> templateViewWrapper;

    private ViewWrapperRepositoryImpl(final ViewWrapperFactory viewWrapperFactory) {
        this.viewWrapperFactory = viewWrapperFactory;
    }

    static ViewWrapperRepository newInstance(final ViewWrapperFactory viewWrapperFactory) {
        return new ViewWrapperRepositoryImpl(viewWrapperFactory);
    }

    @Override
    public TemplateAndroidView.EventsListener bind(TemplateAndroidView view, Bundle savedState) {
        android.util.Log.v(ViewWrapperRepositoryImpl.class.getSimpleName(), "bind, savedState is null: " + (savedState == null ? "true" : "false"));
        if (templateViewWrapper == null) {
            templateViewWrapper = savedState == null ? viewWrapperFactory.createTemplateViewWrapper() : viewWrapperFactory.createTemplateViewWrapper(savedState);
        }
        templateViewWrapper.register(view);
        return templateViewWrapper.viewEventsListener();
    }

    @Override
    public void unbind(TemplateAndroidView view, ViewUnbindType unbindType) {
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
