package com.yuruiyin.androiddesignpattern;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Button;

import com.yuruiyin.androiddesignpattern.util.DialogUtil;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.btnNextPage)
    Button mBtnNextPage;
    @BindView(R.id.btnNotePage)
    Button mBtnNotePage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        if (savedInstanceState == null) {
            DialogUtil.showAlertDialog(this);
        }
        
        registerEvents();
    }

    private void registerEvents() {
        mBtnNextPage.setOnClickListener(v -> {
            navToPage(SecondActivity.class);
        });

        mBtnNotePage.setOnClickListener(v -> {
            navToPage(NoteActivity.class);
        });
    }

    private void navToPage(Class clazz) {
        Intent intent = new Intent(this, clazz);
        startActivity(intent);
    }

    @Override
    public void onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState) {
        Log.d("MainActivity", "onSaveInstanceState -- outPersistentState");
        super.onSaveInstanceState(outState, outPersistentState);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        Log.d("MainActivity", "onSaveInstanceState");
        super.onSaveInstanceState(outState);
    }
}
