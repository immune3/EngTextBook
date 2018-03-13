package com.yellowgreen.engtextbook.Activity;

import android.content.Intent;
import android.view.View;
import android.widget.LinearLayout;

import com.yellowgreen.engtextbook.R;

/**
 * Created by mapl0 on 2018-03-12.
 */

public class Contents extends BaseActivity {

    private LinearLayout parents;

    @Override
    public void setUpEvents() {
        super.setUpEvents();

        /*
        테스트
         */
        parents.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(myContext, Classification.class);
                startActivity(intent);
            }
        });

    }

    @Override
    public void setValues() {
        super.setValues();
        parents.setBackgroundResource(R.drawable.contents);

    }

    @Override
    public void bindVies() {
        super.bindVies();
        setContentView(R.layout.contents);
        this.parents = (LinearLayout) findViewById(R.id.parents);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        parents = null;
    }
}
