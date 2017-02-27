package com.davidcryer.trumpquotes.platformindependent.model.domain.tasks;

import com.davidcryer.trumpquotes.platformindependent.model.domain.entities.TrumpQuizGame;
import com.davidcryer.trumpquotes.platformindependent.model.framework.tasks.Task;

public final class GetNextQuoteTask extends Task<Void, GetNextQuoteTask.ResponseValues> {
    private final TrumpQuizGame game;

    GetNextQuoteTask(TrumpQuizGame game) {
        this.game = game;
    }

    @Override
    protected void doTask(Void requestValues, final Callback<ResponseValues> callback) {
        game.nextQuote(new TrumpQuizGame.NextQuoteCallback() {
            @Override
            public void onGameFinished() {
                onSuccess(new ResponseValues("", true), callback);
            }

            @Override
            public void onNextQuote(String quote) {
                onSuccess(new ResponseValues(quote, false), callback);
            }
        });
    }

    public final static class ResponseValues {
        private final String quote;
        private final boolean isFinished;

        private ResponseValues(String quote, boolean isFinished) {
            this.quote = quote;
            this.isFinished = isFinished;
        }
    }
}
