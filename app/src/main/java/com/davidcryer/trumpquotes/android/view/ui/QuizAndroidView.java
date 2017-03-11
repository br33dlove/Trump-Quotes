package com.davidcryer.trumpquotes.android.view.ui;

import com.davidcryer.trumpquotes.android.view.viewmodels.models.AndroidViewQuestion;

public interface QuizAndroidView extends AndroidMvpView {
    void showScore(final int correctAnswerCount, final int questionCount);
    void showStartNewGameState();
    void showLoadingState();
    void showFailureToStartGameState();
    void showNewGameTutorial();
    void dismissNewGameTutorial();
    void showQuestionState(final AndroidViewQuestion quote);
    void showFinishedGameState();

    interface EventsListener extends AndroidMvpView.EventsListener {
        void onViewCreated();
        void onClickStartNewGame();
        void onDismissNewGameTutorial();
        void onAnswerOptionA();
        void onAnswerOptionB();
    }
}
