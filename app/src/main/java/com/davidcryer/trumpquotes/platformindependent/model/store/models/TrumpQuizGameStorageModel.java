package com.davidcryer.trumpquotes.platformindependent.model.store.models;

public class TrumpQuizGameStorageModel {
    public final int[] quoteIds;
    public final int questionsAnswered;
    public final int correctAnswers;
    public final boolean isFinished;
    public final int currentQuestionIndex;
    public final boolean isCurrentQuestionAnswered;

    public TrumpQuizGameStorageModel(int[] quoteIds, int questionsAnswered, int correctAnswers, boolean isFinished, int currentQuestionIndex, boolean isCurrentQuestionAnswered) {
        this.quoteIds = quoteIds;
        this.questionsAnswered = questionsAnswered;
        this.correctAnswers = correctAnswers;
        this.isFinished = isFinished;
        this.currentQuestionIndex = currentQuestionIndex;
        this.isCurrentQuestionAnswered = isCurrentQuestionAnswered;
    }
}
