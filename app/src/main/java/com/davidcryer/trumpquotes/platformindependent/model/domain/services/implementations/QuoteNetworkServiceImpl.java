package com.davidcryer.trumpquotes.platformindependent.model.domain.services.implementations;

import com.davidcryer.trumpquotes.platformindependent.model.domain.services.QuoteNetworkService;
import com.davidcryer.trumpquotes.platformindependent.model.framework.Cancelable;
import com.davidcryer.trumpquotes.platformindependent.model.framework.network.RequestCallback;
import com.davidcryer.trumpquotes.platformindependent.model.network.quotes.Quote;
import com.davidcryer.trumpquotes.platformindependent.model.network.quotes.requesters.RandomQuoteRequester;

class QuoteNetworkServiceImpl implements QuoteNetworkService {
    private final RandomQuoteRequester quoteRequester;

    QuoteNetworkServiceImpl(RandomQuoteRequester quoteRequester) {
        this.quoteRequester = quoteRequester;
    }

    @Override
    public Cancelable randomQuote(final Callback callback) {
        return quoteRequester.randomQuoteRequest(new RequestCallback<Quote>() {
            @Override
            public void success(Quote quote) {
                callback.onSuccess(quote);
            }

            @Override
            public void failure() {
                callback.onError();
            }
        });
    }
}
