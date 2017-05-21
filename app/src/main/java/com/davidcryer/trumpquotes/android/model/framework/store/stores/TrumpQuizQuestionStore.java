package com.davidcryer.trumpquotes.android.model.framework.store.stores;

import com.davidcryer.trumpquotes.android.model.framework.store.models.TrumpQuizQuestionStorageModel;

public interface TrumpQuizQuestionStore {
    boolean store(final TrumpQuizQuestionStorageModel[] models);
    boolean clearAll();
    TrumpQuizQuestionStorageModel[] retrieve(final int... ids);
}
