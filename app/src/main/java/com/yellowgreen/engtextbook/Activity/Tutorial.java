package com.yellowgreen.engtextbook.Activity;

import android.content.Intent;
import android.view.View;
import android.widget.LinearLayout;

import com.yellowgreen.engtextbook.R;

public class Tutorial extends BaseActivity {

    private LinearLayout parents;

    @Override
    public void setUpEvents() {
        super.setUpEvents();

        parents.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(myContext, Contents.class);
                startActivity(intent);
                finish();
            }
        });
    }

    @Override
    public void setValues() {
        super.setValues();

        parents.setBackgroundResource(R.drawable.tutorial);
    }

    @Override
    public void bindVies() {
        super.bindVies();
        setContentView(R.layout.tutorial);
        this.parents = (LinearLayout) findViewById(R.id.parents);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        parents = null;
    }
}
