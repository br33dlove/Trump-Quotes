package com.davidcryer.trumpquotes.android.view.viewmodels.factories;

import com.davidcryer.trumpquotes.android.view.viewmodels.SwipeQuoteAndroidViewModel;
import com.davidcryer.trumpquotes.android.view.viewmodels.SwipeQuoteViewModel;
import com.davidcryer.trumpquotes.android.view.viewmodels.models.AndroidViewQuote;

import java.util.ArrayList;

public class SwipeQuoteAndroidViewModelFactoryImpl implements SwipeQuoteAndroidViewModelFactory {
    private final static SwipeQuoteViewModel.State DEFAULT_STATE = SwipeQuoteViewModel.State.LOADING;
    private final static boolean DEFAULT_NEW_QUOTE_UPDATED = false;

    @Override
    public SwipeQuoteAndroidViewModel create() {
        return new SwipeQuoteViewModel(
                null,
                DEFAULT_STATE,
                DEFAULT_NEW_QUOTE_UPDATED
        );
    }
}
