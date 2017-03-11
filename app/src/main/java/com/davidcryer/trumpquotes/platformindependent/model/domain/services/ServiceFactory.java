package com.davidcryer.trumpquotes.platformindependent.model.domain.services;

public interface ServiceFactory {
    QuoteFileService createQuoteFileService();
    QuoteNetworkService createQuoteNetworkService();
    TrumpQuizGameInitialisationService createTrumpQuizGameInitialisationService();
    TrumpQuizGameStorageService createTrumpQuizGameStorageService();
}
