package com.davidcryer.trumpquotes.platformindependent.model.services;

import com.davidcryer.trumpquotes.platformindependent.model.framework.localfiles.quotes.QuoteFile;
import com.davidcryer.trumpquotes.platformindependent.model.framework.network.quotes.requesters.RandomQuoteRequester;

public interface ServiceFactory {
    QuoteFileService createQuoteFileService(final QuoteFile quoteFile);
    QuoteNetworkService createQuoteNetworkService(final RandomQuoteRequester quoteRequester);
    TrumpQuizGameInitialisationService createTrumpQuizGameInitialisationService();
    TrumpQuizGameStorageService createTrumpQuizGameStorageService();
}
