package com.davidcryer.trumpquotes.android.view.viewmodels;

import android.os.Parcel;

import com.davidcryer.trumpquotes.android.view.ui.SwipeQuoteAndroidView;
import com.davidcryer.trumpquotes.android.view.viewmodels.models.AndroidViewQuestion;

public final class SwipeQuoteAndroidViewModelImpl implements SwipeQuoteAndroidViewModel {
    enum State {START_NEW_GAME, LOADING, FAILURE_TO_START_GAME, QUESTION, GAME_FINISHED}
    private State state;
    private AndroidViewQuestion question;
    private GameState gameState;
    private boolean showNewGameTutorial;
    private boolean questionUpdated;
    private int correctAnswerCount;
    private int questionCount;

    public SwipeQuoteAndroidViewModelImpl(
            State state,
            AndroidViewQuestion question,
            GameState gameState,
            boolean showNewGameTutorial,
            boolean questionUpdated,
            int correctAnswerCount,
            int questionCount
    ) {
        this.state = state;
        this.question = question;
        this.gameState = gameState;
        this.showNewGameTutorial = showNewGameTutorial;
        this.questionUpdated = questionUpdated;
        this.correctAnswerCount = correctAnswerCount;
        this.questionCount = questionCount;
    }

    private SwipeQuoteAndroidViewModelImpl(final Parcel parcel) {
        question = parcel.readParcelable(AndroidViewQuestion.class.getClassLoader());
        gameState = (GameState) parcel.readSerializable();
        questionUpdated = parcel.readByte() != 0;
        correctAnswerCount = parcel.readInt();
        questionCount = parcel.readInt();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(question, PARCELABLE_WRITE_RETURN_VALUE);
        dest.writeSerializable(gameState);
        dest.writeByte((byte) (questionUpdated ? 1 : 0));
        dest.writeInt(correctAnswerCount);
        dest.writeInt(questionCount);
    }

    static final Creator<SwipeQuoteAndroidViewModel> CREATOR = new Creator<SwipeQuoteAndroidViewModel>() {
        @Override
        public SwipeQuoteAndroidViewModel createFromParcel(Parcel source) {
            return new SwipeQuoteAndroidViewModelImpl(source);
        }

        @Override
        public SwipeQuoteAndroidViewModel[] newArray(int size) {
            return new SwipeQuoteAndroidViewModel[size];
        }
    };

    @Override
    public void showScore(SwipeQuoteAndroidView view, int correctAnswerCount, int questionCount) {
        this.correctAnswerCount = correctAnswerCount;
        this.questionCount = questionCount;
        if (view != null) {
            view.showScore(correctAnswerCount, questionCount);
        }
    }

    @Override
    public void showStartNewGameState(SwipeQuoteAndroidView view) {
        state = State.START_NEW_GAME;
        gameState = GameState.NOT_INITIALISED;
    }

    @Override
    public void showLoadingState(SwipeQuoteAndroidView view) {
        state = State.LOADING;
    }

    @Override
    public void showFailureToStartGameState(SwipeQuoteAndroidView view) {
        state = State.FAILURE_TO_START_GAME;
        gameState = GameState.NOT_INITIALISED;
    }

    @Override
    public void showNewGameTutorial(SwipeQuoteAndroidView view) {
        showNewGameTutorial = true;
    }

    @Override
    public void dismissNewGameTutorial(SwipeQuoteAndroidView view) {
        showNewGameTutorial = false;
    }

    @Override
    public void showQuestionState(AndroidViewQuestion question) {
        state = State.QUESTION;
        gameState = GameState.RUNNING;
    }

    @Override
    public void showFinishedGameState(SwipeQuoteAndroidView view) {
        state = State.GAME_FINISHED;
        gameState = GameState.FINISHED;
    }

    @Override
    public void onto(SwipeQuoteAndroidView view, final boolean setAllData) {
        switch (state) {
            case START_NEW_GAME: {
                break;
            }
            case LOADING: {
                if (setAllData) {
                    view.showLoadingQuotesState();
                }
                break;
            }
            case FAILURE_TO_START_GAME: {
                break;
            }
            case QUESTION: {
                if (setAllData || questionUpdated) {
                    questionUpdated = false;
                    view.showQuoteState(question);
                    view.showScore(correctAnswerCount, questionCount);
                }
                break;
            }
            case GAME_FINISHED: {
                break;
            }
        }
    }

    @Override
    public GameState gameState() {
        return gameState;
    }
}
