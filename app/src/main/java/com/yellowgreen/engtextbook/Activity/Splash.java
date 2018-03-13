package com.yellowgreen.engtextbook.Activity;

import android.content.Intent;
import android.os.Handler;
import android.widget.LinearLayout;

import com.yellowgreen.engtextbook.R;


/**
 * Created by mapl0 on 2018-03-12.
 */

public class Splash extends BaseActivity {

    private LinearLayout parents;
    private Handler nextPageHandler;
    private Runnable nextPageRunnable;

    @Override
    public void setUpEvents() {
        super.setUpEvents();

        nextPageRunnable = new Runnable() {
            @Override
            public void run() {
                Intent nextPageIntent = new Intent(myContext, Tutorial.class);
                startActivity(nextPageIntent);
                finish();
            }
        };

        nextPageHandler = new Handler();
        nextPageHandler.postDelayed(nextPageRunnable, 3000);

    }

    @Override
    public void setValues() {
        super.setValues();

        parents.setBackgroundResource(R.drawable.splash);

    }

    @Override
    public void bindVies() {
        super.bindVies();

        setContentView(R.layout.splash);

        this.parents = (LinearLayout) findViewById(R.id.parents);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        if(nextPageHandler!= null) {
            nextPageHandler.removeCallbacks(nextPageRunnable);
            nextPageHandler = null;
        }
        if(nextPageRunnable != null) nextPageRunnable = null;
    }
}
