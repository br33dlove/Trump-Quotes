package com.davidcryer.trumpquotes.android.view.viewmodels;

import com.davidcryer.trumpquotes.platformindependent.view.viewmodels.SwipeQuoteMvpViewModel;

public class SwipeQuoteAndroidViewModelFactoryImpl implements SwipeQuoteAndroidViewModelFactory {
    private final static SwipeQuoteAndroidViewModelImpl.State DEFAULT_STATE = SwipeQuoteAndroidViewModelImpl.State.LOADING;
    private final static SwipeQuoteAndroidViewModel.GameState DEFAULT_GAME_STATE = SwipeQuoteMvpViewModel.GameState.NOT_INITIALISED;
    private final static int DEFAULT_CORRECT_ANSWER_COUNT = 0;
    private final static int DEFAULT_QUESTION_COUNT = 0;
    private final static boolean DEFAULT_SHOW_NEW_GAME_TUTORIAL = false;
    private final static boolean DEFAULT_QUESTION_UPDATED = false;

    @Override
    public SwipeQuoteAndroidViewModel create() {
        return new SwipeQuoteAndroidViewModelImpl(
                DEFAULT_STATE,
                null,
                DEFAULT_GAME_STATE,
                DEFAULT_SHOW_NEW_GAME_TUTORIAL,
                DEFAULT_QUESTION_UPDATED,
                DEFAULT_CORRECT_ANSWER_COUNT,
                DEFAULT_QUESTION_COUNT
        );
    }
}
