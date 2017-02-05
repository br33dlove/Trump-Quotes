package com.davidcryer.trumpquotes.android.view.models;

import com.davidcryer.trumpquotes.android.view.ui.TemplateAndroidView;
import com.davidcryer.trumpquotes.platformindependent.view.models.TemplateMvpViewModel;

public interface TemplateAndroidViewModel extends TemplateMvpViewModel, AndroidViewModel<TemplateAndroidView> {
    void screenChanged(final TemplateAndroidView view);
}
