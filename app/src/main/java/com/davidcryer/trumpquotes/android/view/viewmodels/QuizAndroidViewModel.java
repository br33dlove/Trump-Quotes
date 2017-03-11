package com.davidcryer.trumpquotes.android.view.viewmodels;

import com.davidcryer.trumpquotes.android.view.ui.QuizAndroidView;
import com.davidcryer.trumpquotes.android.view.viewmodels.models.AndroidViewQuestion;
import com.davidcryer.trumpquotes.platformindependent.view.viewmodels.QuizViewModel;

public interface QuizAndroidViewModel extends QuizViewModel, AndroidViewModel<QuizAndroidView> {
    void showScore(final QuizAndroidView view, final int correctAnswerCount, final int questionCount);
    void showStartNewGameState(final QuizAndroidView view);
    void showLoadingState(final QuizAndroidView view);
    void showFailureToStartGameState(final QuizAndroidView view);
    void showNewGameTutorial(final QuizAndroidView view);
    void dismissNewGameTutorial(final QuizAndroidView view);
    void showQuestionState(final QuizAndroidView view, final AndroidViewQuestion question);
    void showFinishedGameState(final QuizAndroidView view);
}
