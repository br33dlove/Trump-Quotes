package com.davidcryer.trumpquotes.android.model.store.games.sharedpreferences;

import android.content.Context;
import android.content.SharedPreferences;

import com.davidcryer.trumpquotes.platformindependent.javahelpers.ArrayHelper;
import com.davidcryer.trumpquotes.platformindependent.javahelpers.StringHelper;
import com.davidcryer.trumpquotes.platformindependent.model.store.models.TrumpQuizGameStorageModel;
import com.davidcryer.trumpquotes.platformindependent.model.store.stores.TrumpQuizGameStore;

import static com.davidcryer.trumpquotes.android.model.helpers.store.SQLHelper.DELIMITER_COMMA;

public class SharedPreferencesGameStore implements TrumpQuizGameStore {
    private final static String KEY_SHARED_PREFERENCES = "game";
    private final static String KEY_QUESTION_IDS = "question ids";
    private final static String KEY_QUESTIONS_ANSWERED = "questions answered";
    private final static String KEY_CORRECT_ANSWERS = "correct answers";
    private final static String KEY_IS_FINISHED = "is finished";
    private final static String KEY_QUESTION_INDEX = "current question index";
    private final static String KEY_IS_CURRENT_QUESTION_ANSWERED = "is current question answered";
    private final SharedPreferences sharedPreferences;

    public SharedPreferencesGameStore(final Context context) {
        this.sharedPreferences = context.getSharedPreferences(KEY_SHARED_PREFERENCES, Context.MODE_PRIVATE);
    }

    @Override
    public boolean store(TrumpQuizGameStorageModel model) {
        sharedPreferences.edit()
                .putString(KEY_QUESTION_IDS, ArrayHelper.toString(model.questionIds, DELIMITER_COMMA))
                .putInt(KEY_QUESTIONS_ANSWERED, model.questionsAnswered)
                .putInt(KEY_CORRECT_ANSWERS, model.correctAnswers)
                .putBoolean(KEY_IS_FINISHED, model.isFinished)
                .putInt(KEY_QUESTION_INDEX, model.currentQuestionIndex)
                .putBoolean(KEY_IS_CURRENT_QUESTION_ANSWERED, model.isCurrentQuestionAnswered)
                .apply();
        return true;
    }

    @Override
    public boolean clear() {
        sharedPreferences.edit().clear().apply();
        return true;
    }

    @Override
    public TrumpQuizGameStorageModel retrieve() {
        if (hasStoredModel()) {
            return new TrumpQuizGameStorageModel(
                    StringHelper.splitDelimitedInts(sharedPreferences.getString(KEY_QUESTION_IDS, ""), DELIMITER_COMMA),
                    sharedPreferences.getInt(KEY_QUESTIONS_ANSWERED, 0),
                    sharedPreferences.getInt(KEY_CORRECT_ANSWERS, 0),
                    sharedPreferences.getBoolean(KEY_IS_FINISHED, true),
                    sharedPreferences.getInt(KEY_QUESTION_INDEX, 0),
                    sharedPreferences.getBoolean(KEY_IS_CURRENT_QUESTION_ANSWERED, false)
            );
        }
        return null;
    }

    private boolean hasStoredModel() {
        return !sharedPreferences.getAll().isEmpty();
    }
}
