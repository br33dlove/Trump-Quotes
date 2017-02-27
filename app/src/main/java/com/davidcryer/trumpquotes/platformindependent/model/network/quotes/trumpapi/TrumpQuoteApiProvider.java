package com.davidcryer.trumpquotes.platformindependent.model.network.quotes.trumpapi;

import com.davidcryer.trumpquotes.platformindependent.model.framework.network.ApiProvider;

public class TrumpQuoteApiProvider implements ApiProvider {
    private final static String API_BASE_URL = "https://api.whatdoestrumpthink.com/api/";

    @Override
    public String baseUrl() {
        return API_BASE_URL;
    }
}
