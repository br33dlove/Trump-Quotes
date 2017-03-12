package com.davidcryer.trumpquotes.android.view.viewmodels;

import com.davidcryer.trumpquotes.android.view.ui.QuizAndroidView;
import com.davidcryer.trumpquotes.android.view.viewmodels.models.AndroidViewQuestion;
import com.davidcryer.trumpquotes.platformindependent.view.viewmodels.QuizViewModel;

public interface QuizAndroidViewModel extends QuizViewModel, AndroidViewModel<QuizAndroidView> {
    void showScore(final QuizAndroidView view, final int correctAnswerCount, final int questionCount);
    void showStartNewGameState(final QuizAndroidView view);
    void showNewGameLoadingState(final QuizAndroidView view);
    void showFailureToStartGameState(final QuizAndroidView view);
    void showNewGameTutorialState(final QuizAndroidView view);
    void showGameRunningState(final QuizAndroidView view);
    void showQuestion(final QuizAndroidView view, final AndroidViewQuestion question);
    void showFinishedGameState(final QuizAndroidView view);
}
