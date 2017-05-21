package com.davidcryer.trumpquotes.android.model.framework.network.quotes.trumpapi;

import com.davidcryer.trumpquotes.android.model.framework.network.TrumpQuoteApiProvider;

public class LiveTrumpQuoteApiProvider implements TrumpQuoteApiProvider {
    private final static String API_BASE_URL = "https://api.whatdoestrumpthink.com/api/";

    @Override
    public String baseUrl() {
        return API_BASE_URL;
    }
}
