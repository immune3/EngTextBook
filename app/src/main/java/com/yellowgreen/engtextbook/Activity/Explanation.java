package com.yellowgreen.engtextbook.Activity;

import android.content.Intent;
import android.view.View;
import android.widget.LinearLayout;

import com.yellowgreen.engtextbook.R;

/**
 * Created by mapl0 on 2018-03-12.
 */

public class Explanation extends BaseActivity {

    private LinearLayout parents;

    @Override
    public void setUpEvents() {
        super.setUpEvents();

        parents.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(myContext, PracticePage.class);
                startActivity(intent);
                finish();
            }
        });
    }

    @Override
    public void setValues() {
        super.setValues();
        parents.setBackgroundResource(R.drawable.explanation);
    }

    @Override
    public void bindVies() {
        super.bindVies();
        setContentView(R.layout.explanation);
        this.parents = (LinearLayout) findViewById(R.id.parents);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        parents = null;
    }
}
