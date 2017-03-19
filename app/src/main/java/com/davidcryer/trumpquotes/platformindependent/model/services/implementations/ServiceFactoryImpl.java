package com.davidcryer.trumpquotes.platformindependent.model.services.implementations;

import com.davidcryer.trumpquotes.platformindependent.model.services.QuoteFileService;
import com.davidcryer.trumpquotes.platformindependent.model.services.QuoteNetworkService;
import com.davidcryer.trumpquotes.platformindependent.model.services.ServiceFactory;
import com.davidcryer.trumpquotes.platformindependent.model.services.TrumpQuizGameInitialisationService;
import com.davidcryer.trumpquotes.platformindependent.model.services.TrumpQuizGameStorageService;
import com.davidcryer.trumpquotes.platformindependent.model.framework.localfiles.quotes.QuoteFile;
import com.davidcryer.trumpquotes.platformindependent.model.framework.network.quotes.requesters.RandomQuoteRequester;
import com.davidcryer.trumpquotes.platformindependent.model.framework.store.stores.TrumpQuizGameStore;
import com.davidcryer.trumpquotes.platformindependent.model.framework.store.stores.TrumpQuizQuestionStore;

public class ServiceFactoryImpl implements ServiceFactory {
    private final QuoteFile gumpQuoteFile;
    private final RandomQuoteRequester trumpQuoteRequester;
    private final TrumpQuizGameStore gameStore;
    private final TrumpQuizQuestionStore questionStore;

    public ServiceFactoryImpl(
            QuoteFile gumpQuoteFile,
            RandomQuoteRequester trumpQuoteRequester,
            TrumpQuizGameStore gameStore,
            TrumpQuizQuestionStore questionStore
    ) {
        this.gumpQuoteFile = gumpQuoteFile;
        this.trumpQuoteRequester = trumpQuoteRequester;
        this.gameStore = gameStore;
        this.questionStore = questionStore;
    }

    @Override
    public QuoteFileService createQuoteFileService(final QuoteFile quoteFile) {
        return new QuoteFileServiceImpl(quoteFile);
    }

    @Override
    public QuoteNetworkService createQuoteNetworkService(final RandomQuoteRequester quoteRequester) {
        return new QuoteNetworkServiceImpl(quoteRequester);
    }

    @Override
    public TrumpQuizGameInitialisationService createTrumpQuizGameInitialisationService() {
        return new TrumpQuizGameInitialisationServiceImpl(createQuoteNetworkService(trumpQuoteRequester), createQuoteFileService(gumpQuoteFile));
    }

    @Override
    public TrumpQuizGameStorageService createTrumpQuizGameStorageService() {
        return new TrumpQuizGameStorageServiceImpl(gameStore, questionStore);
    }
}
