package com.davidcryer.trumpquotes.android.presenter.presenters;

import com.davidcryer.trumpquotes.android.view.uimodels.models.AndroidViewQuestion;
import com.davidcryer.trumpquotes.platformindependent.model.interactors.InteractorFactory;
import com.davidcryer.trumpquotes.platformindependent.presenter.presenters.QuizPresenterFactory;
import com.davidcryer.trumpquotes.platformindependent.presenter.presenters.implementations.quiz.QuizPresenterFactoryImpl;
import com.davidcryer.trumpquotes.platformindependent.view.viewmodels.models.ViewQuestionFactory;

public class PresenterFactoryFactoryImpl implements PresenterFactoryFactory {
    private final ViewQuestionFactory<AndroidViewQuestion> viewQuestionFactory;
    private final InteractorFactory interactorFactory;

    public PresenterFactoryFactoryImpl(
            final ViewQuestionFactory<AndroidViewQuestion> viewQuestionFactory,
            final InteractorFactory interactorFactory
    ) {
        this.viewQuestionFactory = viewQuestionFactory;
        this.interactorFactory = interactorFactory;
    }

    @Override
    public QuizPresenterFactory<AndroidViewQuestion> createQuizPresenterFactory() {
        return new QuizPresenterFactoryImpl<>(viewQuestionFactory, interactorFactory);
    }
}
