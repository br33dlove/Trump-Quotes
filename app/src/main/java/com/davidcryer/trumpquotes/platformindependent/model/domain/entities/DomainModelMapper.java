package com.davidcryer.trumpquotes.platformindependent.model.domain.entities;

import com.davidcryer.trumpquotes.platformindependent.model.store.models.TrumpQuizGameStorageModel;
import com.davidcryer.trumpquotes.platformindependent.model.store.models.TrumpQuizQuestionStorageModel;

public class DomainModelMapper {

    private DomainModelMapper() {

    }

    public static TrumpQuizGameStorageModel mapGame(final TrumpQuizGameImpl game) {
        return new TrumpQuizGameStorageModel(
                questionIds(game.questions()),
                game.quizScore().questionsAnswered(),
                game.quizScore().correctAnswers(),
                game.isFinished(),
                game.currentQuestionIndex(),
                game.isCurrentQuestionAnswered()
        );
    }

    private static int[] questionIds(final QuizQuestionImpl[] questions) {
        final int[] ids = new int[questions.length];
        for (int i = 0; i < questions.length; i++) {
            ids[i] = i;
        }
        return ids;
    }

    public static TrumpQuizQuestionStorageModel[] mapQuestions(final TrumpQuizGameImpl game) {
        final TrumpQuizQuestionStorageModel[] models = new TrumpQuizQuestionStorageModel[game.questions().length];
        for (int i = 0; i < game.questions().length; i++) {
            final QuizQuestionImpl question = game.questions()[i];
            models[i] = new TrumpQuizQuestionStorageModel(
                    i,
                    question.quote(),
                    question.optionA(),
                    question.optionB(),
                    question.correctAnswer().answerType()
            );
        }
        return models;
    }

    public static TrumpQuizGameImpl mapStorageModels(
            final TrumpQuizGameStorageModel gameStorageModel,
            final TrumpQuizQuestionStorageModel[] questionStorageModels
    ) {
        return new TrumpQuizGameImpl(
                questions(questionStorageModels),
                new QuizScoreImpl(gameStorageModel.questionsAnswered, gameStorageModel.correctAnswers),
                gameStorageModel.currentQuestionIndex,
                gameStorageModel.isFinished,
                gameStorageModel.isCurrentQuestionAnswered
        );
    }

    private static QuizQuestionImpl[] questions(final TrumpQuizQuestionStorageModel[] questionStorageModels) {
        final QuizQuestionImpl[] questions = new QuizQuestionImpl[questionStorageModels.length];
        for (int i = 0; i < questionStorageModels.length; i++) {
            final TrumpQuizQuestionStorageModel questionStorageModel = questionStorageModels[i];
            questions[i] = new QuizQuestionImpl(
                    questionStorageModel.quote,
                    questionStorageModel.optionA,
                    questionStorageModel.optionB,
                    QuizAnswer.newInstance(questionStorageModel.answerType)
            );
        }
        return questions;
    }
}
