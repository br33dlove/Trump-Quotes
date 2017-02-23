package com.davidcryer.trumpquotes.platformindependent.model.network.quotes.trumpapi;

public class TrumpQuote {
    String message;
    String nickname;

    public TrumpQuote(String message, String nickname) {
        this.message = message;
        this.nickname = nickname;
    }
}
