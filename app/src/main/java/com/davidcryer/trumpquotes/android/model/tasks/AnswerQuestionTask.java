package com.davidcryer.trumpquotes.android.model.tasks;

import com.davidcryer.trumpquotes.android.model.domainentities.QuizAnswer;
import com.davidcryer.trumpquotes.android.model.domainentities.QuizGame;

public class AnswerQuestionTask {
    private final QuizGame game;

    public AnswerQuestionTask(QuizGame game) {
        this.game = game;
    }

    public void answer(final QuizAnswer answer, final AnswerQuestionTaskCallback callback) {
        game.onAnswerGiven(answer, new QuizGame.AnswerCallback() {
            @Override
            public void onRightAnswerGiven(final int correctAnswers, final int questionsAnswered) {
                if (callback != null) {
                    callback.onRightAnswerGiven(correctAnswers, questionsAnswered);
                }
            }

            @Override
            public void onWrongAnswerGiven(final int correctAnswers, final int questionsAnswered) {
                if (callback != null) {
                    callback.onWrongAnswerGiven(correctAnswers, questionsAnswered);
                }
            }
        });
    }
}
