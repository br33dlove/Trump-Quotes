package com.davidcryer.trumpquotes.android.view.ui;

import com.davidc.uiwrapper.Ui;
import com.davidcryer.trumpquotes.android.view.uimodels.models.AndroidViewQuestion;

public interface QuizUi extends Ui {
    void showNewGameStartState();
    void animateInNewGameStartState();
    void showNewGameLoadingState();
    void animateInNewGameLoadingState();
    void showNewGameFailedToStartState();
    void animateInNewGameFailedToLoadGameState();
    void showNewGameFinishedState(final int correctAnswerCount, final int questionCount);
    void animateInNewGameFinishedState(final int correctAnswerCount, final int questionCount);
    void hideNewGameScene();
    void animateOutNewGameScene(final Runnable endAction);
    void showScore(final int correctAnswerCount, final int questionCount);
    void animateInScore(final int correctAnswerCount, final int questionCount);
    void showQuestion(final AndroidViewQuestion question);
    void animateInQuestion(final AndroidViewQuestion question);
    void hideGameInPlayScene();
    void animateOutGameInPlayScene(final Runnable endAction);

    interface Listener extends Ui.Listener {
        void onViewCreated();
        void onClickStartNewGame();
        void onAnswerOptionA();
        void onAnswerOptionB();
    }
}
