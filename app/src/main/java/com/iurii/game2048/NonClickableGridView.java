package com.iurii.game2048;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.GridView;

public class NonClickableGridView extends GridView {

    public NonClickableGridView(Context context) {
        super(context);
    }

    public NonClickableGridView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public NonClickableGridView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        return false;
    }
}