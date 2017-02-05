package com.davidcryer.trumpquotes.android.view.viewwrapperfactories;

import android.os.Bundle;

import com.davidcryer.trumpquotes.android.view.ViewWrapper;
import com.davidcryer.trumpquotes.android.view.ui.QuotesAndroidView;

public interface ViewWrapperFactory {
    ViewWrapper<QuotesAndroidView, QuotesAndroidView.EventsListener> createQuotesViewWrapper();
    ViewWrapper<QuotesAndroidView, QuotesAndroidView.EventsListener> createQuotesViewWrapper(final Bundle savedState);
}
