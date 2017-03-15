package com.davidcryer.trumpquotes.android.view.viewmodels;

import android.os.Parcel;

import com.davidcryer.trumpquotes.android.view.ui.QuizAndroidView;
import com.davidcryer.trumpquotes.android.view.viewmodels.models.AndroidViewQuestion;

final class QuizAndroidViewModelImpl implements QuizAndroidViewModel {
    enum State {START_NEW_GAME, LOADING_NEW_GAME, FAILURE_TO_START_NEW_GAME, GAME_TUTORIAL, GAME_RUNNING, GAME_FINISHED}
    private State state;
    private AndroidViewQuestion question;
    private GameState gameState;
    private int correctAnswers;
    private int questionsAnswered;

    QuizAndroidViewModelImpl(
            State state,
            AndroidViewQuestion question,
            GameState gameState,
            int correctAnswers,
            int questionsAnswered
    ) {
        this.state = state;
        this.question = question;
        this.gameState = gameState;
        this.correctAnswers = correctAnswers;
        this.questionsAnswered = questionsAnswered;
    }

    private QuizAndroidViewModelImpl(final Parcel parcel) {
        state = (State) parcel.readSerializable();
        question = parcel.readParcelable(AndroidViewQuestion.class.getClassLoader());
        gameState = (GameState) parcel.readSerializable();
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
        dest.writeInt(correctAnswers);
        dest.writeInt(questionsAnswered);
    }

    static final Creator<QuizAndroidViewModel> CREATOR = new Creator<QuizAndroidViewModel>() {
        @Override
        public QuizAndroidViewModel createFromParcel(Parcel source) {
            return new QuizAndroidViewModelImpl(source);
        }

        @Override
        public QuizAndroidViewModel[] newArray(int size) {
            return new QuizAndroidViewModel[size];
        }
    };

    private boolean showingNewGameState() {
        return state == State.START_NEW_GAME || state == State.LOADING_NEW_GAME || state == State.FAILURE_TO_START_NEW_GAME || state == State.GAME_FINISHED;
    }

    private boolean showingPlayGameState() {
        return state == State.GAME_TUTORIAL || state == State.GAME_RUNNING;
    }

    @Override
    public void showScore(QuizAndroidView view, int correctAnswerCount, int questionCount) {
        this.correctAnswers = correctAnswerCount;
        this.questionsAnswered = questionCount;
        if (view != null) {
            view.showScore(correctAnswerCount, questionCount);
        }
    }

    @Override
    public void showStartNewGameState(QuizAndroidView view) {
        if (view != null) {
            view.showNewGameStateStart();
            if (showingPlayGameState()) {
                view.hidePlayGameState();
            }
        }
        state = State.START_NEW_GAME;
        gameState = GameState.NOT_INITIALISED;
    }

    @Override
    public void showNewGameLoadingState(QuizAndroidView view) {
        if (view != null) {
            view.showNewGameStateStart();
            if (showingPlayGameState()) {
                view.hidePlayGameState();
            }
        }
        state = State.LOADING_NEW_GAME;
        gameState = GameState.NOT_INITIALISED;
    }

    @Override
    public void showFailureToStartGameState(QuizAndroidView view) {
        if (view != null) {
            view.showNewGameStateStart();
            if (showingPlayGameState()) {
                view.hidePlayGameState();
            }
        }
        state = State.FAILURE_TO_START_NEW_GAME;
        gameState = GameState.NOT_INITIALISED;
    }

    @Override
    public void showNewGameTutorialState(QuizAndroidView view) {
        if (view != null) {
            view.showNewGameStateStart();
            if (showingNewGameState()) {
                view.hideNewGameState();
            }
        }
        state = State.GAME_TUTORIAL;
        gameState = GameState.INITIALISED;
    }

    @Override
    public void showGameRunningState(QuizAndroidView view) {
        if (view != null) {
            view.showPlayGameStateRunning();
            if (showingNewGameState()) {
                view.hideNewGameState();
            }
        }
        state = State.GAME_RUNNING;
        gameState = GameState.INITIALISED;
    }

    @Override
    public void showQuestion(QuizAndroidView view, AndroidViewQuestion question) {
        this.question = question;
        if (view != null) {
            view.showQuestion(question);
        }
    }

    @Override
    public void showFinishedGameState(QuizAndroidView view) {
        if (view != null) {
            view.showPlayGameStateFinished();
            if (showingPlayGameState()) {
                view.hidePlayGameState();
            }
        }
        question = null;
        state = State.GAME_FINISHED;
        gameState = GameState.FINISHED;
    }

    @Override
    public void onto(QuizAndroidView view, final boolean setAllData) {
        switch (state) {
            case START_NEW_GAME: {
                view.showNewGameStateStart();
                break;
            }
            case LOADING_NEW_GAME: {
                view.showNewGameStateLoading();
                break;
            }
            case FAILURE_TO_START_NEW_GAME: {
                view.showNewGameStateError();
                break;
            }
            case GAME_TUTORIAL: {
                view.showPlayGameStateTutorial();
                view.showScore(correctAnswers, questionsAnswered);
                view.showQuestion(question);//TODO may want to remove when tutorial properly implemented
                break;
            }
            case GAME_RUNNING: {
                view.showPlayGameStateRunning();
                view.showScore(correctAnswers, questionsAnswered);
                view.showQuestion(question);
                break;
            }
            case GAME_FINISHED: {
                view.showPlayGameStateFinished();
                break;
            }
        }
    }

    @Override
    public GameState gameState() {
        return gameState;
    }
}
