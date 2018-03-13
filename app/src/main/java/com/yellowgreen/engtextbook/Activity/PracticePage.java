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
                linArr = sen.getTextLayout("Tom plays the piano and Tim plays the harp", 35, myContext, contents);
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
