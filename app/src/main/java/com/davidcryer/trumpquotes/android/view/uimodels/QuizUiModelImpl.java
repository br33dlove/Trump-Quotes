package com.davidcryer.trumpquotes.android.view.uimodels;

import android.os.Parcel;

import com.davidcryer.trumpquotes.android.view.ui.QuizUi;
import com.davidcryer.trumpquotes.android.view.uimodels.models.AndroidViewQuestion;

import java.lang.ref.WeakReference;

final class QuizUiModelImpl implements QuizUiModel {
    enum State {START_NEW_GAME, LOADING_NEW_GAME, FAILURE_TO_START_NEW_GAME, GAME_RUNNING, GAME_FINISHED}
    private State state;
    private AndroidViewQuestion question;
    private GameState gameState;
    private int correctAnswers;
    private int questionsAnswered;

    QuizUiModelImpl(State state, AndroidViewQuestion question, GameState gameState, int correctAnswers, int questionsAnswered) {
        this.state = state;
        this.question = question;
        this.gameState = gameState;
        this.correctAnswers = correctAnswers;
        this.questionsAnswered = questionsAnswered;
    }

    private QuizUiModelImpl(final Parcel parcel) {
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

    static final Creator<QuizUiModel> CREATOR = new Creator<QuizUiModel>() {
        @Override
        public QuizUiModel createFromParcel(Parcel source) {
            return new QuizUiModelImpl(source);
        }

        @Override
        public QuizUiModel[] newArray(int size) {
            return new QuizUiModel[size];
        }
    };

    private boolean showingNewGameState() {
        return state == State.START_NEW_GAME || state == State.LOADING_NEW_GAME || state == State.FAILURE_TO_START_NEW_GAME || state == State.GAME_FINISHED;
    }

    private boolean showingPlayGameState() {
        return state == State.GAME_RUNNING;
    }

    @Override
    public void showScore(QuizUi ui, int correctAnswerCount, int questionCount) {
        this.correctAnswers = correctAnswerCount;
        this.questionsAnswered = questionCount;
        if (ui != null && showingPlayGameState()) {
            ui.showScore(correctAnswerCount, questionCount);
        }
    }

    @Override
    public void showStartNewGame(QuizUi ui) {
        if (ui != null) {
            ui.animateInNewGameStartState();
        }
        state = State.START_NEW_GAME;
        gameState = GameState.NOT_INITIALISED;
    }

    @Override
    public void showLoadingGame(QuizUi ui) {
        if (ui != null) {
            ui.animateInNewGameLoadingState();
        }
        state = State.LOADING_NEW_GAME;
        gameState = GameState.NOT_INITIALISED;
    }

    @Override
    public void showFailureToLoadGame(QuizUi ui) {
        if (ui != null) {
            ui.animateInNewGameFailedToLoadGameState();
        }
        state = State.FAILURE_TO_START_NEW_GAME;
        gameState = GameState.NOT_INITIALISED;
    }

    @Override
    public void showQuestion(QuizUi ui, final AndroidViewQuestion question) {
        this.question = question;
        if (ui != null) {
            if (showingNewGameState()) {
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
    }

    @Override
    public void showFinishedGameState(QuizUi ui) {
        if (ui != null) {
            if (showingPlayGameState()) {
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

    @Override
    public void onto(QuizUi ui) {
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

    @Override
    public GameState gameState() {
        return gameState;
    }
}
