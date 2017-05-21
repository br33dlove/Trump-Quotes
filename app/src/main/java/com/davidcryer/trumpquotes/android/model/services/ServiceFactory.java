package com.davidcryer.trumpquotes.android.model.services;

public interface ServiceFactory {
    TrumpQuizGameInitialisationService createTrumpQuizGameInitialisationService();
    TrumpQuizGameStorageService createTrumpQuizGameStorageService();
}
