package com.davidcryer.trumpquotes.android.framework.viewwrapperrepositories;

import android.os.Bundle;

import com.davidcryer.trumpquotes.android.view.ui.TemplateAndroidView;

public interface ViewWrapperRepository {
    TemplateAndroidView.EventsListener bind(final TemplateAndroidView view, final Bundle savedState);
    void unbind(final TemplateAndroidView view, final ViewUnbindType unbindType);
}
