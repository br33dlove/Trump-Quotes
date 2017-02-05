package com.davidcryer.trumpquotes.android.framework.viewwrapperrepositories;

import android.os.Bundle;

import com.davidcryer.trumpquotes.android.view.ViewWrapper;
import com.davidcryer.trumpquotes.android.view.ui.QuotesAndroidView;
import com.davidcryer.trumpquotes.android.view.viewwrapperfactories.ViewWrapperFactory;

class ViewWrapperRepositoryImpl implements ViewWrapperRepository {
    private final ViewWrapperFactory viewWrapperFactory;
    private ViewWrapper<QuotesAndroidView, QuotesAndroidView.EventsListener> quotesViewWrapper;

    private ViewWrapperRepositoryImpl(final ViewWrapperFactory viewWrapperFactory) {
        this.viewWrapperFactory = viewWrapperFactory;
    }

    static ViewWrapperRepository newInstance(final ViewWrapperFactory viewWrapperFactory) {
        return new ViewWrapperRepositoryImpl(viewWrapperFactory);
    }

    @Override
    public QuotesAndroidView.EventsListener bind(QuotesAndroidView view, Bundle savedState) {
        android.util.Log.v(ViewWrapperRepositoryImpl.class.getSimpleName(), "bind, savedState is null: " + (savedState == null ? "true" : "false"));
        if (quotesViewWrapper == null) {
            quotesViewWrapper = savedState == null ? viewWrapperFactory.createQuotesViewWrapper() : viewWrapperFactory.createQuotesViewWrapper(savedState);
        }
        quotesViewWrapper.register(view);
        return quotesViewWrapper.viewEventsListener();
    }

    @Override
    public void unbind(QuotesAndroidView view, ViewUnbindType unbindType) {
        android.util.Log.v(ViewWrapperRepositoryImpl.class.getSimpleName(), "unbind, type: " + unbindType.name());
        if (quotesViewWrapper != null) {
            quotesViewWrapper.unregister();
            if (!unbindType.equals(ViewUnbindType.CONFIG_CHANGE)) {
                quotesViewWrapper.releaseResources();
                if (unbindType.equals(ViewUnbindType.FINISH)) {
                    quotesViewWrapper = null;
                }
            }
        }
    }
}
