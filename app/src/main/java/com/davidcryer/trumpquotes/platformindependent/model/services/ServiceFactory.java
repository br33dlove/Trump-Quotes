package com.davidcryer.trumpquotes.platformindependent.model.services;

public interface ServiceFactory {
    TrumpQuizGameInitialisationService createTrumpQuizGameInitialisationService();
    TrumpQuizGameStorageService createTrumpQuizGameStorageService();
}
