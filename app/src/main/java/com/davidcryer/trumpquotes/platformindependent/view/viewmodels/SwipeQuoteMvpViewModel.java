package com.davidcryer.trumpquotes.platformindependent.view.viewmodels;

import com.davidcryer.trumpquotes.platformindependent.view.viewmodels.models.ViewQuote;

public interface SwipeQuoteMvpViewModel<ViewQuoteType extends ViewQuote> extends MvpViewModel {
    ViewQuoteType quote();
    int correctAnswerCount();
    int questionCount();
}
