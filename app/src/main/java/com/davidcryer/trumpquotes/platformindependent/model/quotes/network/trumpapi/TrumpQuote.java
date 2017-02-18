package com.davidcryer.trumpquotes.platformindependent.model.quotes.network.trumpapi;

public class TrumpQuote {
    public String message;
    public String nickname;

    public TrumpQuote(String message, String nickname) {
        this.message = message;
        this.nickname = nickname;
    }
}
