package com.davidcryer.trumpquotes.android.view.viewmodels;

import com.davidcryer.trumpquotes.platformindependent.view.viewmodels.QuizViewModel;

public class QuizAndroidViewModelFactoryImpl implements QuizAndroidViewModelFactory {
    private final static QuizAndroidViewModelImpl.State DEFAULT_STATE = QuizAndroidViewModelImpl.State.LOADING_NEW_GAME;
    private final static QuizAndroidViewModel.GameState DEFAULT_GAME_STATE = QuizViewModel.GameState.NOT_INITIALISED;
    private final static int DEFAULT_CORRECT_ANSWER_COUNT = 0;
    private final static int DEFAULT_QUESTION_COUNT = 0;

    @Override
    public QuizAndroidViewModel create() {
        return new QuizAndroidViewModelImpl(
                DEFAULT_STATE,
                null,
                DEFAULT_GAME_STATE,
                DEFAULT_CORRECT_ANSWER_COUNT,
                DEFAULT_QUESTION_COUNT
        );
    }
}