package com.davidcryer.trumpquotes.platformindependent.model.domain.entities;

public final class TrumpQuizGameImpl implements TrumpQuizGame {
    private final TrumpQuizQuestionImpl[] questions;
    private final QuizScoreImpl quizScore;
    private int currentQuestionIndex;
    private boolean isFinished;
    private boolean isCurrentQuestionAnswered;

    public TrumpQuizGameImpl(
            TrumpQuizQuestionImpl[] questions,
            QuizScoreImpl quizScore,
            int currentQuestionIndex,
            boolean isFinished,
            boolean isCurrentQuestionAnswered
    ) {
        if (questions == null) {
            throw new IllegalArgumentException("Questions array cannot be null");
        }
        if (quizScore == null) {
            throw new IllegalArgumentException("Score cannot be null");
        }
        if (currentQuestionIndex < 0) {
            throw new IllegalArgumentException("Current question index cannot be negative");
        }
        if (currentQuestionIndex >= questions.length) {
            throw new IllegalArgumentException("Current question index cannot be greater than the number of questions");
        }
        if (quizScore.questionsAnswered() - currentQuestionIndex > 1) {
            throw new IllegalArgumentException("Difference between questions answered and current question index cannot be greater than one");
        }
        this.questions = questions;
        this.quizScore = quizScore;
        this.currentQuestionIndex = currentQuestionIndex;
        this.isFinished = isFinished;
        this.isCurrentQuestionAnswered = isCurrentQuestionAnswered;
    }

    public static TrumpQuizGameImpl newGame(
            TrumpQuizQuestionImpl[] questions
    ) {
        return new TrumpQuizGameImpl(questions, QuizScoreImpl.newInstance(), 0, false, false);
    }

    private TrumpQuizQuestion currentQuestion() {
        return questions[currentQuestionIndex];
    }

    @Override
    public void startGame(StartCallback callback) {
        callback.onReturn(quizScore.correctAnswers(), quizScore.questionsAnswered());
    }

    @Override
    public void onAnswerGiven(TrumpQuizAnswer answer, AnswerCallback answerCallback) {
        if (!isFinished) {
            if (currentQuestion().isCorrect(answer)) {
                if (!isCurrentQuestionAnswered) {
                    quizScore.onRightAnswerGiven();
                }
                answerCallback.onRightAnswerGiven(quizScore.correctAnswers(), quizScore.questionsAnswered());
            } else {
                if (!isCurrentQuestionAnswered) {
                    quizScore.onWrongAnswerGiven();
                }
                answerCallback.onWrongAnswerGiven(quizScore.correctAnswers(), quizScore.questionsAnswered());
            }
        }
        isCurrentQuestionAnswered = true;
    }

    @Override
    public void nextQuote(NextQuoteCallback callback) {
        if (isFinished) {
            callback.onGameFinished();
            return;
        }
        if (isCurrentQuestionAnswered) {
            currentQuestionIndex++;
            if (currentQuestionIndex >= questions.length) {
                isFinished = true;
            }
        }
        callback.onNextQuote(currentQuestion().quote());
    }

    TrumpQuizQuestionImpl[] questions() {
        return questions;
    }

    QuizScoreImpl quizScore() {
        return quizScore;
    }

    int currentQuestionIndex() {
        return currentQuestionIndex;
    }

    boolean isFinished() {
        return isFinished;
    }

    boolean isCurrentQuestionAnswered() {
        return isCurrentQuestionAnswered;
    }
}
