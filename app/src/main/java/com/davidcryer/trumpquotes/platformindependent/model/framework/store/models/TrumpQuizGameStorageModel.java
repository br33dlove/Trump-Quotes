package com.davidcryer.trumpquotes.platformindependent.model.framework.store.models;

public class TrumpQuizGameStorageModel {
    public final int[] questionIds;
    public final int questionsAnswered;
    public final int correctAnswers;
    public final boolean isFinished;
    public final int currentQuestionIndex;
    public final boolean isCurrentQuestionAnswered;

    public TrumpQuizGameStorageModel(int[] questionIds, int questionsAnswered, int correctAnswers, boolean isFinished, int currentQuestionIndex, boolean isCurrentQuestionAnswered) {
        this.questionIds = questionIds;
        this.questionsAnswered = questionsAnswered;
        this.correctAnswers = correctAnswers;
        this.isFinished = isFinished;
        this.currentQuestionIndex = currentQuestionIndex;
        this.isCurrentQuestionAnswered = isCurrentQuestionAnswered;
    }
}
