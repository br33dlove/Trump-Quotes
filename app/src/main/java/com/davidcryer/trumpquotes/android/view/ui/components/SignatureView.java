package com.davidcryer.trumpquotes.android.view.ui.components;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.widget.TextView;

import com.davidcryer.trumpquotes.R;

public class SignatureView extends TextView {

    public SignatureView(Context context) {
        super(context);
    }

    public SignatureView(Context context, AttributeSet attrs) {
        super(context, attrs);
        source(attrs);
    }

    private void source(final AttributeSet attrs) {
        final TypedArray array = getContext().obtainStyledAttributes(attrs, R.styleable.SignatureView);
        setText(array.getText(R.styleable.SignatureView_quote_source));
        array.recycle();
    }

    public void source(final String source) {
        setText(source);
    }
}
