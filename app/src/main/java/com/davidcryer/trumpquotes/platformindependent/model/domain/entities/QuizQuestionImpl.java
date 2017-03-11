package com.davidcryer.trumpquotes.platformindependent.model.domain.entities;

public class QuizQuestionImpl implements QuizQuestion {
    private final String quote;
    private final String optionA;
    private final String optionB;
    private final QuizAnswer correctAnswer;

    public QuizQuestionImpl(String quote, String optionA, String optionB, QuizAnswer correctAnswer) {
        if (quote == null) {
            throw new IllegalArgumentException("Quote cannot be null");
        }
        if (optionA == null) {
            throw new IllegalArgumentException("OptionA cannot be null");
        }
        if (optionB == null) {
            throw new IllegalArgumentException("OptionB cannot be null");
        }
        if (correctAnswer == null) {
            throw new IllegalArgumentException("Correct answer cannot be null");
        }
        this.quote = quote;
        this.optionA = optionA;
        this.optionB = optionB;
        this.correctAnswer = correctAnswer;
    }

    @Override
    public String quote() {
        return quote;
    }

    @Override
    public String optionA() {
        return optionA;
    }

    @Override
    public String optionB() {
        return optionB;
    }

    @Override
    public boolean isCorrect(QuizAnswer answer) {
        return correctAnswer.matches(answer);
    }

    QuizAnswer correctAnswer() {
        return correctAnswer;
    }
}
