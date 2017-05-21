package com.davidcryer.trumpquotes.android.presenter.quiz.uimodels.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.davidcryer.trumpquotes.platformindependent.model.domainentities.QuizQuestion;

public class ViewQuestion implements Parcelable {
    private final String quote;
    private final String optionA;
    private final String optionB;

    private ViewQuestion(String quote, String optionA, String optionB) {
        this.quote = quote;
        this.optionA = optionA;
        this.optionB = optionB;
    }

    private ViewQuestion(final Parcel parcel) {
        quote = parcel.readString();
        optionA = parcel.readString();
        optionB = parcel.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(quote);
        dest.writeString(optionA);
        dest.writeString(optionB);
    }

    static final Creator<ViewQuestion> CREATOR = new Creator<ViewQuestion>() {
        @Override
        public ViewQuestion createFromParcel(Parcel source) {
            return new ViewQuestion(source);
        }

        @Override
        public ViewQuestion[] newArray(int size) {
            return new ViewQuestion[size];
        }
    };

    public String quote() {
        return quote;
    }

    public String optionA() {
        return optionA;
    }

    public String optionB() {
        return optionB;
    }

    public static class Factory {

        private Factory() {}

        public static ViewQuestion create(final QuizQuestion quizQuestion) {
            return new ViewQuestion(quizQuestion.quote(), quizQuestion.optionA(), quizQuestion.optionB());
        }
    }
}
