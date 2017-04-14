package com.davidcryer.trumpquotes.android.framework.activities;

import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;

import com.davidcryer.trumpquotes.R;
import com.davidcryer.trumpquotes.android.framework.uiwrapperrepositories.UiWrapperRepositoryImpl;
import com.davidcryer.trumpquotes.android.helpers.FragmentManagerHelper;
import com.davidcryer.trumpquotes.android.view.ui.fragments.QuizFragment;
import com.example.davidc.uiwrapper.UiWrapperRepositoryActivity;

import static com.davidcryer.trumpquotes.android.helpers.FragmentManagerHelper.addFragment;
import static com.davidcryer.trumpquotes.android.helpers.FragmentManagerHelper.noFragmentBoundToView;

public class SwipeQuoteActivity extends UiWrapperRepositoryActivity<UiWrapperRepositoryImpl> {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_app_bar_with_content);
        setupToolbar();
        addQuotesFragment();
    }

    private void setupToolbar() {
        setSupportActionBar((Toolbar) findViewById(R.id.toolbar));
        final ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayShowHomeEnabled(false);
            actionBar.setDisplayHomeAsUpEnabled(false);
        }
    }

    private void addQuotesFragment() {
        final FragmentManager fragmentManager = getSupportFragmentManager();
        if (noFragmentBoundToView(fragmentManager, getQuotesFragmentViewContainer())) {
            addFragment(fragmentManager, QuizFragment.newInstance(), getQuotesFragmentViewContainer());
        }
    }

    @IdRes
    private int getQuotesFragmentViewContainer() {
        return R.id.content;
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    @Override
    public void onBackPressed() {
        final FragmentManager fragmentManager = getSupportFragmentManager();
        if (FragmentManagerHelper.hasMoreThanOneNonRetainedFragment(fragmentManager)) {
            fragmentManager.popBackStack();
            return;
        }
        super.onBackPressed();
    }
}
