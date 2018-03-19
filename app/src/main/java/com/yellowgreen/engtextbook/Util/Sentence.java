package com.yellowgreen.engtextbook.Util;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Typeface;
import android.util.Log;
import android.view.ContextThemeWrapper;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.NumberPicker;
import android.widget.TextView;

import com.yellowgreen.engtextbook.Activity.BaseActivity;
import com.yellowgreen.engtextbook.Layout.MoveCheckLinearLayout;
import com.yellowgreen.engtextbook.R;

import java.io.IOException;
import java.lang.reflect.Field;


/**
 * Created by mapl0 on 2018-03-13.
 */

public class Sentence {

    private String[] sentenceArr;
    private int fontDpSize = 20;
    private float space = 1 / 2f; // 글자 크기 dp의 2등분을 공백으로 사용한다는 뜻

    public Sentence(String str) {
        sentenceArr = str.split(" ");
    }

    public Sentence() {
    }

    public void setSentence(String str) {
        sentenceArr = str.split(" ");
    }

    public String[] getSentence() {
        for (int i = 0; i < sentenceArr.length; i++) {
            Log.d("문장 : " + i, sentenceArr[i]);
        }
        return sentenceArr;
    }

    public int getAutoFontSize(ViewGroup parent) {
        fontDpSize = 35;

//        Log.d("parentWidthDP", TextUtil.pxToDp(1152, BaseActivity.myContext) + "");
//        Log.d("font/parentWidthDP", 35/TextUtil.pxToDp(1152, BaseActivity.myContext) + "");
//
//        float autoSizeRate = 1152f/(float)BaseActivity.myContext.getResources().getDisplayMetrics().widthPixels;
//        Log.d("autoSizeRate", autoSizeRate + "");
//
//        fontDpSize *= autoSizeRate;
//        Log.d("ReSizeFontSize", fontDpSize + "");

        return fontDpSize;
    }

    public LinearLayout[] getTextLayout(String str, int fontDpSize, Context context, ViewGroup parent) {

        int textViewTotalWidth = 0; // textView의 가로 길이의 총합

        setSentence(str);

        TextView[] sentenceTextArr = new TextView[sentenceArr.length];
        for (int i = 0; i < sentenceTextArr.length; i++) {
            sentenceTextArr[i] = new TextView(context);
            sentenceTextArr[i].setText(sentenceArr[i]);
            sentenceTextArr[i].setTextSize(TextUtil.dpToPixel(fontDpSize, context));

            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                    ViewGroup.LayoutParams.WRAP_CONTENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT);

            params.setMargins((int) (fontDpSize * space), 0, (int) (fontDpSize * space), 0);
            sentenceTextArr[i].setLayoutParams(params);
            sentenceTextArr[i].setTextColor(Color.BLACK);
            sentenceTextArr[i].setTypeface(Typeface.DEFAULT_BOLD);

            sentenceTextArr[i].setSingleLine();

            //부모 뷰에서 onDraw 하기 전에 길이를 알아오기 위함
            sentenceTextArr[i].measure(View.MeasureSpec.UNSPECIFIED, View.MeasureSpec.UNSPECIFIED);
            textViewTotalWidth += (sentenceTextArr[i].getMeasuredWidth() + fontDpSize * space * 2);
        }

        Log.d("TextViewTotalWidth", textViewTotalWidth + "");
        Log.d("parentWidth", parent.getWidth() + "");
        Log.d("width/parent", textViewTotalWidth / parent.getWidth() + "");

        LinearLayout[] linearTextBox;
        linearTextBox = new LinearLayout[textViewTotalWidth / parent.getWidth() + 1];

        for (int i = 0; i < linearTextBox.length; i++) {
            linearTextBox[i] = new LinearLayout(context);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            params.setMargins(0, (int) (fontDpSize * space), 0, (int) (fontDpSize * space));
            linearTextBox[i].setLayoutParams(params);
            linearTextBox[i].setOrientation(LinearLayout.HORIZONTAL);
        }

        int linearCount = 0; // Linear Layout 배열의 index
        int textViewCount = 0; // 텍스트뷰의 갯수
        int textViewSum = 0; // 각 텍스트뷰의 가로 길이의 합 -> parent의 가로와 비교하기 위해 사용

        while (textViewCount < sentenceTextArr.length) {

            textViewSum += sentenceTextArr[textViewCount].getMeasuredWidth();

            //텍스트뷰의 총합이 부모뷰의 가로보다 작을 경우에 addView 시켜준다.
            // 폰트사이즈 * 텍스트뷰의 개수 * 공백 등분 * 단어 하나당 공백 두개가 들어가 있음
            if (linearCount < linearTextBox.length && textViewCount <= sentenceTextArr.length &&
                    textViewSum < (parent.getWidth() - fontDpSize * sentenceTextArr.length * space * 2)) {
                Log.d("IF", "if");

                Log.d("textViewCount", textViewCount + "");
                Log.d("sentenceTextArr.length", sentenceTextArr.length + "");

                Log.d("linearCount", linearCount + "");
                Log.d("linearTextBox.length", linearTextBox.length + "");

                linearTextBox[linearCount].addView(sentenceTextArr[textViewCount++]);
            } else if (linearCount < linearTextBox.length) {
                // 클 경우에는 0으로 초기화시켜주고 다음 레이아웃으로 넘어감
                Log.d("Else if", "else if");
                textViewSum = 0;
                linearCount++;
            } else break;

        }

        return linearTextBox;
    }

    //이 경우 폰트 사이즈는 설정값으로 적용됨
    public LinearLayout[] getTextLayout(String str, Context context, ViewGroup parent) {

        int textViewTotalWidth = 0; // textView의 가로 길이의 총합

        setSentence(str);

        TextView[] sentenceTextArr = new TextView[sentenceArr.length];
        for (int i = 0; i < sentenceTextArr.length; i++) {
            sentenceTextArr[i] = new TextView(context);
            sentenceTextArr[i].setText(sentenceArr[i]);
            sentenceTextArr[i].setTextSize(TextUtil.dpToPixel(fontDpSize, context));
            sentenceTextArr[i].setTextSize(35);

            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                    ViewGroup.LayoutParams.WRAP_CONTENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT);

            params.setMargins((int) (fontDpSize * space), 0, (int) (fontDpSize * space), 0);
            sentenceTextArr[i].setLayoutParams(params);
            sentenceTextArr[i].setTextColor(Color.BLACK);
            sentenceTextArr[i].setTypeface(Typeface.DEFAULT_BOLD);

            sentenceTextArr[i].setSingleLine();

            //부모 뷰에서 onDraw 하기 전에 길이를 알아오기 위함
            sentenceTextArr[i].measure(View.MeasureSpec.UNSPECIFIED, View.MeasureSpec.UNSPECIFIED);
            textViewTotalWidth += (sentenceTextArr[i].getMeasuredWidth() + fontDpSize * space * 2);
        }

        Log.d("TextViewTotalWidth", textViewTotalWidth + "");
        Log.d("parentWidth", parent.getWidth() + "");

        LinearLayout[] linearTextBox;
        linearTextBox = new LinearLayout[textViewTotalWidth / parent.getWidth() + 1];

        for (int i = 0; i < linearTextBox.length; i++) {
            linearTextBox[i] = new LinearLayout(context);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            params.setMargins(0, (int) (fontDpSize * space), 0, (int) (fontDpSize * space));
            linearTextBox[i].setLayoutParams(params);
            linearTextBox[i].setOrientation(LinearLayout.HORIZONTAL);
        }

        int linearCount = 0; // Linear Layout 배열의 index
        int textViewCount = 0; // 텍스트뷰의 갯수
        int textViewSum = 0; // 각 텍스트뷰의 가로 길이의 합 -> parent의 가로와 비교하기 위해 사용

        while (textViewCount < sentenceTextArr.length) {

            textViewSum += sentenceTextArr[textViewCount].getMeasuredWidth();
            if (linearCount < linearTextBox.length && textViewCount <= sentenceTextArr.length &&
                    textViewSum < (parent.getWidth() - fontDpSize * sentenceTextArr.length * space * 2)) { // 폰트사이즈 * 텍스트뷰의 개수 * 공백 등분 * 단어 하나당 공백 두개가 들어가 있음
                Log.d("IF", "if");

                Log.d("textViewCount", textViewCount + "");
                Log.d("sentenceTextArr.length", sentenceTextArr.length + "");

                Log.d("linearCount", linearCount + "");
                Log.d("linearTextBox.length", linearTextBox.length + "");

                linearTextBox[linearCount].addView(sentenceTextArr[textViewCount++]);
            } else if (linearCount < linearTextBox.length) {
                // 클 경우에는 0으로 초기화시켜주고 다음 레이아웃으로 넘어감
                Log.d("Else if", "else if");
                textViewSum = 0;
                linearCount++;
            } else break;

        }

        return linearTextBox;
    }

    //effoctStr에 해당하는 텍스트뷰의 색을 color 변경
    public LinearLayout[] getTextLayout(String str, int fontDpSize, String effectStr, String color, final int[] res, final Context context, ViewGroup parent) {

        int textViewTotalWidth = 0; // textView의 가로 길이의 총합

        setSentence(str);

        TextView[] sentenceTextArr = new TextView[sentenceArr.length];

        for (int i = 0; i < sentenceArr.length; i++) {

            sentenceTextArr[i] = new TextView(context);
            sentenceTextArr[i].setText(sentenceArr[i]);
            Log.d("sentence" + i, sentenceArr[i]);
            sentenceTextArr[i].setTextSize(TextUtil.dpToPixel(fontDpSize, context));
            sentenceTextArr[i].setTextSize(35);

            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                    ViewGroup.LayoutParams.WRAP_CONTENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT);

            params.setMargins((int) (fontDpSize * space), 0, (int) (fontDpSize * space), 0);

            sentenceTextArr[i].setLayoutParams(params);
            sentenceTextArr[i].setTypeface(Typeface.DEFAULT_BOLD);
            sentenceTextArr[i].setSingleLine();

            if (sentenceTextArr[i].getText().toString().equals(effectStr)) {
                try {
                    sentenceTextArr[i].setTextColor(Color.parseColor(color));
                } catch (Exception e) {
                    Log.e("ColorStringError", "지정한 RGB형식 확인 : " + color);
                    e.printStackTrace();
                }

            } else sentenceTextArr[i].setTextColor(Color.BLACK);

            final int finalI = i;
            sentenceTextArr[i].setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View view, MotionEvent motionEvent) {

                    float posx1 = 0;
                    float posx2 = 0;
                    float distance;

                    switch (motionEvent.getAction()) {

                        case MotionEvent.ACTION_DOWN:
                            posx1 = motionEvent.getX();
                            break;

                        case MotionEvent.ACTION_UP:
                            posx2 = motionEvent.getX();

                            if (posx2 - posx1 < 150 && res[finalI] != -1 && !SoundManager.getInstance().getPlayer().isPlaying()) {
                                SoundManager.getInstance().setVoice(res[finalI], context);

                                try {
//                                    SoundManager.getInstance().getPlayer().prepare();
                                    SoundManager.getInstance().getPlayer().start();
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                                break;

                            } else return false;

                    }

                    return true;
                }
            });

            //부모 뷰에서 onDraw 하기 전에 길이를 알아오기 위함
            sentenceTextArr[i].measure(View.MeasureSpec.UNSPECIFIED, View.MeasureSpec.UNSPECIFIED);
            textViewTotalWidth += (sentenceTextArr[i].getMeasuredWidth() + fontDpSize * space * 2);
            Log.d("getMeasuredWidth() " + i, sentenceTextArr[i].getMeasuredWidth() + "");
        }

        Log.d("TextViewTotalWidth", textViewTotalWidth + "");
        Log.d("parentWidth", parent.getWidth() + "");
        Log.d("width/parent", (float) textViewTotalWidth / (float) parent.getWidth() + "");

        final MoveCheckLinearLayout[] linearTextBox;
        linearTextBox = new MoveCheckLinearLayout[textViewTotalWidth / parent.getWidth() + 2]; // effectStr로 인한 개행 추가

        for (int i = 0; i < linearTextBox.length; i++) {
            linearTextBox[i] = new MoveCheckLinearLayout(context);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            params.setMargins(0, (int) (fontDpSize * space), 0, (int) (fontDpSize * space));
            linearTextBox[i].setLayoutParams(params);
            linearTextBox[i].setOrientation(LinearLayout.HORIZONTAL);

            final int finalI = i;
            linearTextBox[i].setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View view, MotionEvent motionEvent) {

                    float posx1 = 0;
                    float posx2 = 0;

                    switch(motionEvent.getAction()) {

                        case MotionEvent.ACTION_UP:
                            posx2 = motionEvent.getX();
                            Log.d("posx2", posx2 + "");
                            float distance = Math.abs(posx2 - posx1);

                            if(linearTextBox[finalI].onInterceptTouchEvent(motionEvent) && !SoundManager.getInstance().getPlayer().isPlaying()) {
                                try {
                                    SoundManager.getInstance().setVoice(res[sentenceArr.length], context);
                                    SoundManager.getInstance().getPlayer().start();
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                                Log.d("MoveEvent", "Move");
                                return true;
                            }

                    }

                    return true;
                }
            });
        }

        int linearCount = 0; // Linear Layout 배열의 index
        int textViewCount = 0; // 텍스트뷰의 갯수
        int textViewSum = 0; // 각 텍스트뷰의 가로 길이의 합 -> parent의 가로와 비교하기 위해 사용

        while (textViewCount < sentenceTextArr.length) {

            textViewSum += sentenceTextArr[textViewCount].getMeasuredWidth();

            //텍스트뷰의 총합이 부모뷰의 가로보다 작을 경우에 addView 시켜준다.
            // 폰트사이즈 * 텍스트뷰의 개수 * 공백 등분 * 단어 하나당 공백 두개가 들어가 있음
            if (linearCount < linearTextBox.length && textViewCount <= sentenceTextArr.length &&
                    textViewSum < (parent.getWidth() - fontDpSize * sentenceTextArr.length * space * 2)) {

                Log.d("IF", "if");

                Log.d("textViewCount", textViewCount + "");
                Log.d("sentenceTextArr.length", sentenceTextArr.length + "");

                Log.d("linearCount", linearCount + "");
                Log.d("linearTextBox.length", linearTextBox.length + "");

                if (sentenceTextArr[textViewCount].getText().toString().equals(effectStr)) {
                    linearCount++;
                    textViewSum = 0;
                }

                linearTextBox[linearCount].addView(sentenceTextArr[textViewCount++]);
            } else if (linearCount < linearTextBox.length) {
                // 클 경우에는 0으로 초기화시켜주고 다음 레이아웃으로 넘어감
                Log.d("Else if", "else if");
                textViewSum = 0;
                linearCount++;
            } else break;

        }

        return linearTextBox;
    }

    //
    public LinearLayout[] getTextLayout(String str, String[] pickerArray, int fontDpSize, int colorIndex, String color, Context context, ViewGroup parent) {

        int textViewTotalWidth = 0; // textView의 가로 길이의 총합

        setSentence(str);

        ContextThemeWrapper cw = new ContextThemeWrapper(context, R.style.NumberPickerText);
        NumberPicker pickers = new NumberPicker(cw);

        //set min value zero
        pickers.setMinValue(0);
        //set max value from length array string reduced 1
        pickers.setMaxValue(pickerArray.length - 1);
        //implement array string to number picker
        pickers.setDisplayedValues(pickerArray);
        //disable soft keyboard
        pickers.setDescendantFocusability(NumberPicker.FOCUS_BLOCK_DESCENDANTS);
        //set wrap true or false, try it you will know the difference
        pickers.setWrapSelectorWheel(false);

        pickers.measure(View.MeasureSpec.UNSPECIFIED, View.MeasureSpec.UNSPECIFIED);
        pickers.getMeasuredWidth();

        setNumberPickerTextColor(pickers, Color.BLUE);

        TextView[] sentenceTextArr = new TextView[sentenceArr.length];
        for (int i = 0; i < sentenceTextArr.length; i++) {
            sentenceTextArr[i] = new TextView(context);
            sentenceTextArr[i].setText(sentenceArr[i]);
            sentenceTextArr[i].setTextSize(TextUtil.dpToPixel(fontDpSize, context));
            sentenceTextArr[i].setTextSize(35);

            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                    ViewGroup.LayoutParams.WRAP_CONTENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT);

            params.setMargins((int) (fontDpSize * space), 0, (int) (fontDpSize * space), 0);

            sentenceTextArr[i].setLayoutParams(params);
            sentenceTextArr[i].setTypeface(Typeface.DEFAULT_BOLD);
            sentenceTextArr[i].setSingleLine();
            sentenceTextArr[i].setGravity(Gravity.CENTER_VERTICAL);

            if (i == (colorIndex - 1)) {
                try {
                    sentenceTextArr[i].setTextColor(Color.parseColor(color));
                } catch (Exception e) {
                    Log.e("ColorStringError", "지정한 RGB형식 확인 : " + color);
                    e.printStackTrace();
                }

            } else sentenceTextArr[i].setTextColor(Color.BLACK);

            //부모 뷰에서 onDraw 하기 전에 길이를 알아오기 위함
            sentenceTextArr[i].measure(View.MeasureSpec.UNSPECIFIED, View.MeasureSpec.UNSPECIFIED);
            textViewTotalWidth += (sentenceTextArr[i].getMeasuredWidth() + fontDpSize * space * 2);
        }

        Log.d("TextViewTotalWidth", textViewTotalWidth + "");
        Log.d("parentWidth", parent.getWidth() + "");
        Log.d("width/parent", textViewTotalWidth / parent.getWidth() + "");

        LinearLayout[] linearTextBox;
        linearTextBox = new LinearLayout[textViewTotalWidth / parent.getWidth() + 1];

        for (int i = 0; i < linearTextBox.length; i++) {
            linearTextBox[i] = new LinearLayout(context);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            params.setMargins(0, (int) (fontDpSize * space), 0, (int) (fontDpSize * space));

            linearTextBox[i].setLayoutParams(params);
            linearTextBox[i].setVerticalGravity(Gravity.CENTER_VERTICAL);
            linearTextBox[i].setGravity(Gravity.CENTER_VERTICAL);
            linearTextBox[i].setOrientation(LinearLayout.HORIZONTAL);
        }

        int linearCount = 0; // Linear Layout 배열의 index
        int textViewCount = 0; // 텍스트뷰의 갯수
        int textViewSum = 0; // 각 텍스트뷰의 가로 길이의 합 -> parent의 가로와 비교하기 위해 사용

        while (textViewCount < sentenceTextArr.length) {

            textViewSum += sentenceTextArr[textViewCount].getMeasuredWidth();

            //텍스트뷰의 총합이 부모뷰의 가로보다 작을 경우에 addView 시켜준다.
            // 폰트사이즈 * 텍스트뷰의 개수 * 공백 등분 * 단어 하나당 공백 두개가 들어가 있음
            if (linearCount < linearTextBox.length && textViewCount <= sentenceTextArr.length &&
                    textViewSum < (parent.getWidth() - fontDpSize * sentenceTextArr.length * space * 2)) {

                if (sentenceTextArr[textViewCount].getText().toString().equals("{p}")) {
                    sentenceTextArr[textViewCount] = null;

                    Log.d("IF", "if");

                    Log.d("textViewCount", textViewCount + "");
                    Log.d("sentenceTextArr.length", sentenceTextArr.length + "");

                    Log.d("linearCount", linearCount + "");
                    Log.d("linearTextBox.length", linearTextBox.length + "");

                    linearTextBox[linearCount].addView(pickers);
                    textViewCount++;

                } else linearTextBox[linearCount].addView(sentenceTextArr[textViewCount++]);

            } else if (linearCount < linearTextBox.length) {
                // 클 경우에는 0으로 초기화시켜주고 다음 레이아웃으로 넘어감
                Log.d("Else if", "else if");
                textViewSum = 0;
                linearCount++;
            } else break;

        }

        return linearTextBox;
    }

    public void setFontDpSize(int size) {
        fontDpSize = size;
    }

    //폰트 사이즈에 비례함 기본값 : 1/4
    public void setFontSpace(float sp) {
        space = sp;
    }

    public static boolean setNumberPickerTextColor(NumberPicker numberPicker, int color) {
        final int count = numberPicker.getChildCount();
        for (int i = 0; i < count; i++) {
            View child = numberPicker.getChildAt(i);
            if (child instanceof EditText) {
                try {
                    Field selectorWheelPaintField = numberPicker.getClass()
                            .getDeclaredField("mSelectorWheelPaint");
                    selectorWheelPaintField.setAccessible(true);
                    ((Paint) selectorWheelPaintField.get(numberPicker)).setColor(color);
                    ((EditText) child).setTextColor(color);
//                    ((EditText)child).setTypeface(Typeface.DEFAULT_BOLD);
//                    ((EditText)child).setTextSize(TextUtil.dpToPixel(35, BaseActivity.myContext));
                    numberPicker.invalidate();
                    return true;
                } catch (NoSuchFieldException e) {
                    Log.w("setNumberPickerTextColor", e);
                } catch (IllegalAccessException e) {
                    Log.w("setNumberPickerTextColor", e);
                } catch (IllegalArgumentException e) {
                    Log.w("setNumberPickerTextColor", e);
                }
            }
        }
        return false;
    }

}
