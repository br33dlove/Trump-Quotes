package com.davidcryer.trumpquotes.android.view.viewmodels;

import com.davidcryer.trumpquotes.android.view.ui.SwipeQuoteAndroidView;
import com.davidcryer.trumpquotes.android.view.viewmodels.models.AndroidViewQuote;
import com.davidcryer.trumpquotes.platformindependent.view.viewmodels.SwipeQuoteMvpViewModel;

import java.util.List;

public interface SwipeQuoteAndroidViewModel extends SwipeQuoteMvpViewModel<AndroidViewQuote>, AndroidViewModel<SwipeQuoteAndroidView> {
    void showQuoteState(final SwipeQuoteAndroidView view, final AndroidViewQuote quote);
    void showLoadingQuoteState(final SwipeQuoteAndroidView view);
    void showFailureToGetQuoteState(final SwipeQuoteAndroidView view);
}
