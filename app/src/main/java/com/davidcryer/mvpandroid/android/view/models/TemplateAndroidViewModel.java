package com.davidcryer.mvpandroid.android.view.models;

import com.davidcryer.mvpandroid.android.view.ui.TemplateAndroidView;
import com.davidcryer.mvpandroid.platformindependent.view.models.TemplateMvpViewModel;

public interface TemplateAndroidViewModel extends TemplateMvpViewModel, AndroidViewModel<TemplateAndroidView> {
    void screenChanged(final TemplateAndroidView view);
}
