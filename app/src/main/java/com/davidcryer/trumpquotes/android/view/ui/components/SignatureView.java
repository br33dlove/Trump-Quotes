package com.davidcryer.trumpquotes.android.view.ui.components;

import android.content.Context;
import android.support.v7.widget.AppCompatTextView;
import android.util.AttributeSet;

public class SignatureView extends AppCompatTextView {

    public SignatureView(Context context) {
        super(context);
    }

    public SignatureView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public void signature(final String signature) {
        setText(signature);
    }
}
