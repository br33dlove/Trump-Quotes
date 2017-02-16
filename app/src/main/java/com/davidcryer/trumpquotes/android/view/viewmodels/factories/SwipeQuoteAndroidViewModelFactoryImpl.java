package com.davidcryer.trumpquotes.android.view.viewmodels.factories;

import com.davidcryer.trumpquotes.android.view.viewmodels.SwipeQuoteAndroidViewModel;
import com.davidcryer.trumpquotes.android.view.viewmodels.SwipeQuoteViewModel;
import com.davidcryer.trumpquotes.android.view.viewmodels.models.AndroidViewQuote;

import java.util.ArrayList;

public class SwipeQuoteAndroidViewModelFactoryImpl implements QuotesAndroidViewModelFactory {
    private final static boolean DEFAULT_SHOW_FAILURE_TO_GET_NEW_QUOTE = false;
    private final static boolean DEFAULT_SHOW_LOADING_NEW_QUOTE = false;
    private final static boolean DEFAULT_QUOTE_HISTORY_UPDATED = false;
    private final static boolean DEFAULT_NEW_QUOTE_UPDATED = false;

    private SwipeQuoteAndroidViewModelFactoryImpl() {

    }

    public static QuotesAndroidViewModelFactory newInstance() {
        return new SwipeQuoteAndroidViewModelFactoryImpl();
    }

    @Override
    public SwipeQuoteAndroidViewModel create() {
        return new SwipeQuoteViewModel(
                null,
                new ArrayList<AndroidViewQuote>(),
                DEFAULT_SHOW_FAILURE_TO_GET_NEW_QUOTE,
                DEFAULT_SHOW_LOADING_NEW_QUOTE,
                DEFAULT_QUOTE_HISTORY_UPDATED,
                DEFAULT_NEW_QUOTE_UPDATED
        );
    }
}
