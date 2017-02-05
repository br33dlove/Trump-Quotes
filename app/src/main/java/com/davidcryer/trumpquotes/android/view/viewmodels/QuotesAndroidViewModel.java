package com.davidcryer.trumpquotes.android.view.viewmodels;

import com.davidcryer.trumpquotes.android.view.ui.QuotesAndroidView;
import com.davidcryer.trumpquotes.platformindependent.view.viewmodels.QuotesMvpViewModel;

public interface QuotesAndroidViewModel extends QuotesMvpViewModel, AndroidViewModel<QuotesAndroidView> {
    void screenChanged(final QuotesAndroidView view);
}
