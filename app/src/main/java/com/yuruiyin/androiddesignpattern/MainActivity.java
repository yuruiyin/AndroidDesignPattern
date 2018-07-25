package com.yuruiyin.androiddesignpattern;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.yuruiyin.androiddesignpattern.util.DialogUtil;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DialogUtil.showAlertDialog(this);
    }
}
