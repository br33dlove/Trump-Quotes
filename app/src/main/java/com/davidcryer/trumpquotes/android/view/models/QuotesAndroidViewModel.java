package com.davidcryer.trumpquotes.android.view.models;

import com.davidcryer.trumpquotes.android.view.ui.QuotesAndroidView;
import com.davidcryer.trumpquotes.platformindependent.view.models.QuotesMvpViewModel;

public interface QuotesAndroidViewModel extends QuotesMvpViewModel, AndroidViewModel<QuotesAndroidView> {
    void screenChanged(final QuotesAndroidView view);
}
