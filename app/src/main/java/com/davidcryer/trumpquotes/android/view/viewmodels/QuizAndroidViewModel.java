package com.davidcryer.trumpquotes.android.view.viewmodels;

import com.davidcryer.trumpquotes.android.view.ui.QuizAndroidView;
import com.davidcryer.trumpquotes.android.view.viewmodels.models.AndroidViewQuestion;
import com.davidcryer.trumpquotes.platformindependent.view.viewmodels.QuizViewModel;

public interface QuizAndroidViewModel extends QuizViewModel, AndroidViewModel<QuizAndroidView> {
    void showStartNewGame(final QuizAndroidView view);
    void showLoadingGame(final QuizAndroidView view);
    void showFailureToLoadGame(final QuizAndroidView view);
    void showQuestion(final QuizAndroidView view, final AndroidViewQuestion question);
    void showScore(final QuizAndroidView view, final int correctAnswerCount, final int questionCount);
    void showFinishedGameState(final QuizAndroidView view);
}
