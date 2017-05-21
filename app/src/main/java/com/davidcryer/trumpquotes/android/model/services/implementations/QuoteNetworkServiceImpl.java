package com.davidcryer.trumpquotes.android.model.services.implementations;

import com.davidcryer.trumpquotes.android.model.services.QuoteNetworkService;
import com.davidcryer.trumpquotes.android.model.framework.store.Cancelable;
import com.davidcryer.trumpquotes.android.model.framework.network.RequestCallback;
import com.davidcryer.trumpquotes.android.model.framework.network.quotes.Quote;
import com.davidcryer.trumpquotes.android.model.framework.network.quotes.requesters.RandomQuoteRequester;

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
