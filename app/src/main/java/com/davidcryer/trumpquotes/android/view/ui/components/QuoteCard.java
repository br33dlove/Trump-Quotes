package com.davidcryer.trumpquotes.android.view.ui.components;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.util.AttributeSet;
import android.widget.TextView;

import com.davidcryer.trumpquotes.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class QuoteCard extends CardView {
    @BindView(R.id.quote)
    TextView quoteView;
    @BindView(R.id.signature)
    SignatureSwitcherLayout signatureSwitcherLayout;

    public QuoteCard(Context context) {
        super(context);
        init();
    }

    public QuoteCard(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        inflate(getContext(), R.layout.container_quote_card, this);
        ButterKnife.bind(this);
    }

    public void quote(final String quote) {
        quoteView.setText(quote);
    }

    public void updateSignature(final float percentageOffsetFromCentreX) {
        signatureSwitcherLayout.update(percentageOffsetFromCentreX);
    }
}
