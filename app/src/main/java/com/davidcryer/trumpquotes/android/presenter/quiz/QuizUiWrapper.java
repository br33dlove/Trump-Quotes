package com.davidcryer.trumpquotes.android.presenter.quiz;

import android.os.Bundle;

import com.davidc.uiwrapper.UiWrapper;
import com.davidcryer.trumpquotes.android.view.ui.QuizUi;
import com.davidcryer.trumpquotes.android.presenter.quiz.uimodels.QuizUiModel;
import com.davidcryer.trumpquotes.android.presenter.quiz.uimodels.QuizUiModelFactory;
import com.davidcryer.trumpquotes.android.presenter.quiz.uimodels.models.ViewQuestion;
import com.davidcryer.trumpquotes.android.model.domainentities.QuizQuestion;
import com.davidcryer.trumpquotes.android.model.interactors.ActiveGameInteractors;
import com.davidcryer.trumpquotes.android.model.interactors.AnswerQuestionInteractor;
import com.davidcryer.trumpquotes.android.model.interactors.GetNextQuestionInteractor;
import com.davidcryer.trumpquotes.android.model.interactors.InitialiseGameInteractor;
import com.davidcryer.trumpquotes.android.model.interactors.LoadGameInteractor;

import java.lang.ref.WeakReference;

public class QuizUiWrapper extends UiWrapper<QuizUi, QuizUi.Listener, QuizUiModel> {
    private final LoadGameInteractor loadGameInteractor;
    private final InitialiseGameInteractor initialiseGameInteractor;
    private ActiveGameInteractors activeGameInteractors;

    private QuizUiWrapper(
            final QuizUiModel uiModel,
            final LoadGameInteractor loadGameInteractor,
            final InitialiseGameInteractor initialiseGameInteractor
    ) {
        super(uiModel);
        this.loadGameInteractor = loadGameInteractor;
        this.initialiseGameInteractor = initialiseGameInteractor;
    }

    public static UiWrapper<QuizUi, QuizUi.Listener, QuizUiModel> newInstance(
            final LoadGameInteractor loadGameInteractor,
            final InitialiseGameInteractor initialiseGameInteractor
    ) {
        return new QuizUiWrapper(QuizUiModelFactory.create(), loadGameInteractor, initialiseGameInteractor);
    }

    public static UiWrapper<QuizUi, QuizUi.Listener, QuizUiModel> retrieveInstanceOrGetNew(
            final Bundle savedState,
            final LoadGameInteractor loadGameInteractor,
            final InitialiseGameInteractor initialiseGameInteractor
    ) {
        final QuizUiModel uiModel = UiWrapper.savedUiModel(savedState);
        return uiModel == null
                ? newInstance(loadGameInteractor, initialiseGameInteractor)
                : new QuizUiWrapper(uiModel, loadGameInteractor, initialiseGameInteractor);
    }

    @Override
    protected QuizUi.Listener uiListener() {
        return uiListener;
    }

    private final QuizUi.Listener uiListener = new QuizUi.Listener() {
        @Override
        public void onViewCreated() {
            initialiseView();
        }

        @Override
        public void onClickStartNewGame() {
            initialiseGame();
        }

        @Override
        public void onAnswerOptionA() {
            answerOptionA();
        }

        @Override
        public void onAnswerOptionB() {
            answerOptionB();
        }
    };

    private void initialiseView() {
        if (activeGameInteractors == null) {
            if (uiModel().gameNotInitialised()) {
                uiModel().showStartNewGame(ui());
            } else {
                loadGame();
            }
        }
    }

    private void loadGame() {
        loadGameInteractor.runTask(new WeakReference<LoadGameInteractor.Callback>(new LoadGameInteractor.Callback() {
            @Override
            public void onLoadGame(ActiveGameInteractors interactors, int correctAnswers, int questionsAnswered) {
                activeGameInteractors = interactors;
                uiModel().showScore(ui(), correctAnswers, questionsAnswered);
                getNextQuestion();
            }

            @Override
            public void onNoSavedGameFound() {
                uiModel().showStartNewGame(ui());
            }

            @Override
            public void onGameCorrupted() {
                uiModel().showStartNewGame(ui());
            }

            @Override
            public void onError() {
                uiModel().showStartNewGame(ui());
            }
        }));
    }

    private void initialiseGame() {
        showLoadingState();
        initialiseGameInteractor.runTask(new WeakReference<>(initialisationCallback));
    }

    private void showLoadingState() {
        uiModel().showLoading(ui());
    }

    private final InitialiseGameInteractor.Callback initialisationCallback = new InitialiseGameInteractor.Callback() {
        @Override
        public void onInitialiseGame(ActiveGameInteractors interactors, int correctAnswers, int questionsAnswered) {
            activeGameInteractors = interactors;
            uiModel().showScore(ui(), correctAnswers, questionsAnswered);
            getNextQuestion();
        }

        @Override
        public void onError() {
            uiModel().showFailureToLoadGame(ui());
        }
    };

    private void answerOptionA() {
        if (activeGameInteractors != null) {
            activeGameInteractors.answerQuestionInteractor().runTaskAnswerOptionA(new WeakReference<>(answerQuestionCallback));
        }
    }

    private void answerOptionB() {
        if (activeGameInteractors != null) {
            activeGameInteractors.answerQuestionInteractor().runTaskAnswerOptionB(new WeakReference<>(answerQuestionCallback));
        }
    }

    private final AnswerQuestionInteractor.Callback answerQuestionCallback = new AnswerQuestionInteractor.Callback() {
        @Override
        public void onRightAnswerGiven(int correctAnswers, int questionsAnswered) {
            uiModel().showScore(ui(), correctAnswers, questionsAnswered);
            getNextQuestion();
        }

        @Override
        public void onWrongAnswerGiven(int correctAnswers, int questionsAnswered) {
            uiModel().showScore(ui(), correctAnswers, questionsAnswered);
            getNextQuestion();
        }
    };

    private void getNextQuestion() {
        activeGameInteractors.getNextQuestionInteractor().runTask(new WeakReference<>(getNextQuestionCallback));
    }

    private final GetNextQuestionInteractor.Callback getNextQuestionCallback = new GetNextQuestionInteractor.Callback() {
        @Override
        public void nextQuestion(QuizQuestion quizQuestion) {
            uiModel().showQuestion(ui(), ViewQuestion.Factory.create(quizQuestion));
        }

        @Override
        public void onGameFinished() {
            uiModel().showFinishedGame(ui());
            activeGameInteractors = null;
        }
    };
}
