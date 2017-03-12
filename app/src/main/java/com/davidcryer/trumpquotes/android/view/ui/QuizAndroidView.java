package com.davidcryer.trumpquotes.android.view.ui;

import com.davidcryer.trumpquotes.android.view.viewmodels.models.AndroidViewQuestion;

public interface QuizAndroidView extends AndroidMvpView {
    void showNewGameStateStart();
    void showNewGameStateLoading();
    void showNewGameStateError();
    void hideNewGameState();
    void showPlayGameStateTutorial();
    void showPlayGameStateRunning();
    void showPlayGameStateFinished();
    void hidePlayGameState();
    void showScore(final int correctAnswerCount, final int questionCount);
    void showQuestion(final AndroidViewQuestion question);

    interface EventsListener extends AndroidMvpView.EventsListener {
        void onViewCreated();
        void onClickStartNewGame();
        void onDismissTutorial();
        void onAnswerOptionA();
        void onAnswerOptionB();
    }
}
