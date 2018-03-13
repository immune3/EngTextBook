package com.yellowgreen.engtextbook.Activity;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

/**
 * Created by mapl0 on 2018-03-12.
 */

public class BaseActivity extends AppCompatActivity {

    public static Context myContext;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        myContext = this;
        Log.d("Density ", myContext.getResources().getDisplayMetrics().density + "");
        bindVies();
        setValues();
        setUpEvents();

    }

    public void setUpEvents() {

    }

    public void setValues() {

    }

    public void bindVies() {

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

    }

}
