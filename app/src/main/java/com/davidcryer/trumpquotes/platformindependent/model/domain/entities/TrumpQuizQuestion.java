package com.davidcryer.trumpquotes.platformindependent.model.domain.entities;

public interface TrumpQuizQuestion {
    String quote();
    boolean isCorrect(final TrumpQuizAnswer answer);
}
