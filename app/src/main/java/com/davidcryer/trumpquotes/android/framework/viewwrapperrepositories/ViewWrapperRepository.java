package com.davidcryer.trumpquotes.android.framework.viewwrapperrepositories;

import android.os.Bundle;

import com.davidcryer.trumpquotes.android.view.ui.SwipeQuoteAndroidView;

public interface ViewWrapperRepository {
    SwipeQuoteAndroidView.EventsListener bind(final SwipeQuoteAndroidView view, final Bundle savedState);
    void unbind(final SwipeQuoteAndroidView view, final ViewUnbindType unbindType);
}
