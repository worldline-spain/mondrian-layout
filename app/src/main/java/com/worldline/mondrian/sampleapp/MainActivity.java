package com.worldline.mondrian.sampleapp;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ViewGroup;

import com.worldline.mondrian.MondrianLayout;

public class MainActivity extends Activity {

    private ViewGroup root;
    private MondrianLayout mondrianLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


    }

}
