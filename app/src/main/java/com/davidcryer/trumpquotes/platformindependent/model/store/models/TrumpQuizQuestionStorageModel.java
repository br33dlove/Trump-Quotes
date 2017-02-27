package com.davidcryer.trumpquotes.platformindependent.model.store.models;

public class TrumpQuizQuestionStorageModel {
    public final int id;
    public final String text;
    public final boolean isTrumpQuote;

    public TrumpQuizQuestionStorageModel(int id, String text, boolean isTrumpQuote) {
        this.id = id;
        this.text = text;
        this.isTrumpQuote = isTrumpQuote;
    }
}
