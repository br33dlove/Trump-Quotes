package com.davidcryer.trumpquotes.android.framework.viewwrapperrepositories;

import android.os.Bundle;

import com.davidcryer.trumpquotes.android.view.ui.QuotesAndroidView;

public interface ViewWrapperRepository {
    QuotesAndroidView.EventsListener bind(final QuotesAndroidView view, final Bundle savedState);
    void unbind(final QuotesAndroidView view, final ViewUnbindType unbindType);
}
