package com.davidcryer.trumpquotes.platformindependent.view.viewmodels;

import com.davidcryer.trumpquotes.platformindependent.view.viewmodels.models.ViewQuestion;

public interface SwipeQuoteMvpViewModel<ViewQuoteType extends ViewQuestion> extends MvpViewModel {
    ViewQuoteType quote();
    int correctAnswerCount();
    int questionCount();
}
