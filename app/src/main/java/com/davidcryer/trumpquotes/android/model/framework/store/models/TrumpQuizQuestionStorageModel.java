package com.davidcryer.trumpquotes.android.model.framework.store.models;

public class TrumpQuizQuestionStorageModel {
    public final int id;
    public final String quote;
    public final String optionA;
    public final String optionB;
    public final String answerType;

    public TrumpQuizQuestionStorageModel(int id, String quote, String optionA, String optionB, String answerType) {
        this.id = id;
        this.quote = quote;
        this.optionA = optionA;
        this.optionB = optionB;
        this.answerType = answerType;
    }
}
