package com.davidcryer.trumpquotes.platformindependent.presenter.presenters.implementations.quiz;

import com.davidcryer.trumpquotes.platformindependent.model.domainentities.QuizQuestion;
import com.davidcryer.trumpquotes.platformindependent.model.interactors.ActiveGameInteractors;
import com.davidcryer.trumpquotes.platformindependent.model.interactors.AnswerQuestionInteractor;
import com.davidcryer.trumpquotes.platformindependent.model.interactors.GetNextQuestionInteractor;
import com.davidcryer.trumpquotes.platformindependent.model.interactors.InitialiseGameInteractor;
import com.davidcryer.trumpquotes.platformindependent.model.interactors.LoadGameInteractor;
import com.davidcryer.trumpquotes.platformindependent.presenter.presenters.Presenter;
import com.davidcryer.trumpquotes.platformindependent.view.QuizView;
import com.davidcryer.trumpquotes.platformindependent.view.viewmodels.QuizViewModel;
import com.davidcryer.trumpquotes.platformindependent.view.viewmodels.models.ViewQuestion;
import com.davidcryer.trumpquotes.platformindependent.view.viewmodels.models.ViewQuestionFactory;

import java.lang.ref.WeakReference;

class QuizPresenter<ViewQuestionType extends ViewQuestion> extends Presenter<QuizView.EventsListener> {
    private final QuizView<ViewQuestionType> viewWrapper;
    private final ViewQuestionFactory<ViewQuestionType> viewQuestionFactory;
    private final LoadGameInteractor loadGameInteractor;
    private final InitialiseGameInteractor initialiseGameInteractor;
    private ActiveGameInteractors activeGameInteractors;

    QuizPresenter(
            final QuizView<ViewQuestionType> viewWrapper,
            final ViewQuestionFactory<ViewQuestionType> viewQuestionFactory,
            final LoadGameInteractor loadGameInteractor,
            final InitialiseGameInteractor initialiseGameInteractor
    ) {
        this.viewWrapper = viewWrapper;
        this.viewQuestionFactory = viewQuestionFactory;
        this.loadGameInteractor = loadGameInteractor;
        this.initialiseGameInteractor = initialiseGameInteractor;
    }

    @Override
    public QuizView.EventsListener eventsListener() {
        return new QuizView.EventsListener() {
            @Override
            public void onInitialise() {
                initialiseView();
            }

            @Override
            public void onClickStartNewGame() {
                showLoadingState();
                initialiseGame();
            }

            @Override
            public void onDismissNewGameTutorial() {
                dismissNewGameTutorial();
            }

            @Override
            public void onAnswerOptionA() {
                answerOptionA();
            }

            @Override
            public void onAnswerOptionB() {
                answerOptionB();
            }

            @Override
            public void onReleaseResources(final boolean isFinishing) {

            }
        };
    }

    private void initialiseView() {
        if (activeGameInteractors == null) {
            if (viewWrapper.viewModel().gameState() == QuizViewModel.GameState.NOT_INITIALISED) {
                viewWrapper.showStartNewGameState();
            } else {
                loadGame();
            }
        }
    }

    private void showLoadingState() {
        viewWrapper.showLoadingState();
    }

    private void loadGame() {
        loadGameInteractor.runTask(new WeakReference<LoadGameInteractor.Callback>(new LoadGameInteractor.Callback() {
            @Override
            public void onLoadGame(ActiveGameInteractors interactors, int correctAnswers, int questionsAnswered) {
                activeGameInteractors = interactors;
                viewWrapper.showScore(correctAnswers, questionsAnswered);
                getNextQuestion();
            }

            @Override
            public void onNoSavedGameFound() {
                viewWrapper.showStartNewGameState();
            }

            @Override
            public void onGameCorrupted() {
                viewWrapper.showStartNewGameState();
            }

            @Override
            public void onError() {
                viewWrapper.showStartNewGameState();
            }
        }));
    }

    private void initialiseGame() {
        initialiseGameInteractor.runTask(new WeakReference<>(initialisationCallback));
    }

    private final InitialiseGameInteractor.Callback initialisationCallback = new InitialiseGameInteractor.Callback() {
        @Override
        public void onInitialiseGame(ActiveGameInteractors interactors, int correctAnswers, int questionsAnswered) {
            activeGameInteractors = interactors;
            viewWrapper.showScore(correctAnswers, questionsAnswered);
            viewWrapper.showNewGameTutorial();
            getNextQuestion();
        }

        @Override
        public void onError() {
            viewWrapper.showFailureToStartGameState();
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
            viewWrapper.showScore(correctAnswers, questionsAnswered);
            getNextQuestion();
        }

        @Override
        public void onWrongAnswerGiven(int correctAnswers, int questionsAnswered) {
            viewWrapper.showScore(correctAnswers, questionsAnswered);
            getNextQuestion();
        }
    };

    private void getNextQuestion() {
        activeGameInteractors.getNextQuestionInteractor().runTask(new WeakReference<>(getNextQuestionCallback));
    }

    private final GetNextQuestionInteractor.Callback getNextQuestionCallback = new GetNextQuestionInteractor.Callback() {
        @Override
        public void nextQuestion(QuizQuestion quizQuestion) {
            viewWrapper.showQuestionState(viewQuestionFactory.create(quizQuestion));
        }

        @Override
        public void onGameFinished() {
            viewWrapper.showFinishedGameState();
            activeGameInteractors = null;
        }
    };

    private void dismissNewGameTutorial() {
        viewWrapper.dismissNewGameTutorial();
    }
}
