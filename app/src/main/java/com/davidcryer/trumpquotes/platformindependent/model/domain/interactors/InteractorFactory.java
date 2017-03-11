package com.davidcryer.trumpquotes.platformindependent.model.domain.interactors;

import com.davidcryer.trumpquotes.platformindependent.model.domain.entities.QuizGame;
import com.davidcryer.trumpquotes.platformindependent.model.domain.services.ServiceFactory;
import com.davidcryer.trumpquotes.platformindependent.model.framework.tasks.TaskScheduler;

public class InteractorFactory {
    private final TaskScheduler taskScheduler;
    private final ServiceFactory serviceFactory;

    public InteractorFactory(TaskScheduler taskScheduler, ServiceFactory serviceFactory) {
        this.taskScheduler = taskScheduler;
        this.serviceFactory = serviceFactory;
    }

    public AnswerQuestionInteractor createAnswerNotTrumpInteractor(final QuizGame game) {
        return new AnswerQuestionInteractor(taskScheduler, game);
    }

    public GetNextQuestionInteractor createGetNextQuoteInteractor(final QuizGame game) {
        return new GetNextQuestionInteractor(taskScheduler, game);
    }

    public InitialiseGameInteractor createInitialiseGameInteractor() {
        return new InitialiseGameInteractor(
                taskScheduler,
                this,
                serviceFactory.createTrumpQuizGameStorageService(),
                serviceFactory.createTrumpQuizGameInitialisationService()
        );
    }
}
