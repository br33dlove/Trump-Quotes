package com.davidcryer.trumpquotes.android.view.viewmodels.factories;

import com.davidcryer.trumpquotes.android.view.viewmodels.SwipeQuoteAndroidViewModel;
import com.davidcryer.trumpquotes.android.view.viewmodels.SwipeQuoteViewModel;
import com.davidcryer.trumpquotes.android.view.viewmodels.models.AndroidViewQuote;

import java.util.ArrayList;

public class SwipeQuoteAndroidViewModelFactoryImpl implements SwipeQuoteAndroidViewModelFactory {
    private final static boolean DEFAULT_SHOW_FAILURE_TO_GET_NEW_QUOTE = false;
    private final static boolean DEFAULT_SHOW_LOADING_NEW_QUOTE = false;
    private final static boolean DEFAULT_NEW_QUOTE_UPDATED = false;

    @Override
    public SwipeQuoteAndroidViewModel create() {
        return new SwipeQuoteViewModel(
                null,
                DEFAULT_SHOW_FAILURE_TO_GET_NEW_QUOTE,
                DEFAULT_SHOW_LOADING_NEW_QUOTE,
                DEFAULT_NEW_QUOTE_UPDATED
        );
    }
}
