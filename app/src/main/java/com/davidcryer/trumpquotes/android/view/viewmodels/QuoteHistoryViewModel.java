package com.davidcryer.trumpquotes.android.view.viewmodels;

import android.os.Parcel;

import com.davidcryer.trumpquotes.android.view.ui.QuoteHistoryAndroidView;
import com.davidcryer.trumpquotes.android.view.viewmodels.models.AndroidViewQuote;

import java.util.Arrays;
import java.util.List;

public class QuoteHistoryViewModel implements QuoteHistoryAndroidViewModel {
    private List<AndroidViewQuote> quoteHistory;
    private boolean quoteHistoryUpdated;

    public QuoteHistoryViewModel(List<AndroidViewQuote> quoteHistory, boolean quoteHistoryUpdated) {
        this.quoteHistory = quoteHistory;
        this.quoteHistoryUpdated = quoteHistoryUpdated;
    }

    private QuoteHistoryViewModel(final Parcel parcel) {
        quoteHistory = Arrays.asList((AndroidViewQuote[]) parcel.readParcelableArray(AndroidViewQuote.class.getClassLoader()));
        quoteHistoryUpdated = parcel.readByte() != 0;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelableArray(quoteHistory.toArray(new AndroidViewQuote[quoteHistory.size()]), PARCELABLE_WRITE_RETURN_VALUE);
        dest.writeByte((byte) (quoteHistoryUpdated ? 1 : 0));
    }

    static final Creator<QuoteHistoryViewModel> CREATOR = new Creator<QuoteHistoryViewModel>() {
        @Override
        public QuoteHistoryViewModel createFromParcel(Parcel source) {
            return new QuoteHistoryViewModel(source);
        }

        @Override
        public QuoteHistoryViewModel[] newArray(int size) {
            return new QuoteHistoryViewModel[size];
        }
    };

    @Override
    public void showQuoteHistory(QuoteHistoryAndroidView view, List<AndroidViewQuote> quotes) {
        quoteHistory = quotes;
        if (view != null) {
            view.showQuoteHistory(quotes);
        }
    }

    @Override
    public void removeQuoteInHistory(QuoteHistoryAndroidView view, AndroidViewQuote quote) {
        if (view != null) {
            final int indexOfRemovedQuote = quoteHistory.indexOf(quote);
            quoteHistory.remove(quote);
            view.updateQuoteHistoryForRemoval(indexOfRemovedQuote);
        }
    }

    @Override
    public void removeAllQuotesInHistory(QuoteHistoryAndroidView view) {
        if (view != null) {
            quoteHistory.clear();
            view.removeAllQuotesInHistory();
        }
    }

    @Override
    public AndroidViewQuote[] quoteHistory() {
        return quoteHistory.toArray(new AndroidViewQuote[quoteHistory.size()]);
    }

    @Override
    public int indexOfQuoteInHistory(AndroidViewQuote viewQuote) {
        return quoteHistory.indexOf(viewQuote);
    }

    @Override
    public void onto(QuoteHistoryAndroidView view, boolean setAllData) {
        if (setAllData || quoteHistoryUpdated) {
            quoteHistoryUpdated = false;
            view.showQuoteHistory(quoteHistory);
        }
    }
}
