package com.davidcryer.trumpquotes.platformindependent.model.domain.interactors;

import com.davidcryer.trumpquotes.platformindependent.model.domain.entities.QuizGame;
import com.davidcryer.trumpquotes.platformindependent.model.domain.services.ServiceFactory;
import com.davidcryer.trumpquotes.platformindependent.model.framework.tasks.TaskScheduler;

public class InteractorFactory {
    private final TaskScheduler taskScheduler;
    private final ServiceFactory serviceFactory;
    private LoadGameInteractor loadGameInteractor;
    private InitialiseGameInteractor initialiseGameInteractor;
    private AnswerQuestionInteractor answerQuestionInteractor;
    private GetNextQuestionInteractor getNextQuestionInteractor;

    public InteractorFactory(TaskScheduler taskScheduler, ServiceFactory serviceFactory) {
        this.taskScheduler = taskScheduler;
        this.serviceFactory = serviceFactory;
    }

    public LoadGameInteractor createLoadGameInteractor() {
        if (loadGameInteractor == null) {
            return new LoadGameInteractor(taskScheduler, this, serviceFactory.createTrumpQuizGameStorageService());
        }
        return loadGameInteractor;
    }

    public InitialiseGameInteractor createInitialiseGameInteractor() {
        if (initialiseGameInteractor == null) {
            initialiseGameInteractor = new InitialiseGameInteractor(taskScheduler, this, serviceFactory.createTrumpQuizGameInitialisationService());
        }
        return initialiseGameInteractor;
    }

    public AnswerQuestionInteractor createAnswerNotTrumpInteractor(final QuizGame game) {
        if (answerQuestionInteractor == null) {
            answerQuestionInteractor = new AnswerQuestionInteractor(taskScheduler, game);
        }
        return answerQuestionInteractor;
    }

    public GetNextQuestionInteractor createGetNextQuoteInteractor(final QuizGame game) {
        if (getNextQuestionInteractor == null) {
            getNextQuestionInteractor = new GetNextQuestionInteractor(taskScheduler, game);
        }
        return getNextQuestionInteractor;
    }
}
