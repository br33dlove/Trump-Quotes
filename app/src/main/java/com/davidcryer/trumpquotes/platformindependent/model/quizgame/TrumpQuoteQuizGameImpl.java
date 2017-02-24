package com.davidcryer.trumpquotes.platformindependent.model.quizgame;

import android.support.annotation.NonNull;

import com.davidcryer.trumpquotes.platformindependent.model.quotes.Quote;

class TrumpQuoteQuizGameImpl implements QuoteQuizGame {
    private final Quote[] quotes;
    private final QuizScore quizScore;
    private int currentQuoteIndex;

    private TrumpQuoteQuizGameImpl(Quote[] quotes, QuizScore quizScore) {
        this.quotes = quotes;
        this.quizScore = quizScore;
        currentQuoteIndex = quizScore.questionsAnswered();
    }

    public static QuoteQuizGame newInstance(@NonNull final Quote[] quotes, @NonNull final QuizScore quizScore) {
        if (quotes.length < quizScore.questionsAnswered()) {
            throw new IllegalArgumentException("Questions answered is greater than the number of quotes");
        }
        return new TrumpQuoteQuizGameImpl(quotes, quizScore);
    }

    private Quote currentQuote() {
        return quotes[currentQuoteIndex];
    }

    @Override
    public void startGame(GameStateCallback callback) {
        if (quotes.length == 0) {
            callback.onGameFinished();
        } else {
            callback.onNextQuote(currentQuote());
        }
    }

    @Override
    public void answerOptionA(AnswerCallback answerCallback, GameStateCallback gameStateCallback) {
        if (hasCurrentQuote()) {
            onAnswerGiven(answerCallback, currentQuote().isTrumpQuote());
        }
    }

    @Override
    public void answerOptionB(AnswerCallback answerCallback, GameStateCallback gameStateCallback) {
        if (hasCurrentQuote()) {
            onAnswerGiven(answerCallback, !currentQuote().isTrumpQuote());
            continueGame(gameStateCallback);
        }
    }

    private void onAnswerGiven(final AnswerCallback callback, final boolean answerIsCorrect) {
        if (answerIsCorrect) {
            quizScore.onCorrectAnswerGiven();
            callback.onCorrectAnswerGiven(quizScore.correctAnswers(), quizScore.questionsAnswered());
        } else {
            quizScore.onIncorrectAnswerGiven();
            callback.onIncorrectAnswerGiven(quizScore.correctAnswers(), quizScore.questionsAnswered());
        }
    }

    private boolean hasCurrentQuote() {
        return currentQuoteIndex < quotes.length;
    }

    private void continueGame(final GameStateCallback callback) {
        currentQuoteIndex++;
        if (hasCurrentQuote()) {
            callback.onNextQuote(currentQuote());
        } else {
            callback.onGameFinished();
        }
    }
}
