package com.davidcryer.trumpquotes.platformindependent.model.framework.store.stores;

import com.davidcryer.trumpquotes.platformindependent.model.framework.store.models.TrumpQuizQuestionStorageModel;

public interface TrumpQuizQuestionStore {
    boolean store(final TrumpQuizQuestionStorageModel[] models);
    boolean clearAll();
    TrumpQuizQuestionStorageModel[] retrieve(final int... ids);
}
