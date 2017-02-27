package com.davidcryer.trumpquotes.platformindependent.model.domain.tasks;

import com.davidcryer.trumpquotes.platformindependent.model.domain.entities.NotTrumpAnswer;
import com.davidcryer.trumpquotes.platformindependent.model.domain.entities.TrumpQuizGame;
import com.davidcryer.trumpquotes.platformindependent.model.framework.tasks.Task;

public final class AnswerNotTrumpTask extends Task<Void, AnswerNotTrumpTask.ResponseValues> {
    private final TrumpQuizGame game;

    AnswerNotTrumpTask(TrumpQuizGame game) {
        this.game = game;
    }

    @Override
    protected void doTask(Void requestValues, final Callback<ResponseValues> callback) {
        game.onAnswerGiven(new NotTrumpAnswer(), new TrumpQuizGame.AnswerCallback() {
            @Override
            public void onRightAnswerGiven(int correctAnswers, int questionsAnswered) {
                onSuccess(new ResponseValues(true, correctAnswers, questionsAnswered), callback);
            }

            @Override
            public void onWrongAnswerGiven(int correctAnswers, int questionsAnswered) {
                onSuccess(new ResponseValues(false, correctAnswers, questionsAnswered), callback);
            }
        });
    }

    public final static class ResponseValues {
        private final boolean correctAnswer;
        private final int correctAnswers;
        private final int questionsAnswered;

        private ResponseValues(boolean correctAnswer, int correctAnswers, int questionsAnswered) {
            this.correctAnswer = correctAnswer;
            this.correctAnswers = correctAnswers;
            this.questionsAnswered = questionsAnswered;
        }
    }
}
