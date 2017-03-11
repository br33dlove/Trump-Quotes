package com.davidcryer.trumpquotes.android.view.viewmodels;

import android.os.Parcel;

import com.davidcryer.trumpquotes.android.view.ui.SwipeQuoteAndroidView;
import com.davidcryer.trumpquotes.android.view.viewmodels.models.AndroidViewQuestion;

final class SwipeQuoteAndroidViewModelImpl implements SwipeQuoteAndroidViewModel {
    enum State {START_NEW_GAME, LOADING, FAILURE_TO_START_GAME, QUESTION, GAME_FINISHED}
    private State state;
    private AndroidViewQuestion question;
    private GameState gameState;
    private boolean showNewGameTutorial;
    private boolean questionUpdated;
    private int correctAnswers;
    private int questionsAnswered;

    SwipeQuoteAndroidViewModelImpl(
            State state,
            AndroidViewQuestion question,
            GameState gameState,
            boolean showNewGameTutorial,
            boolean questionUpdated,
            int correctAnswers,
            int questionsAnswered
    ) {
        this.state = state;
        this.question = question;
        this.gameState = gameState;
        this.showNewGameTutorial = showNewGameTutorial;
        this.questionUpdated = questionUpdated;
        this.correctAnswers = correctAnswers;
        this.questionsAnswered = questionsAnswered;
    }

    private SwipeQuoteAndroidViewModelImpl(final Parcel parcel) {
        state = (State) parcel.readSerializable();
        question = parcel.readParcelable(AndroidViewQuestion.class.getClassLoader());
        gameState = (GameState) parcel.readSerializable();
        showNewGameTutorial = parcel.readByte() != 0;
        questionUpdated = parcel.readByte() != 0;
        correctAnswers = parcel.readInt();
        questionsAnswered = parcel.readInt();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeSerializable(state);
        dest.writeParcelable(question, PARCELABLE_WRITE_RETURN_VALUE);
        dest.writeSerializable(gameState);
        dest.writeByte((byte) (showNewGameTutorial ? 1 : 0));
        dest.writeByte((byte) (questionUpdated ? 1 : 0));
        dest.writeInt(correctAnswers);
        dest.writeInt(questionsAnswered);
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
        this.correctAnswers = correctAnswerCount;
        this.questionsAnswered = questionCount;
        if (view != null) {
            view.showScore(correctAnswerCount, questionCount);
        }
    }

    @Override
    public void showStartNewGameState(SwipeQuoteAndroidView view) {
        state = State.START_NEW_GAME;
        gameState = GameState.NOT_INITIALISED;
        if (view != null) {
            view.showStartNewGameState();
        }
    }

    @Override
    public void showLoadingState(SwipeQuoteAndroidView view) {
        state = State.LOADING;
        if (view != null) {
            view.showStartNewGameState();
        }
    }

    @Override
    public void showFailureToStartGameState(SwipeQuoteAndroidView view) {
        state = State.FAILURE_TO_START_GAME;
        gameState = GameState.NOT_INITIALISED;
        if (view != null) {
            view.showStartNewGameState();
        }
    }

    @Override
    public void showNewGameTutorial(SwipeQuoteAndroidView view) {
        showNewGameTutorial = true;
        if (view != null) {
            view.showStartNewGameState();
        }
    }

    @Override
    public void dismissNewGameTutorial(SwipeQuoteAndroidView view) {
        showNewGameTutorial = false;
        if (view != null) {
            view.showStartNewGameState();
        }
    }

    @Override
    public void showQuestionState(SwipeQuoteAndroidView view, AndroidViewQuestion question) {
        state = State.QUESTION;
        gameState = GameState.RUNNING;
        this.question = question;
        if (view != null) {
            view.showQuestionState(question);
        }
    }

    @Override
    public void showFinishedGameState(SwipeQuoteAndroidView view) {
        state = State.GAME_FINISHED;
        gameState = GameState.FINISHED;
        question = null;
        if (view != null) {
            view.showFinishedGameState();
        }
    }

    @Override
    public void onto(SwipeQuoteAndroidView view, final boolean setAllData) {
        if (setAllData) {
            if (showNewGameTutorial) {
                view.showNewGameTutorial();
            }
        }
        switch (state) {
            case START_NEW_GAME: {
                if (setAllData) {
                    view.showStartNewGameState();
                }
                break;
            }
            case LOADING: {
                if (setAllData) {
                    view.showLoadingState();
                }
                break;
            }
            case FAILURE_TO_START_GAME: {
                if (setAllData) {
                    view.showFailureToStartGameState();
                }
                break;
            }
            case QUESTION: {
                if (setAllData || questionUpdated) {
                    questionUpdated = false;
                    view.showQuestionState(question);
                    view.showScore(correctAnswers, questionsAnswered);
                }
                break;
            }
            case GAME_FINISHED: {
                if (setAllData) {
                    view.showFinishedGameState();
                    view.showScore(correctAnswers, questionsAnswered);
                }
                break;
            }
        }
    }

    @Override
    public GameState gameState() {
        return gameState;
    }
}
