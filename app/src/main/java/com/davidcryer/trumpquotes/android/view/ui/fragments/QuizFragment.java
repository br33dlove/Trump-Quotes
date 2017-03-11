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
import com.davidcryer.trumpquotes.android.view.ui.QuizAndroidView;
import com.davidcryer.trumpquotes.android.view.ui.components.QuoteCard;
import com.davidcryer.trumpquotes.android.view.ui.components.SwipeLayout;
import com.davidcryer.trumpquotes.android.view.ui.helpers.AlphaAnimationHelper;
import com.davidcryer.trumpquotes.android.view.ui.swipe.SwipeDelegate;
import com.davidcryer.trumpquotes.android.view.viewmodels.models.AndroidViewQuestion;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class QuizFragment extends ViewBindingFragment<QuizAndroidView.EventsListener> implements QuizAndroidView {
    private final static long ANIMATION_DURATION_MAX_FADE = 300;
    private Unbinder unbinder;
    @BindView(R.id.quote_card)
    QuoteCard card;
    @BindView(R.id.swipe_refresh)
    SwipeRefreshLayout swipeRefreshLayout;
    @BindView(R.id.swipe_layout)
    SwipeLayout swipeLayout;
    @BindView(R.id.loading_failed)
    View loadingFailedTextView;

    public static QuizFragment newInstance() {
        return new QuizFragment();
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
        setupViews();
        return view;
    }

    private void initialiseViewReferences(final View root) {
        unbinder = ButterKnife.bind(this, root);
    }

    private void setupViews() {
        swipeLayout.swipeListener(new SwipeDelegate.Listener() {
            @Override
            public void onViewEscapedLeft(View child) {
                eventsListener.onAnswerOptionA();
                swipeLayout.listenForChildGestures(child, false);
                //TODO clean up view
            }

            @Override
            public void onViewEscapedRight(View child) {
                eventsListener.onAnswerOptionB();
                swipeLayout.listenForChildGestures(child, false);
                //TODO clean up view
            }

            @Override
            public void onCardMoved(float percentageOffsetFromCentreX) {
                card.updateSignatureAlpha(percentageOffsetFromCentreX);
            }
        });
        card.setVisibility(View.GONE);
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
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        eventsListener.onViewCreated();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        eventsListener.onSaveInstance(outState);
    }

    @Override
    public void showScore(int correctAnswerCount, int questionCount) {
        //TODO
    }

    @Override
    public void showStartNewGameState() {
        //TODO
    }

    @Override
    public void showLoadingState() {
        hideQuoteCard();
        showLoadingQuote();
        hideFailureToStartGameViews();
        swipeRefreshLayout.setEnabled(false);
    }

    @Override
    public void showNewGameTutorial() {
        //TODO
    }

    @Override
    public void dismissNewGameTutorial() {
        //TODO
    }

    @Override
    public void showQuestionState(AndroidViewQuestion question) {
        showQuoteCard();
        hideLoadingViews();
        hideFailureToStartGameViews();
        swipeRefreshLayout.setEnabled(false);
        card.quote(question.quote());
        card.signatures(question.optionA(), question.optionB());
        swipeLayout.listenForChildGestures(card, true);
        if (card.getWidth() > 0) {
            final ViewGroup.MarginLayoutParams cardLp = (ViewGroup.MarginLayoutParams) card.getLayoutParams();
            final int xOrigin = cardLp.leftMargin;
            final int yOrigin = cardLp.topMargin;
            card.setX(xOrigin);
            card.setY(yOrigin);
            card.invalidate();
        }
        card.updateSignatureAlpha(0);
        //TODO setup card (slide into view?)
    }

    @Override
    public void showFailureToStartGameState() {
        hideQuoteCard();
        hideLoadingViews();
        showFailureToGetQuote();
        swipeRefreshLayout.setEnabled(true);
    }

    public void showQuoteCard() {
        card.setVisibility(View.VISIBLE);
    }

    public void hideQuoteCard() {
        card.setVisibility(View.GONE);
    }

    public void showLoadingQuote() {
        swipeRefreshLayout.post(new Runnable() {
            @Override
            public void run() {
                swipeRefreshLayout.setRefreshing(true);
            }
        });
    }

    public void hideLoadingViews() {
        swipeRefreshLayout.post(new Runnable() {
            @Override
            public void run() {
                swipeRefreshLayout.setRefreshing(false);
            }
        });
    }

    public void showFailureToGetQuote() {
        AlphaAnimationHelper.fadeIn(loadingFailedTextView, ANIMATION_DURATION_MAX_FADE);
    }

    public void hideFailureToStartGameViews() {
        AlphaAnimationHelper.fadeOut(loadingFailedTextView, ANIMATION_DURATION_MAX_FADE);
    }

    @Override
    public void showFinishedGameState() {
        //TODO
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    protected QuizAndroidView.EventsListener bind(ViewWrapperRepository viewWrapperRepository, final Bundle savedState) {
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
