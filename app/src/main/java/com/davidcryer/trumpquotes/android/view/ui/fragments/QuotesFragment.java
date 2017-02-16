package com.davidcryer.trumpquotes.android.view.ui.fragments;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.davidcryer.trumpquotes.R;
import com.davidcryer.trumpquotes.android.framework.viewwrapperrepositories.ViewUnbindType;
import com.davidcryer.trumpquotes.android.framework.viewwrapperrepositories.ViewWrapperRepository;
import com.davidcryer.trumpquotes.android.view.ui.QuotesAndroidView;
import com.davidcryer.trumpquotes.android.view.ui.components.QuoteCard;
import com.davidcryer.trumpquotes.android.view.viewmodels.models.AndroidViewQuote;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class QuotesFragment extends ViewBindingFragment<QuotesAndroidView.EventsListener> implements QuotesAndroidView {
    private Unbinder unbinder;
    @BindView(R.id.quote_card)
    QuoteCard card;
    @BindView(R.id.swipe_refresh)
    SwipeRefreshLayout swipeRefreshLayout;
    @BindView(R.id.loading_progress_bar)
    View loadingView;
    @BindView(R.id.loading_failed)
    View loadingFailedTextView;

    public static QuotesFragment newInstance() {
        return new QuotesFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_quotes, container, false);
        initialiseViewReferences(view);
        return view;
    }

    private void initialiseViewReferences(final View root) {
        unbinder = ButterKnife.bind(this, root);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.quotes_menu, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        eventsListener.onSaveInstance(outState);
    }

    @Override
    public void showFailureToGetNewQuote() {

    }

    @Override
    public void showLoadingNewQuote() {

    }

    @Override
    public void hideLoadingNewQuote() {

    }

    @Override
    public void showNewQuote(AndroidViewQuote quote) {

    }

    @Override
    public void showQuoteHistory(List<AndroidViewQuote> quotes) {

    }

    @Override
    public void updateQuoteHistoryForInsert(int index) {

    }

    @Override
    public void updateQuoteHistoryForRemoval(int index) {

    }

    @Override
    public void removeAllQuotesInHistory() {

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    protected QuotesAndroidView.EventsListener bind(ViewWrapperRepository viewWrapperRepository, final Bundle savedState) {
        return viewWrapperRepository.bind(this, savedState);
    }

    @Override
    protected void unbind(ViewWrapperRepository viewWrapperRepository, ViewUnbindType unbindType) {
        viewWrapperRepository.unbind(this, unbindType);
    }

    @Override
    public Activity activity() {
        return getActivity();
    }
}
