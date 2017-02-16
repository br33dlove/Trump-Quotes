package com.davidcryer.trumpquotes.android.view.viewmodels;

import com.davidcryer.trumpquotes.android.view.ui.SwipeQuoteAndroidView;
import com.davidcryer.trumpquotes.android.view.viewmodels.models.AndroidViewQuote;
import com.davidcryer.trumpquotes.platformindependent.view.viewmodels.SwipeQuoteMvpViewModel;

import java.util.List;

public interface SwipeQuoteAndroidViewModel extends SwipeQuoteMvpViewModel<AndroidViewQuote>, AndroidViewModel<SwipeQuoteAndroidView> {
    void showQuote(final SwipeQuoteAndroidView view, final AndroidViewQuote quote);
    void showLoadingQuote(final SwipeQuoteAndroidView view);
    void hideLoadingQuote(final SwipeQuoteAndroidView view);
    void showFailureToGetQuote(final SwipeQuoteAndroidView view);
    void hideFailureToGetQuote(final SwipeQuoteAndroidView view);
}
