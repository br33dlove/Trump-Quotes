package com.davidcryer.trumpquotes.platformindependent.model.domainentities;

public interface QuizQuestion {
    String quote();
    String optionA();
    String optionB();
    boolean isCorrect(final QuizAnswer answer);
}
