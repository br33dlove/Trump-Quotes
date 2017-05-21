package com.davidcryer.trumpquotes.android.model.interactors;

import com.davidcryer.trumpquotes.android.model.domainentities.QuizGame;

public class ActiveGameInteractors {
    private final AnswerQuestionInteractor answerQuestionInteractor;
    private final GetNextQuestionInteractor getNextQuestionInteractor;

    ActiveGameInteractors(final InteractorFactory interactorFactory, final QuizGame game) {
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
