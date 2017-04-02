package com.davidcryer.trumpquotes.platformindependent.view;

import com.davidcryer.trumpquotes.platformindependent.view.viewmodels.QuizViewModel;
import com.davidcryer.trumpquotes.platformindependent.view.viewmodels.models.ViewQuestion;

public interface QuizView<ViewQuestionType extends ViewQuestion> extends MvpView<QuizViewModel> {
    void showStartNewGame();
    void showLoadingGame();
    void showFailureToLoadGame();
    void showQuestion(final ViewQuestionType quotes);
    void showScore(final int correctAnswerCount, final int questionCount);
    void showFinishedGame();

    interface EventsListener extends MvpView.EventsListener {
        void onInitialise();
        void onClickStartNewGame();
        void onAnswerOptionA();
        void onAnswerOptionB();
    }
}
