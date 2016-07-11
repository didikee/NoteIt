package com.inno.noteit.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public abstract class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        int layoutID = setContentView();
        setContentView(layoutID);
        init();
    }
    protected abstract int setContentView();
    protected abstract void init();

}
