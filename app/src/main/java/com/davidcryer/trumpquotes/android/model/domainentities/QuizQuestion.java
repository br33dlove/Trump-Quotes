package com.davidcryer.trumpquotes.android.model.domainentities;

public interface QuizQuestion {
    String quote();
    String optionA();
    String optionB();
    boolean isCorrect(final QuizAnswer answer);
}
