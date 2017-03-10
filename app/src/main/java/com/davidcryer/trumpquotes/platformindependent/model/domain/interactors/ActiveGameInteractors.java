package com.davidcryer.trumpquotes.platformindependent.model.domain.interactors;

public class ActiveGameInteractors {
    private final AnswerIsTrumpInteractor answerIsTrumpInteractor;
    private final AnswerNotTrumpInteractor answerNotTrumpInteractor;
    private final GetNextQuoteInteractor getNextQuoteInteractor;

    public ActiveGameInteractors(AnswerIsTrumpInteractor answerIsTrumpInteractor, AnswerNotTrumpInteractor answerNotTrumpInteractor, GetNextQuoteInteractor getNextQuoteInteractor) {
        this.answerIsTrumpInteractor = answerIsTrumpInteractor;
        this.answerNotTrumpInteractor = answerNotTrumpInteractor;
        this.getNextQuoteInteractor = getNextQuoteInteractor;
    }

    public AnswerIsTrumpInteractor answerIsTrumpTask() {
        return answerIsTrumpInteractor;
    }

    public AnswerNotTrumpInteractor answerNotTrumpTask() {
        return answerNotTrumpInteractor;
    }

    public GetNextQuoteInteractor getNextQuoteTask() {
        return getNextQuoteInteractor;
    }
}
