package com.davidcryer.trumpquotes.android.view.viewmodels;

import com.davidcryer.trumpquotes.android.view.ui.SwipeQuoteAndroidView;
import com.davidcryer.trumpquotes.android.view.viewmodels.models.AndroidViewQuestion;
import com.davidcryer.trumpquotes.platformindependent.view.viewmodels.SwipeQuoteMvpViewModel;

public interface SwipeQuoteAndroidViewModel extends SwipeQuoteMvpViewModel<AndroidViewQuestion>, AndroidViewModel<SwipeQuoteAndroidView> {
    void showQuoteState(final SwipeQuoteAndroidView view, final AndroidViewQuestion quote);
    void showLoadingQuotesState(final SwipeQuoteAndroidView view);
    void showFailureToGetQuotesState(final SwipeQuoteAndroidView view);
    void showScore(final SwipeQuoteAndroidView view, final int correctAnswerCount, final int questionCount);
}
