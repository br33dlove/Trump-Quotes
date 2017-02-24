package com.davidcryer.trumpquotes.platformindependent.model.quizgame;

import com.davidcryer.trumpquotes.platformindependent.model.quotes.Quote;

public interface QuoteQuizGame {
    void startGame(final GameStateCallback callback);
    void answerOptionA(final AnswerCallback answerCallback, final GameStateCallback callback);
    void answerOptionB(final AnswerCallback answerCallback, final GameStateCallback callback);

    interface AnswerCallback {
        void onCorrectAnswerGiven(final int correctAnswers, final int questionsAnswered);
        void onIncorrectAnswerGiven(final int correctAnswers, final int questionsAnswered);
    }

    interface GameStateCallback {
        void onGameFinished();
        void onNextQuote(final Quote quote);
    }
}
