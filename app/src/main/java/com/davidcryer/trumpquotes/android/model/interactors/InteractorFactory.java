package com.davidcryer.trumpquotes.android.model.interactors;

import com.davidc.interactor.TaskScheduler;
import com.davidcryer.trumpquotes.android.model.domainentities.QuizGame;
import com.davidcryer.trumpquotes.android.model.services.ServiceFactory;

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
