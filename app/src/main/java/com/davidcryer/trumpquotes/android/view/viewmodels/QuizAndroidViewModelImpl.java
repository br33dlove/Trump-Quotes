package com.davidcryer.trumpquotes.android.view.viewmodels;

import android.os.Parcel;

import com.davidcryer.trumpquotes.android.view.ui.QuizAndroidView;
import com.davidcryer.trumpquotes.android.view.viewmodels.models.AndroidViewQuestion;

import java.lang.ref.WeakReference;

final class QuizAndroidViewModelImpl implements QuizAndroidViewModel {
    enum State {START_NEW_GAME, LOADING_NEW_GAME, FAILURE_TO_START_NEW_GAME, GAME_RUNNING, GAME_FINISHED}
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
        return state == State.GAME_RUNNING;
    }

    @Override
    public void showScore(QuizAndroidView view, int correctAnswerCount, int questionCount) {
        this.correctAnswers = correctAnswerCount;
        this.questionsAnswered = questionCount;
        if (view != null && showingPlayGameState()) {
            view.showScore(correctAnswerCount, questionCount);
        }
    }

    @Override
    public void showStartNewGame(QuizAndroidView view) {
        if (view != null) {
            view.animateInNewGameStartState();
        }
        state = State.START_NEW_GAME;
        gameState = GameState.NOT_INITIALISED;
    }

    @Override
    public void showLoadingGame(QuizAndroidView view) {
        if (view != null) {
            view.animateInNewGameLoadingState();
        }
        state = State.LOADING_NEW_GAME;
        gameState = GameState.NOT_INITIALISED;
    }

    @Override
    public void showFailureToLoadGame(QuizAndroidView view) {
        if (view != null) {
            view.animateInNewGameFailedToLoadGameState();
        }
        state = State.FAILURE_TO_START_NEW_GAME;
        gameState = GameState.NOT_INITIALISED;
    }

    @Override
    public void showQuestion(QuizAndroidView view, final AndroidViewQuestion question) {
        this.question = question;
        if (view != null) {
            if (showingNewGameState()) {
                final WeakReference<QuizAndroidView> weakReference = new WeakReference<>(view);
                view.animateOutNewGameScene(new Runnable() {
                    @Override
                    public void run() {
                        if (weakReference.get() != null) {
                            weakReference.get().animateInQuestion(question);
                            weakReference.get().animateInScore(correctAnswers, questionsAnswered);
                        }
                    }
                });
            } else {
                view.animateInQuestion(question);
            }
        }
        state = State.GAME_RUNNING;
    }

    @Override
    public void showFinishedGameState(QuizAndroidView view) {
        if (view != null) {
            if (showingPlayGameState()) {
                final WeakReference<QuizAndroidView> weakReference = new WeakReference<>(view);
                view.animateOutGameInPlayScene(new Runnable() {
                    @Override
                    public void run() {
                        if (weakReference.get() != null) {
                            weakReference.get().animateInNewGameFinishedState(correctAnswers, questionsAnswered);
                        }
                    }
                });
            }
        }
        question = null;
        state = State.GAME_FINISHED;
        gameState = GameState.FINISHED;
    }

    @Override
    public void onto(QuizAndroidView view) {
        switch (state) {
            case START_NEW_GAME: {
                view.showNewGameStartState();
                view.hideGameInPlayScene();
                break;
            }
            case LOADING_NEW_GAME: {
                view.showNewGameLoadingState();
                view.hideGameInPlayScene();
                break;
            }
            case FAILURE_TO_START_NEW_GAME: {
                view.showNewGameFailedToStartState();
                view.hideGameInPlayScene();
                break;
            }
            case GAME_RUNNING: {
                view.showScore(correctAnswers, questionsAnswered);
                view.showQuestion(question);
                view.hideNewGameScene();
                break;
            }
            case GAME_FINISHED: {
                view.showNewGameFinishedState(correctAnswers, questionsAnswered);
                view.hideGameInPlayScene();
                break;
            }
        }
    }

    @Override
    public GameState gameState() {
        return gameState;
    }
}
