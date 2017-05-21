package com.davidcryer.trumpquotes.android.presenter.quiz.uimodels;

import android.os.Parcel;
import android.support.annotation.NonNull;

import com.davidc.uiwrapper.UiModel;
import com.davidcryer.trumpquotes.android.view.ui.QuizUi;
import com.davidcryer.trumpquotes.android.presenter.quiz.uimodels.models.ViewQuestion;

import java.lang.ref.WeakReference;

public class QuizUiModel implements UiModel<QuizUi> {
    enum GameState {NOT_INITIALISED, INITIALISED, FINISHED}
    enum State {START_NEW_GAME, LOADING_NEW_GAME, FAILURE_TO_START_NEW_GAME, GAME_RUNNING, GAME_FINISHED}
    private State state;
    private ViewQuestion question;
    private GameState gameState;
    private int correctAnswers;
    private int questionsAnswered;

    QuizUiModel(State state, ViewQuestion question, GameState gameState, int correctAnswers, int questionsAnswered) {
        this.state = state;
        this.question = question;
        this.gameState = gameState;
        this.correctAnswers = correctAnswers;
        this.questionsAnswered = questionsAnswered;
    }

    private QuizUiModel(final Parcel parcel) {
        state = (State) parcel.readSerializable();
        question = parcel.readParcelable(ViewQuestion.class.getClassLoader());
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

    static final Creator<QuizUiModel> CREATOR = new Creator<QuizUiModel>() {
        @Override
        public QuizUiModel createFromParcel(Parcel source) {
            return new QuizUiModel(source);
        }

        @Override
        public QuizUiModel[] newArray(int size) {
            return new QuizUiModel[size];
        }
    };

    @Override
    public void onto(@NonNull QuizUi ui) {
        switch (state) {
            case START_NEW_GAME: {
                ui.showNewGameStartState();
                ui.hideGameInPlayScene();
                break;
            }
            case LOADING_NEW_GAME: {
                ui.showNewGameLoadingState();
                ui.hideGameInPlayScene();
                break;
            }
            case FAILURE_TO_START_NEW_GAME: {
                ui.showNewGameFailedToStartState();
                ui.hideGameInPlayScene();
                break;
            }
            case GAME_RUNNING: {
                ui.showScore(correctAnswers, questionsAnswered);
                ui.showQuestion(question);
                ui.hideNewGameScene();
                break;
            }
            case GAME_FINISHED: {
                ui.showNewGameFinishedState(correctAnswers, questionsAnswered);
                ui.hideGameInPlayScene();
                break;
            }
        }
    }

    public void showScore(QuizUi ui, int correctAnswerCount, int questionCount) {
        this.correctAnswers = correctAnswerCount;
        this.questionsAnswered = questionCount;
        if (ui != null && showingPlayGame()) {
            ui.showScore(correctAnswerCount, questionCount);
        }
    }

    private boolean showingPlayGame() {
        return state == State.GAME_RUNNING;
    }

    public void showStartNewGame(QuizUi ui) {
        if (ui != null) {
            ui.animateInStartNewGame();
        }
        state = State.START_NEW_GAME;
        gameState = GameState.NOT_INITIALISED;
    }

    public void showLoading(QuizUi ui) {
        if (ui != null) {
            ui.animateInNewGameLoading();
        }
        state = State.LOADING_NEW_GAME;
        gameState = GameState.NOT_INITIALISED;
    }

    public void showFailureToLoadGame(QuizUi ui) {
        if (ui != null) {
            ui.animateInFailureToLoadGameFromLoading();
        }
        state = State.FAILURE_TO_START_NEW_GAME;
        gameState = GameState.NOT_INITIALISED;
    }

    public void showQuestion(QuizUi ui, final ViewQuestion question) {
        this.question = question;
        if (ui != null) {
            if (showingNewGame()) {
                final WeakReference<QuizUi> weakReference = new WeakReference<>(ui);
                ui.animateOutNewGameScene(new Runnable() {
                    @Override
                    public void run() {
                        final QuizUi ui = weakReference.get();
                        if (ui != null) {
                            ui.animateInQuestion(question);
                            ui.animateInScore(correctAnswers, questionsAnswered);
                        }
                    }
                });
            } else {
                ui.animateInQuestion(question);
            }
        }
        state = State.GAME_RUNNING;
        gameState = GameState.INITIALISED;
    }

    private boolean showingNewGame() {
        return state == State.START_NEW_GAME || state == State.LOADING_NEW_GAME || state == State.FAILURE_TO_START_NEW_GAME || state == State.GAME_FINISHED;
    }

    public void showFinishedGame(QuizUi ui) {
        if (ui != null) {
            if (showingPlayGame()) {
                final WeakReference<QuizUi> weakReference = new WeakReference<>(ui);
                ui.animateOutGameInPlayScene(new Runnable() {
                    @Override
                    public void run() {
                        final QuizUi ui = weakReference.get();
                        if (ui != null) {
                            ui.animateInNewGameFinishedState(correctAnswers, questionsAnswered);
                        }
                    }
                });
            }
        }
        question = null;
        state = State.GAME_FINISHED;
        gameState = GameState.FINISHED;
    }

    public boolean gameNotInitialised() {
        return gameState == GameState.NOT_INITIALISED;
    }
}
