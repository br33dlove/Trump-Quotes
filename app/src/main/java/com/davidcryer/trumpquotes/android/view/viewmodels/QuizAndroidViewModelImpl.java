package com.davidcryer.trumpquotes.android.view.viewmodels;

import android.os.Parcel;

import com.davidcryer.trumpquotes.android.view.ui.QuizAndroidView;
import com.davidcryer.trumpquotes.android.view.viewmodels.models.AndroidViewQuestion;

final class QuizAndroidViewModelImpl implements QuizAndroidViewModel {
    enum State {START_NEW_GAME, LOADING, FAILURE_TO_START_GAME, QUESTION, GAME_FINISHED}
    private State state;
    private AndroidViewQuestion question;
    private GameState gameState;
    private boolean showNewGameTutorial;
    private boolean questionUpdated;
    private int correctAnswers;
    private int questionsAnswered;

    QuizAndroidViewModelImpl(
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

    private QuizAndroidViewModelImpl(final Parcel parcel) {
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
        state = State.START_NEW_GAME;
        gameState = GameState.NOT_INITIALISED;
        if (view != null) {
            view.showStartNewGameState();
        }
    }

    @Override
    public void showLoadingState(QuizAndroidView view) {
        state = State.LOADING;
        if (view != null) {
            view.showStartNewGameState();
        }
    }

    @Override
    public void showFailureToStartGameState(QuizAndroidView view) {
        state = State.FAILURE_TO_START_GAME;
        gameState = GameState.NOT_INITIALISED;
        if (view != null) {
            view.showStartNewGameState();
        }
    }

    @Override
    public void showNewGameTutorial(QuizAndroidView view) {
        showNewGameTutorial = true;
        if (view != null) {
            view.showStartNewGameState();
        }
    }

    @Override
    public void dismissNewGameTutorial(QuizAndroidView view) {
        showNewGameTutorial = false;
        if (view != null) {
            view.showStartNewGameState();
        }
    }

    @Override
    public void showQuestionState(QuizAndroidView view, AndroidViewQuestion question) {
        state = State.QUESTION;
        gameState = GameState.RUNNING;
        this.question = question;
        if (view != null) {
            view.showQuestionState(question);
        }
    }

    @Override
    public void showFinishedGameState(QuizAndroidView view) {
        state = State.GAME_FINISHED;
        gameState = GameState.FINISHED;
        question = null;
        if (view != null) {
            view.showFinishedGameState();
        }
    }

    @Override
    public void onto(QuizAndroidView view, final boolean setAllData) {
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
