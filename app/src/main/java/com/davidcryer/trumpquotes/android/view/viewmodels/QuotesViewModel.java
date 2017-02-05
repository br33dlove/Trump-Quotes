package com.davidcryer.trumpquotes.android.view.viewmodels;

import android.os.Parcel;

import com.davidcryer.trumpquotes.android.view.ui.QuotesAndroidView;
import com.davidcryer.trumpquotes.android.view.viewmodels.models.AndroidViewQuote;

import java.util.Arrays;
import java.util.List;

public class QuotesViewModel implements QuotesAndroidViewModel {
    private AndroidViewQuote newQuote;
    private List<AndroidViewQuote> quoteHistory;
    private boolean showLoadingNewQuote;
    private boolean newQuoteUpdated;
    private boolean quoteHistoryUpdated;

    public QuotesViewModel(
            AndroidViewQuote newQuote,
            List<AndroidViewQuote> quoteHistory,
            boolean showLoadingNewQuote,
            boolean quoteHistoryUpdated,
            boolean newQuoteUpdated
    ) {
        this.newQuote = newQuote;
        this.quoteHistory = quoteHistory;
        this.showLoadingNewQuote = showLoadingNewQuote;
        this.quoteHistoryUpdated = quoteHistoryUpdated;
        this.newQuoteUpdated = newQuoteUpdated;
    }

    private QuotesViewModel(final Parcel parcel) {
        newQuote = parcel.readParcelable(AndroidViewQuote.class.getClassLoader());
        quoteHistory = Arrays.asList((AndroidViewQuote[]) parcel.readParcelableArray(AndroidViewQuote.class.getClassLoader()));
        showLoadingNewQuote = parcel.readByte() != 0;
        quoteHistoryUpdated = parcel.readByte() != 0;
        newQuoteUpdated = parcel.readByte() != 0;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(newQuote, PARCELABLE_WRITE_RETURN_VALUE);
        dest.writeParcelableArray(quoteHistory.toArray(new AndroidViewQuote[quoteHistory.size()]), PARCELABLE_WRITE_RETURN_VALUE);
        dest.writeByte((byte) (showLoadingNewQuote ? 1 : 0));
        dest.writeByte((byte) (quoteHistoryUpdated ? 1 : 0));
        dest.writeByte((byte) (newQuoteUpdated ? 1 : 0));
    }

    static final Creator<QuotesAndroidViewModel> CREATOR = new Creator<QuotesAndroidViewModel>() {
        @Override
        public QuotesAndroidViewModel createFromParcel(Parcel source) {
            return new QuotesViewModel(source);
        }

        @Override
        public QuotesAndroidViewModel[] newArray(int size) {
            return new QuotesAndroidViewModel[size];
        }
    };

    @Override
    public void showLoadingNewQuote(QuotesAndroidView view) {
        showLoadingNewQuote = true;
        if (view != null) {
            view.showLoadingNewQuote();
        }
    }

    @Override
    public void hideLoadingNewQuote(QuotesAndroidView view) {
        showLoadingNewQuote = false;
        if (view != null) {
            view.hideLoadingNewQuote();
        }
    }

    @Override
    public void showNewQuote(QuotesAndroidView view, AndroidViewQuote quote) {
        newQuote = quote;
        if (view != null) {
            view.showNewQuote(quote);
        } else {
            newQuoteUpdated = true;
        }
    }

    @Override
    public void addNewQuoteToHistory(QuotesAndroidView view, AndroidViewQuote quote) {
        quoteHistory.add(0, quote);
        if (view != null) {
            view.updateQuoteHistoryForInsert(0);
        } else {
            quoteHistoryUpdated = true;
        }
    }

    @Override
    public void showQuoteHistory(QuotesAndroidView view, List<AndroidViewQuote> quotes) {
        quoteHistory = quotes;
        if (view != null) {
            view.showQuoteHistory(quotes);
        }
    }

    @Override
    public void removeQuoteInHistory(QuotesAndroidView view, AndroidViewQuote quote) {
        if (view != null) {
            final int indexOfRemovedQuote = quoteHistory.indexOf(quote);
            quoteHistory.remove(quote);
            view.updateQuoteHistoryForRemoval(indexOfRemovedQuote);
        }
    }

    @Override
    public void removeAllQuotesInHistory(QuotesAndroidView view) {
        if (view != null) {
            quoteHistory.clear();
            view.removeAllQuotesInHistory();
        }
    }

    @Override
    public void onto(QuotesAndroidView view, final boolean setAllData) {
        if (showLoadingNewQuote) {
            view.showLoadingNewQuote();
        } else {
            view.hideLoadingNewQuote();
        }
        if (setAllData || newQuoteUpdated) {
            newQuoteUpdated = false;
            view.showNewQuote(newQuote);
        }
        if (setAllData || quoteHistoryUpdated) {
            quoteHistoryUpdated = false;
            view.showQuoteHistory(quoteHistory);
        }
    }

    @Override
    public AndroidViewQuote newQuote() {
        return newQuote;
    }

    @Override
    public AndroidViewQuote[] quoteHistory() {
        return quoteHistory.toArray(new AndroidViewQuote[quoteHistory.size()]);
    }

    @Override
    public int indexOfQuoteInHistory(AndroidViewQuote viewQuote) {
        return quoteHistory.indexOf(viewQuote);
    }
}
