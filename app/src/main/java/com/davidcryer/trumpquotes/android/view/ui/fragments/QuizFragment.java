package com.davidcryer.trumpquotes.android.view.ui.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.DecelerateInterpolator;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.davidc.uiwrapper.UiFragment;
import com.davidcryer.trumpquotes.R;
import com.davidcryer.trumpquotes.android.framework.uiwrapperrepositories.UiWrapperRepository;
import com.davidcryer.trumpquotes.android.view.ui.QuizUi;
import com.davidcryer.trumpquotes.android.view.ui.components.QuoteCard;
import com.davidcryer.trumpquotes.android.view.ui.components.SwipeLayout;
import com.davidcryer.trumpquotes.android.view.ui.helpers.AlphaAnimationHelper;
import com.davidcryer.trumpquotes.android.view.ui.helpers.OnGlobalLayoutHelper;
import com.davidcryer.trumpquotes.android.view.ui.helpers.nongeneric.ScoreViewAnimationHelper;
import com.davidcryer.trumpquotes.android.view.ui.helpers.nongeneric.StartNewGameContainerAnimationHelper;
import com.davidcryer.trumpquotes.android.view.ui.swipe.SwipeDelegate;
import com.davidcryer.trumpquotes.android.view.uimodels.models.AndroidViewQuestion;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class QuizFragment extends UiFragment<UiWrapperRepository, QuizUi.Listener> implements QuizUi {
    private Unbinder unbinder;
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
    @BindView(R.id.score)
    TextView scoreTextView;

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
                swipeLayout.removeView(child);
            }

            @Override
            public void onViewEscapedRight(View child) {
                if (hasEventsListener()) {
                    eventsListener().onAnswerOptionB();
                }
                swipeLayout.removeView(child);
            }

            public void onViewMoved(final View child, float percentageOffsetFromCentreX) {
                if (child instanceof QuoteCard) {
                    ((QuoteCard) child).updateSignatureAlpha(percentageOffsetFromCentreX);
                }
            }
        });
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
    public void showNewGameStartState() {
        showStartNewGameViews();
        setupNewGameStartState();
    }

    @Override
    public void animateInNewGameStartState() {
        animateInStartNewGameViews();
        setupNewGameStartState();
    }

    private void setupNewGameStartState() {
        swipeRefreshLayout.post(new Runnable() {
            @Override
            public void run() {
                swipeRefreshLayout.setRefreshing(false);
            }
        });
        startNewGameInfoTextView.setText(getString(R.string.start_game_info_description));
        startNewGameButton.setVisibility(View.VISIBLE);
        startNewGameButton.setEnabled(true);
        startNewGameButton.setText(getString(R.string.start_game_button));
    }

    @Override
    public void showNewGameLoadingState() {
        showStartNewGameViews();
        startNewGameButton.setVisibility(View.GONE);
        setupNewGameLoadingState();
    }

    @Override
    public void animateInNewGameLoadingState() {
        AlphaAnimationHelper.fadeOut(startNewGameButton, getResources().getInteger(R.integer.max_duration_fade_score_ms), View.INVISIBLE);
        setupNewGameLoadingState();
    }

    private void setupNewGameLoadingState() {
        swipeRefreshLayout.post(new Runnable() {
            @Override
            public void run() {
                swipeRefreshLayout.setRefreshing(true);
            }
        });
    }

    @Override
    public void showNewGameFailedToStartState() {
        showStartNewGameViews();
        startNewGameButton.setVisibility(View.VISIBLE);
        setupNewGameFailedToLoadGameState();
    }

    @Override
    public void animateInNewGameFailedToLoadGameState() {
        AlphaAnimationHelper.fadeIn(startNewGameButton, getResources().getInteger(R.integer.max_duration_fade_score_ms));
        setupNewGameFailedToLoadGameState();
    }

    private void setupNewGameFailedToLoadGameState() {
        swipeRefreshLayout.post(new Runnable() {
            @Override
            public void run() {
                swipeRefreshLayout.setRefreshing(false);
            }
        });
        startNewGameInfoTextView.setText(getString(R.string.start_game_info_load_failed));
        startNewGameButton.setEnabled(true);
        startNewGameButton.setText(getString(R.string.start_game_button));
    }

    @Override
    public void hideNewGameScene() {
        swipeRefreshLayout.post(new Runnable() {
            @Override
            public void run() {
                swipeRefreshLayout.setRefreshing(false);
            }
        });
        hideStartNewGameContainer();
    }

    @Override
    public void animateOutNewGameScene(Runnable endAction) {
        swipeRefreshLayout.post(new Runnable() {
            @Override
            public void run() {
                swipeRefreshLayout.setRefreshing(false);
            }
        });
        animateOutStartNewGameContainer(endAction);
    }

    @Override
    public void showScore(int correctAnswerCount, int questionCount) {
        scoreTextView.setVisibility(View.VISIBLE);
        setupScore(correctAnswerCount, questionCount);
    }

    @Override
    public void animateInScore(int correctAnswerCount, int questionCount) {
        ScoreViewAnimationHelper.slideIn(scoreTextView, swipeRefreshLayout);
        setupScore(correctAnswerCount, questionCount);
    }

    private void setupScore(int correctAnswerCount, int questionCount) {
        scoreTextView.setText(String.format(getString(R.string.score_format), correctAnswerCount, questionCount));
    }

    @Override
    public void showQuestion(final AndroidViewQuestion question) {
        showPlayGameViews();
        setupQuestion(question, new CardPostLayoutRunnable() {
            @Override
            public void run(QuoteCard card) {
                card.setY(swipeRefreshLayout.getHeight() - card.getHeight() / 2.0f);
                swipeLayout.listenForChildGestures(card, true);
            }
        });
    }

    @Override
    public void animateInQuestion(AndroidViewQuestion question) {
        setupQuestion(question, new CardPostLayoutRunnable() {
            @Override
            public void run(final QuoteCard card) {
                card.setY(swipeRefreshLayout.getHeight() + getResources().getDimensionPixelOffset(R.dimen.card_buffer_offscreen));
                card.animate()
                        .y((swipeRefreshLayout.getHeight() - card.getHeight()) / 2.0f)
                        .setInterpolator(new DecelerateInterpolator())
                        .setDuration(300)
                        .withEndAction(new Runnable() {
                            @Override
                            public void run() {
                                swipeLayout.listenForChildGestures(card, true);
                            }
                        })
                        .start();
            }
        });
    }

    private void setupQuestion(final AndroidViewQuestion question, final CardPostLayoutRunnable postLayoutRunnable) {
        final int horizontalMargin = getResources().getDimensionPixelOffset(R.dimen.card_margin_horizontal);
        final QuoteCard card = new QuoteCard(getActivity());
        OnGlobalLayoutHelper.listen(card, new OnGlobalLayoutHelper.PreLayoutCallback() {
            @Override
            public void onPreLayout() {
                card.quote(question.quote());
                card.signatures(question.optionA(), question.optionB());
                card.updateSignatureAlpha(0);
                final FrameLayout.LayoutParams cardLp = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                cardLp.leftMargin = horizontalMargin;
                cardLp.rightMargin = horizontalMargin;
                card.setLayoutParams(cardLp);
                swipeLayout.addView(card);
            }
        }, new OnGlobalLayoutHelper.PostLayoutCallback() {
            @Override
            public void onPostLayout() {
                postLayoutRunnable.run(card);
            }
        });
    }

    private interface CardPostLayoutRunnable {
        void run(final QuoteCard card);
    }

    @Override
    public void hideGameInPlayScene() {
        hideScoreView();
    }

    @Override
    public void animateOutGameInPlayScene(Runnable endAction) {
        animateOutScoreView(endAction);
    }

    @Override
    public void showNewGameFinishedState(int correctAnswerCount, int questionCount) {
        showStartNewGameViews();
        setupNewGameFinishedState(correctAnswerCount, questionCount);
    }

    @Override
    public void animateInNewGameFinishedState(int correctAnswerCount, int questionCount) {
        animateInStartNewGameViews();
        setupNewGameFinishedState(correctAnswerCount, questionCount);
    }

    private void setupNewGameFinishedState(final int correctAnswerCount, final int questionCount) {
        startNewGameInfoTextView.setText(String.format(getString(R.string.start_game_info_finished), correctAnswerCount, questionCount));
        startNewGameButton.setEnabled(true);
        startNewGameButton.setText(getString(R.string.start_game_button));
        startNewGameButton.setVisibility(View.VISIBLE);
    }

    private void showStartNewGameViews() {
        startNewGameContainer.setVisibility(View.VISIBLE);
    }

    private void animateInStartNewGameViews() {
        StartNewGameContainerAnimationHelper.slideIn(startNewGameContainer, swipeRefreshLayout);
    }

    private void hideStartNewGameContainer() {
        startNewGameContainer.setVisibility(View.GONE);
    }

    private void animateOutStartNewGameContainer(final Runnable endAction) {
        StartNewGameContainerAnimationHelper.slideOut(startNewGameContainer, swipeRefreshLayout, endAction);
    }

    private void showPlayGameViews() {
        showScoreView();
    }

    private void showScoreView() {
        AlphaAnimationHelper.fadeIn(scoreTextView, getResources().getInteger(R.integer.max_duration_fade_score_ms));
    }

    private void hideScoreView() {
        scoreTextView.setVisibility(View.GONE);
    }

    private void animateOutScoreView(final Runnable endAction) {
        ScoreViewAnimationHelper.slideOut(scoreTextView, swipeRefreshLayout, endAction);
    }

    @SuppressWarnings("unused")
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
    protected Listener bind(UiWrapperRepository uiWrapperRepository, String instanceId, Bundle savedInstanceState) {
        return uiWrapperRepository.bind(this, instanceId, savedInstanceState);
    }

    @Override
    protected void unbind(UiWrapperRepository uiWrapperRepository, String instanceId, Bundle outState, boolean isConfigurationChange) {
        uiWrapperRepository.unbind(this, instanceId, outState, isConfigurationChange);
    }
}
