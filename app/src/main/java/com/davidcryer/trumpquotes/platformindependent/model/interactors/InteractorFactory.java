package com.davidcryer.trumpquotes.platformindependent.model.interactors;

import com.davidcryer.trumpquotes.platformindependent.model.domainentities.QuizGame;
import com.davidcryer.trumpquotes.platformindependent.model.services.ServiceFactory;
import com.davidcryer.trumpquotes.platformindependent.model.framework.tasks.TaskScheduler;

public class InteractorFactory {
    private final TaskScheduler taskScheduler;
    private final ServiceFactory serviceFactory;

    public InteractorFactory(TaskScheduler taskScheduler, ServiceFactory serviceFactory) {
        this.taskScheduler = taskScheduler;
        this.serviceFactory = serviceFactory;
    }

    public LoadGameInteractor createLoadGameInteractor() {
        return new LoadGameInteractor(taskScheduler, this, serviceFactory.createTrumpQuizGameStorageService());
    }

    public InitialiseGameInteractor createInitialiseGameInteractor() {
        return new InitialiseGameInteractor(taskScheduler, this, serviceFactory.createTrumpQuizGameInitialisationService());
    }

    AnswerQuestionInteractor createAnswerNotTrumpInteractor(final QuizGame game) {
        return new AnswerQuestionInteractor(taskScheduler, game);
    }

    GetNextQuestionInteractor createGetNextQuoteInteractor(final QuizGame game) {
        return new GetNextQuestionInteractor(taskScheduler, game);
    }
}
