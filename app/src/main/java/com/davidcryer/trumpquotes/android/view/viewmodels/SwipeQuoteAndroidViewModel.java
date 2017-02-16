package com.davidcryer.trumpquotes.android.view.viewmodels;

import com.davidcryer.trumpquotes.android.view.ui.SwipeQuoteAndroidView;
import com.davidcryer.trumpquotes.android.view.viewmodels.models.AndroidViewQuote;
import com.davidcryer.trumpquotes.platformindependent.view.viewmodels.SwipeQuoteMvpViewModel;

import java.util.List;

public interface SwipeQuoteAndroidViewModel extends SwipeQuoteMvpViewModel<AndroidViewQuote>, AndroidViewModel<SwipeQuoteAndroidView> {
    void showFailureToGetNewQuote(final SwipeQuoteAndroidView view);
    void showLoadingNewQuote(final SwipeQuoteAndroidView view);
    void hideLoadingNewQuote(final SwipeQuoteAndroidView view);
    void showNewQuote(final SwipeQuoteAndroidView view, final AndroidViewQuote quote);
}
