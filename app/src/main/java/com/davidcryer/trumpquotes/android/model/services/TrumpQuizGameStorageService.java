package com.davidcryer.trumpquotes.android.model.services;

import com.davidcryer.trumpquotes.android.model.domainentities.TrumpQuizGameImpl;
import com.davidcryer.trumpquotes.android.model.services.errors.StorageError;

public interface TrumpQuizGameStorageService {
    void save(TrumpQuizGameImpl game, final SaveCallback callback);
    void load(final LoadCallback callback);

    interface SaveCallback {
        void onSuccess();
        void onError(final StorageError error);
    }

    interface LoadCallback {
        void onLoadGame(final TrumpQuizGameImpl game);
        void onNoSavedGameFound();
        void onGameCorrupted();
        void onError(final StorageError error);
    }
}
