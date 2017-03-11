package com.davidcryer.trumpquotes.platformindependent.model.domain.interactors;

import com.davidcryer.trumpquotes.platformindependent.model.domain.entities.QuizGame;

public class ActiveGameInteractors {
    private final AnswerQuestionInteractor answerQuestionInteractor;
    private final GetNextQuestionInteractor getNextQuestionInteractor;

    public ActiveGameInteractors(final InteractorFactory interactorFactory, final QuizGame game) {
        answerQuestionInteractor = interactorFactory.createAnswerNotTrumpInteractor(game);
        getNextQuestionInteractor = interactorFactory.createGetNextQuoteInteractor(game);
    }

    public AnswerQuestionInteractor answerQuestionInteractor() {
        return answerQuestionInteractor;
    }

    public GetNextQuestionInteractor getNextQuestionInteractor() {
        return getNextQuestionInteractor;
    }
}
