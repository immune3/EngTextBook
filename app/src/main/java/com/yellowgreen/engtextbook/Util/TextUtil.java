package com.yellowgreen.engtextbook.Util;

import android.content.Context;
import android.util.TypedValue;

/**
 * Created by mapl0 on 2018-03-13.
 */

public class TextUtil {

    /*
    dp 값을 px로 변경
     */
    public static int dpToPixel(int dp, Context context) {

        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, context.getResources().getDisplayMetrics());
    }

    public static float pxToDp(int px, Context context) {
        return px / (context.getResources().getDisplayMetrics().densityDpi / 160f);
    }
}
