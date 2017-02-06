package com.davidcryer.trumpquotes.android.view.viewmodels.factories;

import com.davidcryer.trumpquotes.android.view.viewmodels.QuotesAndroidViewModel;
import com.davidcryer.trumpquotes.android.view.viewmodels.QuotesViewModel;
import com.davidcryer.trumpquotes.android.view.viewmodels.models.AndroidViewQuote;

import java.util.ArrayList;

public class QuotesAndroidViewModelFactoryImpl implements QuotesAndroidViewModelFactory {
    private final static boolean DEFAULT_SHOW_FAILURE_TO_GET_NEW_QUOTE = false;
    private final static boolean DEFAULT_SHOW_LOADING_NEW_QUOTE = false;
    private final static boolean DEFAULT_QUOTE_HISTORY_UPDATED = false;
    private final static boolean DEFAULT_NEW_QUOTE_UPDATED = false;

    private QuotesAndroidViewModelFactoryImpl() {

    }

    public static QuotesAndroidViewModelFactory newInstance() {
        return new QuotesAndroidViewModelFactoryImpl();
    }

    @Override
    public QuotesAndroidViewModel create() {
        return new QuotesViewModel(
                null,
                new ArrayList<AndroidViewQuote>(),
                DEFAULT_SHOW_FAILURE_TO_GET_NEW_QUOTE,
                DEFAULT_SHOW_LOADING_NEW_QUOTE,
                DEFAULT_QUOTE_HISTORY_UPDATED,
                DEFAULT_NEW_QUOTE_UPDATED
        );
    }
}
