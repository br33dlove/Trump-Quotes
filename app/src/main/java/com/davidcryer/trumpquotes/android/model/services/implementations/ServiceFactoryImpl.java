package com.davidcryer.trumpquotes.android.model.services.implementations;

import com.davidcryer.trumpquotes.android.model.services.QuoteFileService;
import com.davidcryer.trumpquotes.android.model.services.QuoteNetworkService;
import com.davidcryer.trumpquotes.android.model.services.ServiceFactory;
import com.davidcryer.trumpquotes.android.model.services.TrumpQuizGameInitialisationService;
import com.davidcryer.trumpquotes.android.model.services.TrumpQuizGameStorageService;
import com.davidcryer.trumpquotes.android.model.framework.localfiles.quotes.QuoteFile;
import com.davidcryer.trumpquotes.android.model.framework.network.quotes.requesters.RandomQuoteRequester;
import com.davidcryer.trumpquotes.android.model.framework.store.stores.TrumpQuizGameStore;
import com.davidcryer.trumpquotes.android.model.framework.store.stores.TrumpQuizQuestionStore;

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
    public TrumpQuizGameInitialisationService createTrumpQuizGameInitialisationService() {
        return new TrumpQuizGameInitialisationServiceImpl(createQuoteNetworkService(trumpQuoteRequester), createQuoteFileService(gumpQuoteFile));
    }

    private QuoteNetworkService createQuoteNetworkService(final RandomQuoteRequester quoteRequester) {
        return new QuoteNetworkServiceImpl(quoteRequester);
    }

    private QuoteFileService createQuoteFileService(final QuoteFile quoteFile) {
        return new QuoteFileServiceImpl(quoteFile);
    }

    @Override
    public TrumpQuizGameStorageService createTrumpQuizGameStorageService() {
        return new TrumpQuizGameStorageServiceImpl(gameStore, questionStore);
    }
}
