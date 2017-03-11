package com.davidcryer.trumpquotes.platformindependent.presenter.presenters.implementations.swipequote;

import com.davidcryer.trumpquotes.platformindependent.model.domain.entities.QuizQuestion;
import com.davidcryer.trumpquotes.platformindependent.model.domain.interactors.ActiveGameInteractors;
import com.davidcryer.trumpquotes.platformindependent.model.domain.interactors.AnswerQuestionInteractor;
import com.davidcryer.trumpquotes.platformindependent.model.domain.interactors.GetNextQuestionInteractor;
import com.davidcryer.trumpquotes.platformindependent.model.domain.interactors.InitialiseGameInteractor;
import com.davidcryer.trumpquotes.platformindependent.presenter.presenters.Presenter;
import com.davidcryer.trumpquotes.platformindependent.view.SwipeQuestionView;
import com.davidcryer.trumpquotes.platformindependent.view.viewmodels.models.ViewQuestion;
import com.davidcryer.trumpquotes.platformindependent.view.viewmodels.models.ViewQuoteFactory;

import java.lang.ref.WeakReference;

class SwipeQuotePresenter<ViewQuestionType extends ViewQuestion> extends Presenter<SwipeQuestionView.EventsListener> {
    private final SwipeQuestionView<ViewQuestionType> viewWrapper;
    private final ViewQuoteFactory<ViewQuestionType> viewQuestionFactory;
    private final InitialiseGameInteractor initialiseGameInteractor;
    private ActiveGameInteractors activeGameInteractors;

    SwipeQuotePresenter(
            final SwipeQuestionView<ViewQuestionType> viewWrapper,
            final ViewQuoteFactory<ViewQuestionType> viewQuestionFactory,
            final InitialiseGameInteractor initialiseGameInteractor
    ) {
        this.viewWrapper = viewWrapper;
        this.viewQuestionFactory = viewQuestionFactory;
        this.initialiseGameInteractor = initialiseGameInteractor;
    }

    @Override
    public SwipeQuestionView.EventsListener eventsListener() {
        return new SwipeQuestionView.EventsListener() {

            @Override
            public void onStartGame() {
                showLoadingQuote();
                initialiseGameAndDisplay();
            }

            @Override
            public void onAnswerOptionA() {
                showLoadingQuote();
                answerOptionA();
            }

            @Override
            public void onAnswerOptionB() {
                showLoadingQuote();
                answerOptionB();
            }

            @Override
            public void onReleaseResources(final boolean isFinishing) {

            }
        };
    }

    private void initialiseGameAndDisplay() {
        initialiseGameInteractor.runTask(new WeakReference<InitialiseGameInteractor.Callback>(new InitialiseGameInteractor.Callback() {
            @Override
            public void onInitialiseGame(InitialiseGameInteractor.Payload payload) {
                activeGameInteractors = payload.activeGameInteractors;
                viewWrapper.showQuestionState(viewQuestionFactory.create(payload.quizQuestion));
                viewWrapper.showScore(payload.correctAnswers, payload.questionsAnswered);
                //TODO use isNewGame?
            }

            @Override
            public void onError() {
                viewWrapper.showFailureToStartGameState();
            }
        }));
    }

    private void showLoadingQuote() {
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
}
