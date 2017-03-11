package com.davidcryer.trumpquotes.android.presenter.presenters;

import com.davidcryer.trumpquotes.android.view.viewmodels.models.AndroidViewQuestion;
import com.davidcryer.trumpquotes.platformindependent.presenter.presenters.QuizPresenterFactory;

public interface PresenterFactoryFactory {
    QuizPresenterFactory<AndroidViewQuestion> createQuizPresenterFactory();
}
