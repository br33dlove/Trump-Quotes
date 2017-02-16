package com.davidcryer.trumpquotes.android.view.viewmodels.factories;

import com.davidcryer.trumpquotes.android.view.viewmodels.QuoteHistoryAndroidViewModel;
import com.davidcryer.trumpquotes.android.view.viewmodels.QuoteHistoryViewModel;
import com.davidcryer.trumpquotes.android.view.viewmodels.models.AndroidViewQuote;

import java.util.ArrayList;

public class QuoteHistoryAndroidViewModelFactoryImpl implements QuoteHistoryAndroidViewModelFactory {
    private final static boolean DEFAULT_QUOTE_HISTORY_UPDATED = false;

    @Override
    public QuoteHistoryAndroidViewModel create() {
        return new QuoteHistoryViewModel(
                new ArrayList<AndroidViewQuote>(),
                DEFAULT_QUOTE_HISTORY_UPDATED
        );
    }
}
