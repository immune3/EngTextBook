package com.yellowgreen.engtextbook.Util;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.util.Log;
import android.view.ContextThemeWrapper;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.NumberPicker;
import android.widget.TextView;

import com.yellowgreen.engtextbook.R;


/**
 * Created by mapl0 on 2018-03-13.
 */

public class Sentence {

    private String[] sentenceArr;
    private int fontDpSize = 20;
    private float space = 1/4f; // 글자 크기 dp의 4등분을 공백으로 사용한다는 뜻

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

            params.setMargins(TextUtil.dpToPixel((int)(fontDpSize * space), context), 0, TextUtil.dpToPixel((int)(fontDpSize * space), context), 0);
            sentenceTextArr[i].setLayoutParams(params);
            sentenceTextArr[i].setTextColor(Color.BLACK);
            sentenceTextArr[i].setTypeface(Typeface.DEFAULT_BOLD);

            sentenceTextArr[i].setSingleLine();

            //부모 뷰에서 onDraw 하기 전에 길이를 알아오기 위함
            sentenceTextArr[i].measure(View.MeasureSpec.UNSPECIFIED, View.MeasureSpec.UNSPECIFIED);
            textViewTotalWidth += sentenceTextArr[i].getMeasuredWidth();
        }

        Log.d("TextViewTotalWidth", textViewTotalWidth + "");
        Log.d("parentWidth", parent.getWidth() + "");
        Log.d("width/parent", textViewTotalWidth / parent.getWidth() + "");

        LinearLayout[] linearTextBox;
        linearTextBox = new LinearLayout[textViewTotalWidth / parent.getWidth() + 1];

        for (int i = 0; i < linearTextBox.length; i++) {
            linearTextBox[i] = new LinearLayout(context);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            params.setMargins(0, TextUtil.dpToPixel((int)(fontDpSize * space), context), 0, TextUtil.dpToPixel((int)(fontDpSize * space), context));
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
            if (textViewSum < (parent.getWidth() - TextUtil.dpToPixel((int)(fontDpSize * sentenceTextArr.length * space * 2), context))) {
                linearTextBox[linearCount].addView(sentenceTextArr[textViewCount++]);
            } else {
                // 클 경우에는 0으로 초기화시켜주고 다음 레이아웃으로 넘어감
                textViewSum = 0;
                linearCount++;
            }

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

            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                    ViewGroup.LayoutParams.WRAP_CONTENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT);

            params.setMargins(TextUtil.dpToPixel((int)(fontDpSize * space), context), 0, TextUtil.dpToPixel((int)(fontDpSize * space), context), 0);
            sentenceTextArr[i].setLayoutParams(params);
            sentenceTextArr[i].setTextColor(Color.BLACK);
            sentenceTextArr[i].setTypeface(Typeface.DEFAULT_BOLD);

            sentenceTextArr[i].setSingleLine();

            //부모 뷰에서 onDraw 하기 전에 길이를 알아오기 위함
            sentenceTextArr[i].measure(View.MeasureSpec.UNSPECIFIED, View.MeasureSpec.UNSPECIFIED);
            textViewTotalWidth += sentenceTextArr[i].getMeasuredWidth();
        }

        Log.d("TextViewTotalWidth", textViewTotalWidth + "");
        Log.d("parentWidth", parent.getWidth() + "");

        LinearLayout[] linearTextBox;
        linearTextBox = new LinearLayout[textViewTotalWidth / parent.getWidth() + 1];

        for (int i = 0; i < linearTextBox.length; i++) {
            linearTextBox[i] = new LinearLayout(context);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            params.setMargins(0, TextUtil.dpToPixel((int)(fontDpSize * space), context), 0, TextUtil.dpToPixel((int)(fontDpSize * space), context));
            linearTextBox[i].setLayoutParams(params);
            linearTextBox[i].setOrientation(LinearLayout.HORIZONTAL);
        }

        int linearCount = 0; // Linear Layout 배열의 index
        int textViewCount = 0; // 텍스트뷰의 갯수
        int textViewSum = 0; // 각 텍스트뷰의 가로 길이의 합 -> parent의 가로와 비교하기 위해 사용

        while (textViewCount < sentenceTextArr.length) {

            textViewSum += sentenceTextArr[textViewCount].getMeasuredWidth();
            if (textViewSum < (parent.getWidth() - TextUtil.dpToPixel((int)(fontDpSize * sentenceTextArr.length * space * 2), context))) { // 폰트사이즈 * 텍스트뷰의 개수 * 공백 등분 * 단어 하나당 공백 두개가 들어가 있음
                linearTextBox[linearCount].addView(sentenceTextArr[textViewCount++]);
            } else {
                textViewSum = 0;
                linearCount++;
            }

        }

        return linearTextBox;
    }

    //colorIndex의 TextView의 글자색을 color로 바꿈. 인덱스 혼란을 줄이기 위해 첫번째 텍스트뷰는 1로 함.
    public LinearLayout[] getTextLayout(String str, int fontDpSize, int colorIndex, String color, Context context, ViewGroup parent) {

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

            params.setMargins(TextUtil.dpToPixel((int)(fontDpSize * space), context), 0, TextUtil.dpToPixel((int)(fontDpSize * space), context), 0);

            sentenceTextArr[i].setLayoutParams(params);
            sentenceTextArr[i].setTypeface(Typeface.DEFAULT_BOLD);
            sentenceTextArr[i].setSingleLine();

            if(i == (colorIndex -1)) {
                try{
                    sentenceTextArr[i].setTextColor(Color.parseColor(color));
                } catch(Exception e) {
                    Log.e("ColorStringError", "지정한 RGB형식 확인 : " + color);
                    e.printStackTrace();
                }

            } else sentenceTextArr[i].setTextColor(Color.BLACK);

            //부모 뷰에서 onDraw 하기 전에 길이를 알아오기 위함
            sentenceTextArr[i].measure(View.MeasureSpec.UNSPECIFIED, View.MeasureSpec.UNSPECIFIED);
            textViewTotalWidth += sentenceTextArr[i].getMeasuredWidth();
        }

        Log.d("TextViewTotalWidth", textViewTotalWidth + "");
        Log.d("parentWidth", parent.getWidth() + "");
        Log.d("width/parent", textViewTotalWidth / parent.getWidth() + "");

        LinearLayout[] linearTextBox;
        linearTextBox = new LinearLayout[textViewTotalWidth / parent.getWidth() + 1];

        for (int i = 0; i < linearTextBox.length; i++) {
            linearTextBox[i] = new LinearLayout(context);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            params.setMargins(0, TextUtil.dpToPixel((int)(fontDpSize * space), context), 0, TextUtil.dpToPixel((int)(fontDpSize * space), context));
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
            if (textViewSum < (parent.getWidth() - TextUtil.dpToPixel((int)(fontDpSize * sentenceTextArr.length * space * 2), context))) {
                linearTextBox[linearCount].addView(sentenceTextArr[textViewCount++]);
            } else {
                // 클 경우에는 0으로 초기화시켜주고 다음 레이아웃으로 넘어감
                textViewSum = 0;
                linearCount++;
            }

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

        TextView[] sentenceTextArr = new TextView[sentenceArr.length];
        for (int i = 0; i < sentenceTextArr.length; i++) {
            sentenceTextArr[i] = new TextView(context);
            sentenceTextArr[i].setText(sentenceArr[i]);
            sentenceTextArr[i].setTextSize(TextUtil.dpToPixel(fontDpSize, context));

            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                    ViewGroup.LayoutParams.WRAP_CONTENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT);

            params.setMargins(TextUtil.dpToPixel((int)(fontDpSize * space), context), 0, TextUtil.dpToPixel((int)(fontDpSize * space), context), 0);

            sentenceTextArr[i].setLayoutParams(params);
            sentenceTextArr[i].setTypeface(Typeface.DEFAULT_BOLD);
            sentenceTextArr[i].setSingleLine();

            if(i == (colorIndex -1)) {
                try{
                    sentenceTextArr[i].setTextColor(Color.parseColor(color));
                } catch(Exception e) {
                    Log.e("ColorStringError", "지정한 RGB형식 확인 : " + color);
                    e.printStackTrace();
                }

            } else sentenceTextArr[i].setTextColor(Color.WHITE);

            //부모 뷰에서 onDraw 하기 전에 길이를 알아오기 위함
            sentenceTextArr[i].measure(View.MeasureSpec.UNSPECIFIED, View.MeasureSpec.UNSPECIFIED);
            textViewTotalWidth += sentenceTextArr[i].getMeasuredWidth();
        }

        Log.d("TextViewTotalWidth", textViewTotalWidth + "");
        Log.d("parentWidth", parent.getWidth() + "");
        Log.d("width/parent", textViewTotalWidth / parent.getWidth() + "");

        LinearLayout[] linearTextBox;
        linearTextBox = new LinearLayout[textViewTotalWidth / parent.getWidth() + 1];

        for (int i = 0; i < linearTextBox.length; i++) {
            linearTextBox[i] = new LinearLayout(context);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            params.setMargins(0, TextUtil.dpToPixel((int)(fontDpSize * space), context), 0, TextUtil.dpToPixel((int)(fontDpSize * space), context));
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
            if (textViewSum < (parent.getWidth() - TextUtil.dpToPixel((int)(fontDpSize * sentenceTextArr.length * space * 2), context))) {

                if(sentenceTextArr[textViewCount].getText().toString().equals("{p}")){
                    sentenceTextArr[textViewCount] = null;
                    linearTextBox[linearCount].addView(pickers);
                    textViewCount++;

                } else {
                    linearTextBox[linearCount].addView(sentenceTextArr[textViewCount++]);
                }

            } else {
                // 클 경우에는 0으로 초기화시켜주고 다음 레이아웃으로 넘어감
                textViewSum = 0;
                linearCount++;
            }

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

}
