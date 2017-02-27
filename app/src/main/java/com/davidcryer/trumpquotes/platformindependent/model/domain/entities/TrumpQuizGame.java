package com.davidcryer.trumpquotes.platformindependent.model.domain.entities;

public interface TrumpQuizGame {
    void startGame(final StartCallback callback);
    void onAnswerGiven(final TrumpQuizAnswer answer, final AnswerCallback answerCallback);
    void nextQuote(final NextQuoteCallback callback);

    interface StartCallback {
        void onReturn(final int correctAnswers, final int questionsAnswered);
    }

    interface AnswerCallback {
        void onRightAnswerGiven(final int correctAnswers, final int questionsAnswered);
        void onWrongAnswerGiven(final int correctAnswers, final int questionsAnswered);
    }

    interface NextQuoteCallback {
        void onGameFinished();
        void onNextQuote(final String quote);
    }
}
