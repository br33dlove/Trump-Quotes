package com.davidcryer.trumpquotes.platformindependent.model.domain.entities;

public class QuizScoreImpl implements QuizScore {
    private int questionsAnswered;
    private int correctAnswers;

    public QuizScoreImpl(int questionsAnswered, int correctAnswers) {
        if (correctAnswers > questionsAnswered) {
            throw new IllegalArgumentException("Correct answers count cannot be greater than questions answered count");
        }
        if (correctAnswers < 0) {
            throw new IllegalArgumentException("Correct answers count cannot be less than zero");
        }
        if (questionsAnswered < 0) {
            throw new IllegalArgumentException("Questions answered count cannot be less than zero");
        }
        this.questionsAnswered = questionsAnswered;
        this.correctAnswers = correctAnswers;
    }

    public static QuizScoreImpl newInstance() {
        return new QuizScoreImpl(0, 0);
    }

    @Override
    public int questionsAnswered() {
        return questionsAnswered;
    }

    @Override
    public int correctAnswers() {
        return correctAnswers;
    }

    @Override
    public void onRightAnswerGiven() {
        correctAnswers++;
        questionsAnswered++;
    }

    @Override
    public void onWrongAnswerGiven() {
        questionsAnswered++;
    }
}
