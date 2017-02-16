package com.davidcryer.trumpquotes.android.framework.viewwrapperrepositories;

import android.os.Bundle;

import com.davidcryer.trumpquotes.android.view.ViewWrapper;
import com.davidcryer.trumpquotes.android.view.ui.SwipeQuoteAndroidView;
import com.davidcryer.trumpquotes.android.view.viewwrapperfactories.ViewWrapperFactory;

class ViewWrapperRepositoryImpl implements ViewWrapperRepository {
    private final ViewWrapperFactory viewWrapperFactory;
    private ViewWrapper<SwipeQuoteAndroidView, SwipeQuoteAndroidView.EventsListener> quotesViewWrapper;

    ViewWrapperRepositoryImpl(final ViewWrapperFactory viewWrapperFactory) {
        this.viewWrapperFactory = viewWrapperFactory;
    }

    @Override
    public SwipeQuoteAndroidView.EventsListener bind(SwipeQuoteAndroidView view, Bundle savedState) {
        if (quotesViewWrapper == null) {
            quotesViewWrapper = savedState == null ? viewWrapperFactory.createSwipeQuoteViewWrapper() : viewWrapperFactory.createSwipeQuoteViewWrapper(savedState);
        }
        quotesViewWrapper.register(view, savedState != null);
        return quotesViewWrapper.viewEventsListener();
    }

    @Override
    public void unbind(SwipeQuoteAndroidView view, ViewUnbindType unbindType) {
        if (quotesViewWrapper != null) {
            quotesViewWrapper.unregister();
            if (!unbindType.equals(ViewUnbindType.CONFIG_CHANGE)) {
                final boolean isFinishing = unbindType.equals(ViewUnbindType.FINISH);
                quotesViewWrapper.releaseResources(isFinishing);
                if (isFinishing) {
                    quotesViewWrapper = null;
                }
            }
        }
    }
}
