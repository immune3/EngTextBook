package com.yellowgreen.engtextbook.Activity;

import android.graphics.Color;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;

import com.yellowgreen.engtextbook.R;
import com.yellowgreen.engtextbook.Util.Sentence;

/**
 * Created by mapl0 on 2018-03-12.
 */

public class PracticePage extends BaseActivity {

    private LinearLayout parents;private LinearLayout contents;
    private LinearLayout[] linArr;
    private int count = 0;

    @Override
    public void setUpEvents() {
        super.setUpEvents();

        contents.getWidth();

        parents.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                contents.removeAllViews();
                Sentence sen = new Sentence();

                //띄어쓰기를 기준으로 TextView를 나누어서 부모뷰의 가로 길이에 맞게 동적으로 레이아웃 배열을 반환하여줌.
                //글자크기와 컬러지정 가능

                linArr = sen.getTextLayout("Tom plays the piano and Tim plays the harp", 35, 5, "#0000ff", myContext, contents);

//                이렇게 사용할 수도 있음
//                linArr = sen.getTextLayout("Tom plays the piano and Tim plays the harp", 35, myContext, contents);
//                linArr = sen.getTextLayout("Tom plays the piano and Tim plays the harp", myContext, contents);

//                String Picker Get Text 예상 사용법
//                문장 / {p} 부분에 들어갈 배열 / 글자크기 / 컬러지정 인덱스 / 색깔 / 컨텍스트 / 부모뷰

//                linArr = sen.getTextLayout("Kate is from {p}", new String[]{"London", "Canada", "England", "Korea", "China"} , sen.getAutoFontSize(contents), 5, "#0000ff", myContext, contents);

                addSentenceLayout(linArr);

            }
        });
    }

    @Override
    public void setValues() {
        super.setValues();
        parents.setBackgroundResource(R.drawable.practice);
        contents.setBackgroundColor(Color.WHITE);
    }

    private void addSentenceLayout(LinearLayout[] linArr) {

        Log.d("linArrLength", linArr.length + "");
        for(int i = 0; i< linArr.length; i++) {
            contents.addView(linArr[i]);
            Log.d("addCount", i + "");
        }

    }

    @Override
    public void bindVies() {
        super.bindVies();
        setContentView(R.layout.practice);

        this.contents = (LinearLayout) findViewById(R.id.contents);
        this.parents = (LinearLayout) findViewById(R.id.parents);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        parents = null;
    }
}
