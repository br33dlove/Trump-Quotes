package com.davidcryer.trumpquotes.android.view.viewmodels;

import com.davidcryer.trumpquotes.android.view.ui.SwipeQuoteAndroidView;
import com.davidcryer.trumpquotes.android.view.viewmodels.models.AndroidViewQuestion;
import com.davidcryer.trumpquotes.platformindependent.view.viewmodels.SwipeQuoteMvpViewModel;

public interface SwipeQuoteAndroidViewModel extends SwipeQuoteMvpViewModel, AndroidViewModel<SwipeQuoteAndroidView> {
    void showScore(final SwipeQuoteAndroidView view, final int correctAnswerCount, final int questionCount);
    void showStartNewGameState(final SwipeQuoteAndroidView view);
    void showLoadingState(final SwipeQuoteAndroidView view);
    void showFailureToStartGameState(final SwipeQuoteAndroidView view);
    void showNewGameTutorial(final SwipeQuoteAndroidView view);
    void dismissNewGameTutorial(final SwipeQuoteAndroidView view);
    void showQuestionState(final AndroidViewQuestion question);
    void showFinishedGameState(final SwipeQuoteAndroidView view);
}
