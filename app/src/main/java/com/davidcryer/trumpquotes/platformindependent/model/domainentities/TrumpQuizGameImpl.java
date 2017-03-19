package com.davidcryer.trumpquotes.platformindependent.model.domainentities;

public final class TrumpQuizGameImpl implements QuizGame {
    private final QuizQuestionImpl[] questions;
    private final QuizScoreImpl quizScore;
    private int currentQuestionIndex;
    private boolean isFinished;
    private boolean isCurrentQuestionAnswered;

    TrumpQuizGameImpl(
            QuizQuestionImpl[] questions,
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
            throw new IllegalArgumentException("Current question index cannot be greater than the number of game");
        }
        if (quizScore.questionsAnswered() - currentQuestionIndex > 1) {
            throw new IllegalArgumentException("Difference between game answered and current question index cannot be greater than one");
        }
        this.questions = questions;
        this.quizScore = quizScore;
        this.currentQuestionIndex = currentQuestionIndex;
        this.isFinished = isFinished;
        this.isCurrentQuestionAnswered = isCurrentQuestionAnswered;
    }

    public static TrumpQuizGameImpl newGame(
            QuizQuestionImpl[] questions
    ) {
        return new TrumpQuizGameImpl(questions, QuizScoreImpl.newInstance(), 0, false, false);
    }

    private QuizQuestion currentQuestion() {
        return questions[currentQuestionIndex];
    }

    @Override
    public void startGame(StartCallback callback) {
        callback.onReturn(quizScore.correctAnswers(), quizScore.questionsAnswered());
    }

    @Override
    public void onAnswerGiven(QuizAnswer answer, AnswerCallback answerCallback) {
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
    public void nextQuestion(NextQuestionCallback callback) {
        if (isFinished) {
            callback.onGameFinished();
            return;
        }
        if (isCurrentQuestionAnswered) {
            isCurrentQuestionAnswered = false;
            currentQuestionIndex++;
            if (currentQuestionIndex >= questions.length) {
                isFinished = true;
                callback.onGameFinished();
                return;
            }
        }
        callback.nextQuestion(currentQuestion());
    }

    QuizQuestionImpl[] questions() {
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
