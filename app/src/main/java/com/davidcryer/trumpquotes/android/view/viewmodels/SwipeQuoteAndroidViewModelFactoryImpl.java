package com.davidcryer.trumpquotes.android.view.viewmodels;

public class SwipeQuoteAndroidViewModelFactoryImpl implements SwipeQuoteAndroidViewModelFactory {
    private final static SwipeQuoteAndroidViewModelImpl.State DEFAULT_STATE = SwipeQuoteAndroidViewModelImpl.State.LOADING;
    private final static int DEFAULT_CORRECT_ANSWER_COUNT = 0;
    private final static int DEFAULT_QUESTION_COUNT = 0;
    private final static boolean DEFAULT_NEW_QUOTE_UPDATED = false;

    @Override
    public SwipeQuoteAndroidViewModel create() {
        return new SwipeQuoteAndroidViewModelImpl(
                null,
                DEFAULT_STATE,
                DEFAULT_NEW_QUOTE_UPDATED,
                DEFAULT_CORRECT_ANSWER_COUNT,
                DEFAULT_QUESTION_COUNT
        );
    }
}
