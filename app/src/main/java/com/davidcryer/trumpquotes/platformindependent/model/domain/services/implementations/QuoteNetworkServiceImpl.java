package com.davidcryer.trumpquotes.platformindependent.model.domain.services.implementations;

import com.davidcryer.trumpquotes.platformindependent.model.domain.services.QuoteNetworkService;
import com.davidcryer.trumpquotes.platformindependent.model.framework.Cancelable;
import com.davidcryer.trumpquotes.platformindependent.model.network.quotes.Quote;
import com.davidcryer.trumpquotes.platformindependent.model.network.quotes.QuoteRequestCallback;
import com.davidcryer.trumpquotes.platformindependent.model.network.quotes.requesters.RandomQuoteRequester;

public class QuoteNetworkServiceImpl implements QuoteNetworkService {
    private final RandomQuoteRequester quoteRequester;

    public QuoteNetworkServiceImpl(RandomQuoteRequester quoteRequester) {
        this.quoteRequester = quoteRequester;
    }

    @Override
    public Cancelable randomQuote(final Callback callback) {
        return quoteRequester.request(new QuoteRequestCallback() {
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
