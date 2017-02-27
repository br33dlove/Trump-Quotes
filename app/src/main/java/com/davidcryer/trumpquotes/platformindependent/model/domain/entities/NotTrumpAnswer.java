package com.davidcryer.trumpquotes.platformindependent.model.domain.entities;

public class NotTrumpAnswer extends TrumpQuizAnswer {

    @Override
    public boolean isTrump() {
        return false;
    }
}
