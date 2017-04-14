package com.davidcryer.trumpquotes.android.view.uimodels;

import com.davidc.uiwrapper.UiModel;
import com.davidcryer.trumpquotes.android.view.ui.QuizUi;
import com.davidcryer.trumpquotes.android.view.uimodels.models.AndroidViewQuestion;
import com.davidcryer.trumpquotes.platformindependent.view.viewmodels.QuizViewModel;

public interface QuizUiModel extends QuizViewModel, UiModel<QuizUi> {
    void showStartNewGame(final QuizUi ui);
    void showLoadingGame(final QuizUi ui);
    void showFailureToLoadGame(final QuizUi ui);
    void showQuestion(final QuizUi ui, final AndroidViewQuestion question);
    void showScore(final QuizUi ui, final int correctAnswerCount, final int questionCount);
    void showFinishedGameState(final QuizUi ui);
}
