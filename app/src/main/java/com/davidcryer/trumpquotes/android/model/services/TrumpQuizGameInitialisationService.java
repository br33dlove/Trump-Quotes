package com.davidcryer.trumpquotes.android.model.services;

import com.davidcryer.trumpquotes.android.model.domainentities.QuizGame;
import com.davidcryer.trumpquotes.android.model.services.errors.InitialisationError;

public interface TrumpQuizGameInitialisationService {
    void initialiseNewGame(final int questionCount, final Callback callback);

    interface Callback {
        void onSuccess(final QuizGame game);
        void onFailure(final InitialisationError error);
    }
}
