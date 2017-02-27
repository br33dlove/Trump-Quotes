package com.davidcryer.trumpquotes.platformindependent.model.domain.entities;

public abstract class TrumpQuizAnswer {

    abstract boolean isTrump();

    boolean matches(final TrumpQuizAnswer answer) {
        return answer.isTrump() == isTrump();
    }
}
