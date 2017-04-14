package com.davidcryer.trumpquotes.android.view.uimodels;

import com.davidcryer.trumpquotes.platformindependent.view.viewmodels.QuizViewModel;

public class QuizUiModelFactoryImpl implements QuizUiModelFactory {
    private final static QuizUiModelImpl.State DEFAULT_STATE = QuizUiModelImpl.State.LOADING_NEW_GAME;
    private final static QuizUiModel.GameState DEFAULT_GAME_STATE = QuizViewModel.GameState.NOT_INITIALISED;
    private final static int DEFAULT_CORRECT_ANSWER_COUNT = 0;
    private final static int DEFAULT_QUESTION_COUNT = 0;

    @Override
    public QuizUiModel create() {
        return new QuizUiModelImpl(
                DEFAULT_STATE,
                null,
                DEFAULT_GAME_STATE,
                DEFAULT_CORRECT_ANSWER_COUNT,
                DEFAULT_QUESTION_COUNT
        );
    }
}
