package com.davidcryer.trumpquotes.android.view.models.factories;

import com.davidcryer.trumpquotes.android.view.models.QuotesAndroidViewModel;
import com.davidcryer.trumpquotes.android.view.models.QuotesViewModel;

public class QuotesAndroidViewModelFactoryImpl implements QuotesAndroidViewModelFactory {
    private final static boolean DEFAULT_SCREEN_CHANGED = false;

    private QuotesAndroidViewModelFactoryImpl() {

    }

    public static QuotesAndroidViewModelFactory newInstance() {
        return new QuotesAndroidViewModelFactoryImpl();
    }

    @Override
    public QuotesAndroidViewModel create() {
        return QuotesViewModel.newInstance(DEFAULT_SCREEN_CHANGED);
    }
}
