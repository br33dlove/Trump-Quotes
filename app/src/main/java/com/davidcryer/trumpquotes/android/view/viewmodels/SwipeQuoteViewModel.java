package com.davidcryer.trumpquotes.android.view.viewmodels;

import android.os.Parcel;

import com.davidcryer.trumpquotes.android.view.ui.SwipeQuoteAndroidView;
import com.davidcryer.trumpquotes.android.view.viewmodels.models.AndroidViewQuote;

public class SwipeQuoteViewModel implements SwipeQuoteAndroidViewModel {
    private AndroidViewQuote quote;
    private boolean showFailureToGetQuote;
    private boolean showLoadingQuote;
    private boolean quoteUpdated;

    public SwipeQuoteViewModel(
            AndroidViewQuote quote,
            boolean showFailureToGetQuote,
            boolean showLoadingQuote,
            boolean quoteUpdated
    ) {
        this.quote = quote;
        this.showFailureToGetQuote = showFailureToGetQuote;
        this.showLoadingQuote = showLoadingQuote;
        this.quoteUpdated = quoteUpdated;
    }

    private SwipeQuoteViewModel(final Parcel parcel) {
        quote = parcel.readParcelable(AndroidViewQuote.class.getClassLoader());
        showFailureToGetQuote = parcel.readByte() != 0;
        showLoadingQuote = parcel.readByte() != 0;
        quoteUpdated = parcel.readByte() != 0;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(quote, PARCELABLE_WRITE_RETURN_VALUE);
        dest.writeByte((byte) (showFailureToGetQuote ? 1 : 0));
        dest.writeByte((byte) (showLoadingQuote ? 1 : 0));
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
    public void showFailureToGetNewQuote(SwipeQuoteAndroidView view) {
        showFailureToGetQuote = true;
        if (view != null) {
            view.showFailureToGetQuote();
        }
    }

    @Override
    public void showLoadingNewQuote(SwipeQuoteAndroidView view) {
        showLoadingQuote = true;
        if (view != null) {
            view.showLoadingQuote();
        }
    }

    @Override
    public void hideLoadingNewQuote(SwipeQuoteAndroidView view) {
        showLoadingQuote = false;
        if (view != null) {
            view.hideLoadingQuote();
        }
    }

    @Override
    public void showNewQuote(SwipeQuoteAndroidView view, AndroidViewQuote quote) {
        this.quote = quote;
        if (view != null) {
            view.showQuote(quote);
        } else {
            quoteUpdated = true;
        }
    }

    @Override
    public void onto(SwipeQuoteAndroidView view, final boolean setAllData) {
        if (showLoadingQuote) {
            view.showLoadingQuote();
        } else {
            view.hideLoadingQuote();
        }
        if (showFailureToGetQuote) {
            view.showFailureToGetQuote();
        }
        if (setAllData || quoteUpdated) {
            quoteUpdated = false;
            view.showQuote(quote);
        }
    }

    @Override
    public AndroidViewQuote newQuote() {
        return quote;
    }
}
