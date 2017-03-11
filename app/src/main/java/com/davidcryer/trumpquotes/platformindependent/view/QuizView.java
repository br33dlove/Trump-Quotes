package com.davidcryer.trumpquotes.platformindependent.view;

import com.davidcryer.trumpquotes.platformindependent.view.viewmodels.QuizViewModel;
import com.davidcryer.trumpquotes.platformindependent.view.viewmodels.models.ViewQuestion;

public interface QuizView<ViewQuestionType extends ViewQuestion> extends MvpView<QuizViewModel> {
    void showScore(final int correctAnswerCount, final int questionCount);
    void showStartNewGameState();
    void showLoadingState();
    void showFailureToStartGameState();
    void showNewGameTutorial();
    void dismissNewGameTutorial();
    void showQuestionState(final ViewQuestionType quotes);
    void showFinishedGameState();

    interface EventsListener extends MvpView.EventsListener {
        void onInitialise();
        void onClickStartNewGame();
        void onDismissNewGameTutorial();
        void onAnswerOptionA();
        void onAnswerOptionB();
    }
}
