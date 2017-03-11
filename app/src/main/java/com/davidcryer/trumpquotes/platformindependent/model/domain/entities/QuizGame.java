package com.davidcryer.trumpquotes.platformindependent.model.domain.entities;

public interface QuizGame {
    void startGame(final StartCallback callback);
    void onAnswerGiven(final QuizAnswer answer, final AnswerCallback answerCallback);
    void nextQuestion(final NextQuestionCallback callback);

    interface StartCallback {
        void onReturn(final int correctAnswers, final int questionsAnswered);
    }

    interface AnswerCallback {
        void onRightAnswerGiven(final int correctAnswers, final int questionsAnswered);
        void onWrongAnswerGiven(final int correctAnswers, final int questionsAnswered);
    }

    interface NextQuestionCallback {
        void onGameFinished();
        void nextQuestion(final QuizQuestion quizQuestion);
    }
}
