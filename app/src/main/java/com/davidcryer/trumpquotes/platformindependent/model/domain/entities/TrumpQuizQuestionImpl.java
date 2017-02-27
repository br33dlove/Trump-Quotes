package com.davidcryer.trumpquotes.platformindependent.model.domain.entities;

public class TrumpQuizQuestionImpl implements TrumpQuizQuestion {
    private final String quote;
    private final TrumpQuizAnswer correctAnswer;

    public TrumpQuizQuestionImpl(String quote, TrumpQuizAnswer correctAnswer) {
        if (quote == null) {
            throw new IllegalArgumentException("Quote cannot be null");
        }
        if (correctAnswer == null) {
            throw new IllegalArgumentException("Correct answer cannot be null");
        }
        this.quote = quote;
        this.correctAnswer = correctAnswer;
    }

    @Override
    public String quote() {
        return quote;
    }

    @Override
    public boolean isCorrect(TrumpQuizAnswer answer) {
        return correctAnswer.matches(answer);
    }

    boolean isTrumpQuote() {
        return correctAnswer.isTrump();
    }
}
