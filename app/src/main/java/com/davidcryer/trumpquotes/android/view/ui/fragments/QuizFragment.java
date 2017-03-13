package com.davidcryer.trumpquotes.android.view.ui.fragments;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.davidcryer.trumpquotes.R;
import com.davidcryer.trumpquotes.android.framework.viewwrapperrepositories.ViewUnbindType;
import com.davidcryer.trumpquotes.android.framework.viewwrapperrepositories.ViewWrapperRepository;
import com.davidcryer.trumpquotes.android.view.ui.QuizAndroidView;
import com.davidcryer.trumpquotes.android.view.ui.components.QuoteCard;
import com.davidcryer.trumpquotes.android.view.ui.components.SwipeLayout;
import com.davidcryer.trumpquotes.android.view.ui.helpers.nongeneric.StartNewGameContainerAnimationHelper;
import com.davidcryer.trumpquotes.android.view.ui.swipe.SwipeDelegate;
import com.davidcryer.trumpquotes.android.view.viewmodels.models.AndroidViewQuestion;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class QuizFragment extends ViewBindingFragment<QuizAndroidView.EventsListener> implements QuizAndroidView {
    private Unbinder unbinder;
    @BindView(R.id.quote_card)
    QuoteCard card;
    @BindView(R.id.swipe_refresh)
    SwipeRefreshLayout swipeRefreshLayout;
    @BindView(R.id.swipe_layout)
    SwipeLayout swipeLayout;
    @BindView(R.id.start_new_game_container)
    View startNewGameContainer;
    @BindView(R.id.start_new_game_info)
    TextView startNewGameInfoTextView;
    @BindView(R.id.start_new_game_button)
    Button startNewGameButton;

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
        swipeRefreshLayout.setEnabled(false);
        swipeLayout.swipeListener(new SwipeDelegate.Listener() {
            @Override
            public void onViewEscapedLeft(View child) {
                if (hasEventsListener()) {
                    eventsListener().onAnswerOptionA();
                }
                swipeLayout.listenForChildGestures(child, false);
                //TODO clean up view
            }

            @Override
            public void onViewEscapedRight(View child) {
                if (hasEventsListener()) {
                    eventsListener().onAnswerOptionB();
                }
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
        if (hasEventsListener()) {
            eventsListener().onViewCreated();
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        if (hasEventsListener()) {
            eventsListener().onSaveInstance(outState);
        }
    }

    @Override
    public void showScore(int correctAnswerCount, int questionCount) {
        //TODO
    }

    @Override
    public void showNewGameStateStart() {
        showStartNewGameViews();
        startNewGameInfoTextView.setText(getString(R.string.game_start_game_info_description));
        startNewGameButton.setEnabled(true);
        startNewGameButton.setText(getString(R.string.game_start_game_button));
    }

    @Override
    public void showNewGameStateLoading() {
        showStartNewGameViews();
        startNewGameButton.setEnabled(false);
        startNewGameButton.setText("");
    }

    @Override
    public void showNewGameStateError() {
        showStartNewGameViews();
        startNewGameInfoTextView.setText(getString(R.string.game_start_game_info_description));
        startNewGameButton.setEnabled(true);
        startNewGameButton.setText(getString(R.string.game_start_game_button));
    }

    @Override
    public void hideNewGameState() {
        hideStartNewGameContainer();
    }

    @Override
    public void showPlayGameStateTutorial() {
        showPlayGameViews();
    }

    @Override
    public void showPlayGameStateRunning() {
        showPlayGameViews();
    }

    @Override
    public void showPlayGameStateFinished() {
        showStartNewGameViews();
        startNewGameInfoTextView.setText(getString(R.string.game_start_game_info_description));
        startNewGameButton.setEnabled(true);
        startNewGameButton.setText(getString(R.string.game_start_game_button));
    }

    @Override
    public void hidePlayGameState() {
        hidePlayGameViews();
    }

    @Override
    public void showQuestion(AndroidViewQuestion question) {
        showPlayGameViews();//TODO remove later, need to fix showPlayGameStateTutorial and showPlayGameStateRunning not being called
        card.quote(question.quote());
        card.signatures(question.optionA(), question.optionB());
        swipeLayout.listenForChildGestures(card, true);
        if (ViewCompat.isLaidOut(card)) {//TODO test
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

    private void showStartNewGameViews() {
        StartNewGameContainerAnimationHelper.slideIn(startNewGameContainer, swipeRefreshLayout);
    }

    private void hideStartNewGameContainer() {
        StartNewGameContainerAnimationHelper.slideOut(startNewGameContainer, swipeRefreshLayout);
    }

    private void showPlayGameViews() {
        showQuoteCard();
    }

    private void hidePlayGameViews() {
        hideQuoteCard();
    }

    private void showQuoteCard() {
        card.setVisibility(View.VISIBLE);
    }

    private void hideQuoteCard() {
        card.setVisibility(View.GONE);
    }

    @OnClick(R.id.start_new_game_button)
    public void startNewGame() {
        if (hasEventsListener()) {
            eventsListener().onClickStartNewGame();
        }
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
