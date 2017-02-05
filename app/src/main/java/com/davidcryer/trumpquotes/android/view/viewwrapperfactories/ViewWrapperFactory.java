package com.davidcryer.trumpquotes.android.view.viewwrapperfactories;

import android.os.Bundle;

import com.davidcryer.trumpquotes.android.view.ViewWrapper;
import com.davidcryer.trumpquotes.android.view.ui.QuotesAndroidView;

public interface ViewWrapperFactory {
    ViewWrapper<QuotesAndroidView, QuotesAndroidView.EventsListener> createTemplateViewWrapper();
    ViewWrapper<QuotesAndroidView, QuotesAndroidView.EventsListener> createTemplateViewWrapper(final Bundle savedState);
}
