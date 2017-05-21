package com.davidcryer.trumpquotes.android.view.uimodels;

public class QuizUiModelFactory {
    private final static QuizUiModel.State DEFAULT_STATE = QuizUiModel.State.LOADING_NEW_GAME;
    private final static QuizUiModel.GameState DEFAULT_GAME_STATE = QuizUiModel.GameState.NOT_INITIALISED;
    private final static int DEFAULT_CORRECT_ANSWER_COUNT = 0;
    private final static int DEFAULT_QUESTION_COUNT = 0;

    private QuizUiModelFactory() {}

    public static QuizUiModel create() {
        return new QuizUiModel(
                DEFAULT_STATE,
                null,
                DEFAULT_GAME_STATE,
                DEFAULT_CORRECT_ANSWER_COUNT,
                DEFAULT_QUESTION_COUNT
        );
    }
}
