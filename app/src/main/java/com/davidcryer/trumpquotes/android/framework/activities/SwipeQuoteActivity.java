package com.davidcryer.trumpquotes.android.framework.activities;

import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;

import com.davidc.uiwrapper.SingleContentContainerWithAppBarActivity;
import com.davidcryer.trumpquotes.android.framework.uiwrapperrepositories.UiWrapperRepository;
import com.davidcryer.trumpquotes.android.view.ui.fragments.QuizFragment;

public class SwipeQuoteActivity extends SingleContentContainerWithAppBarActivity<UiWrapperRepository> {

    @Override
    protected void setupActionBar(@NonNull ActionBar actionBar) {
        actionBar.setDisplayShowHomeEnabled(false);
        actionBar.setDisplayHomeAsUpEnabled(false);
    }

    @Override
    @NonNull
    protected Fragment initialFragment() {
        return QuizFragment.newInstance();
    }
}
