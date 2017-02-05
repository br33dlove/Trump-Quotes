package com.davidcryer.trumpquotes.android.view.viewwrapperfactories;

import android.os.Bundle;

import com.davidcryer.trumpquotes.android.view.ViewWrapper;
import com.davidcryer.trumpquotes.android.view.ui.TemplateAndroidView;

public interface ViewWrapperFactory {
    ViewWrapper<TemplateAndroidView, TemplateAndroidView.EventsListener> createTemplateViewWrapper();
    ViewWrapper<TemplateAndroidView, TemplateAndroidView.EventsListener> createTemplateViewWrapper(final Bundle savedState);
}
