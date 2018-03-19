package com.yellowgreen.engtextbook.Layout;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v4.view.MotionEventCompat;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Created by mapl0 on 2018-03-19.
 */

public class MoveCheckLinearLayout extends LinearLayout {

    private float posX1, posX2;
    private float distance;
    private int defaultDistance = 150;

    public MoveCheckLinearLayout(Context context) {
        super(context);
    }

    public MoveCheckLinearLayout(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public MoveCheckLinearLayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {

        final int action = ev.getAction() & MotionEvent.ACTION_MASK;

        switch(action) {

            case MotionEvent.ACTION_DOWN:
                posX1 = ev.getX();
                break;
            case MotionEvent.ACTION_UP:
                posX2 = ev.getX();
                distance = posX2 - posX1;

                if(distance < -defaultDistance || distance > defaultDistance) {
                    Log.d("Move", "true");
                    return true;
                }
                else {
                    Log.d("Move", "false");
                    return false;
                }
        }

        return super.onInterceptTouchEvent(ev);
    }

}
