package com.davidcryer.trumpquotes.platformindependent.model.store.stores;

import com.davidcryer.trumpquotes.platformindependent.model.store.models.TrumpQuizQuestionStorageModel;

public interface TrumpQuizQuestionStore {
    boolean store(final TrumpQuizQuestionStorageModel[] models);
    boolean clear();
    TrumpQuizQuestionStorageModel[] retrieve(final int... ids);
}
