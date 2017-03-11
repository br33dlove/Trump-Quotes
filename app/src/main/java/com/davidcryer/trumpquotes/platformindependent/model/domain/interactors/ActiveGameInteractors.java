package com.davidcryer.trumpquotes.platformindependent.model.domain.interactors;

public class ActiveGameInteractors {
    private final AnswerQuestionInteractor answerQuestionInteractor;
    private final GetNextQuestionInteractor getNextQuestionInteractor;

    public ActiveGameInteractors(AnswerQuestionInteractor answerQuestionInteractor, GetNextQuestionInteractor getNextQuestionInteractor) {
        this.answerQuestionInteractor = answerQuestionInteractor;
        this.getNextQuestionInteractor = getNextQuestionInteractor;
    }

    public AnswerQuestionInteractor answerQuestionInteractor() {
        return answerQuestionInteractor;
    }

    public GetNextQuestionInteractor getNextQuestionInteractor() {
        return getNextQuestionInteractor;
    }
}
