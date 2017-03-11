package com.davidcryer.trumpquotes.platformindependent.presenter.presenters.implementations.swipequote;

import com.davidcryer.trumpquotes.platformindependent.model.domain.entities.QuizQuestion;
import com.davidcryer.trumpquotes.platformindependent.model.domain.interactors.ActiveGameInteractors;
import com.davidcryer.trumpquotes.platformindependent.model.domain.interactors.AnswerQuestionInteractor;
import com.davidcryer.trumpquotes.platformindependent.model.domain.interactors.GetNextQuestionInteractor;
import com.davidcryer.trumpquotes.platformindependent.model.domain.interactors.InitialiseGameInteractor;
import com.davidcryer.trumpquotes.platformindependent.model.domain.interactors.LoadGameInteractor;
import com.davidcryer.trumpquotes.platformindependent.presenter.presenters.Presenter;
import com.davidcryer.trumpquotes.platformindependent.view.SwipeQuestionView;
import com.davidcryer.trumpquotes.platformindependent.view.viewmodels.models.ViewQuestion;
import com.davidcryer.trumpquotes.platformindependent.view.viewmodels.models.ViewQuoteFactory;

import java.lang.ref.WeakReference;

class SwipeQuotePresenter<ViewQuestionType extends ViewQuestion> extends Presenter<SwipeQuestionView.EventsListener> {
    private final SwipeQuestionView<ViewQuestionType> viewWrapper;
    private final ViewQuoteFactory<ViewQuestionType> viewQuestionFactory;
    private final LoadGameInteractor loadGameInteractor;
    private final InitialiseGameInteractor initialiseGameInteractor;
    private ActiveGameInteractors activeGameInteractors;

    SwipeQuotePresenter(
            final SwipeQuestionView<ViewQuestionType> viewWrapper,
            final ViewQuoteFactory<ViewQuestionType> viewQuestionFactory,
            final LoadGameInteractor loadGameInteractor,
            final InitialiseGameInteractor initialiseGameInteractor
    ) {
        this.viewWrapper = viewWrapper;
        this.viewQuestionFactory = viewQuestionFactory;
        this.loadGameInteractor = loadGameInteractor;
        this.initialiseGameInteractor = initialiseGameInteractor;
    }

    @Override
    public SwipeQuestionView.EventsListener eventsListener() {
        return new SwipeQuestionView.EventsListener() {
            @Override
            public void onStartGame() {
                showLoadingQuestion();
                loadGameAndDisplay();
            }

            @Override
            public void onClickStartNewGame() {
                showLoadingQuestion();
                initialiseGame();
            }

            @Override
            public void onDismissNewGameTutorial() {
                dismissNewGameTutorial();
            }

            @Override
            public void onAnswerOptionA() {
                showLoadingQuestion();
                answerOptionA();
            }

            @Override
            public void onAnswerOptionB() {
                showLoadingQuestion();
                answerOptionB();
            }

            @Override
            public void onReleaseResources(final boolean isFinishing) {

            }
        };
    }

    private void loadGameAndDisplay() {
        loadGameInteractor.runTask(new WeakReference<LoadGameInteractor.Callback>(new LoadGameInteractor.Callback() {
            @Override
            public void onLoadGame(ActiveGameInteractors interactors, int correctAnswers, int questionsAnswered) {
                activeGameInteractors = interactors;
                viewWrapper.showScore(correctAnswers, questionsAnswered);
                getNextQuestion();
            }

            @Override
            public void onNoSavedGameFound() {
                initialiseGame();
            }

            @Override
            public void onGameCorrupted() {
                //TODO error screen
            }

            @Override
            public void onError() {
                //TODO error screen
            }
        }));
    }

    private void initialiseGame() {
        initialiseGameInteractor.runTask(new WeakReference<InitialiseGameInteractor.Callback>(new InitialiseGameInteractor.Callback() {
            @Override
            public void onInitialiseGame(ActiveGameInteractors interactors, int correctAnswers, int questionsAnswered) {
                activeGameInteractors = interactors;
                viewWrapper.showScore(correctAnswers, questionsAnswered);
                getNextQuestion();
            }

            @Override
            public void onError() {
                viewWrapper.showFailureToStartGameState();
            }
        }));
    }

    private void showLoadingQuestion() {
        viewWrapper.showStartingGameState();
    }

    private void answerOptionA() {
        if (activeGameInteractors != null) {
            activeGameInteractors.answerQuestionInteractor().runTaskAnswerOptionA(new WeakReference<AnswerQuestionInteractor.Callback>(new AnswerQuestionInteractor.Callback() {
                @Override
                public void onRightAnswerGiven(int correctAnswers, int questionsAnswered) {
                    viewWrapper.showScore(correctAnswers, questionsAnswered);
                }

                @Override
                public void onWrongAnswerGiven(int correctAnswers, int questionsAnswered) {
                    viewWrapper.showScore(correctAnswers, questionsAnswered);
                }
            }));
        }
    }

    private void answerOptionB() {
        if (activeGameInteractors != null) {
            activeGameInteractors.answerQuestionInteractor().runTaskAnswerOptionB(new WeakReference<AnswerQuestionInteractor.Callback>(new AnswerQuestionInteractor.Callback() {
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
            }));
        }
    }

    private void getNextQuestion() {
        activeGameInteractors.getNextQuestionInteractor().runTask(new WeakReference<GetNextQuestionInteractor.Callback>(new GetNextQuestionInteractor.Callback() {
            @Override
            public void nextQuestion(QuizQuestion quizQuestion) {
                viewWrapper.showQuestionState(viewQuestionFactory.create(quizQuestion));
            }

            @Override
            public void onGameFinished() {
                viewWrapper.showFinishedGameState();
            }
        }));
    }

    private void dismissNewGameTutorial() {
        viewWrapper.dismissNewGameTutorial();
    }
}
