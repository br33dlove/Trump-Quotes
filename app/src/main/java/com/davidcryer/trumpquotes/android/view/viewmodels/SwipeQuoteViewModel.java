package com.davidcryer.trumpquotes.android.view.viewmodels;

import android.os.Parcel;

import com.davidcryer.trumpquotes.android.view.ui.SwipeQuoteAndroidView;
import com.davidcryer.trumpquotes.android.view.viewmodels.models.AndroidViewQuote;

public final class SwipeQuoteViewModel implements SwipeQuoteAndroidViewModel {
    public enum State {QUOTE, LOADING, LOADING_FAILED}
    private AndroidViewQuote quote;
    private State state;
    private boolean quoteUpdated;

    public SwipeQuoteViewModel(
            AndroidViewQuote quote,
            State state,
            boolean quoteUpdated
    ) {
        this.quote = quote;
        this.state = state;
        this.quoteUpdated = quoteUpdated;
    }

    private SwipeQuoteViewModel(final Parcel parcel) {
        quote = parcel.readParcelable(AndroidViewQuote.class.getClassLoader());
        state = (State) parcel.readSerializable();
        quoteUpdated = parcel.readByte() != 0;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(quote, PARCELABLE_WRITE_RETURN_VALUE);
        dest.writeSerializable(state);
        dest.writeByte((byte) (quoteUpdated ? 1 : 0));
    }

    static final Creator<SwipeQuoteAndroidViewModel> CREATOR = new Creator<SwipeQuoteAndroidViewModel>() {
        @Override
        public SwipeQuoteAndroidViewModel createFromParcel(Parcel source) {
            return new SwipeQuoteViewModel(source);
        }

        @Override
        public SwipeQuoteAndroidViewModel[] newArray(int size) {
            return new SwipeQuoteAndroidViewModel[size];
        }
    };

    @Override
    public void showQuoteState(SwipeQuoteAndroidView view, AndroidViewQuote quote) {
        this.quote = quote;
        state = State.QUOTE;
        if (view != null) {
            view.showQuoteState(quote);
        } else {
            quoteUpdated = true;
        }
    }

    @Override
    public void showLoadingQuoteState(SwipeQuoteAndroidView view) {
        state = State.LOADING;
        if (view != null) {
            view.showLoadingQuoteState();
        }
    }

    @Override
    public void showFailureToGetQuoteState(SwipeQuoteAndroidView view) {
        state = State.LOADING_FAILED;
        if (view != null) {
            view.showFailureToGetQuoteState();
        }
    }

    @Override
    public void onto(SwipeQuoteAndroidView view, final boolean setAllData) {
        switch (state) {
            case QUOTE: {
                if (setAllData || quoteUpdated) {
                    quoteUpdated = false;
                    view.showQuoteState(quote);
                }
                break;
            }
            case LOADING: {
                view.showLoadingQuoteState();
                break;
            }
            case LOADING_FAILED: {
                view.showLoadingQuoteState();
                break;
            }
        }
    }

    @Override
    public AndroidViewQuote newQuote() {
        return quote;
    }
}
