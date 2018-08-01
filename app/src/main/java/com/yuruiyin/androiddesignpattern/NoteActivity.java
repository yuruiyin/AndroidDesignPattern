package com.yuruiyin.androiddesignpattern;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.yuruiyin.androiddesignpattern.memonto.NoteEditText;

import butterknife.BindView;
import butterknife.ButterKnife;

public class NoteActivity extends AppCompatActivity {

    @BindView(R.id.editText)
    NoteEditText mEditText;
    @BindView(R.id.undo)
    TextView mTvUndo;
    @BindView(R.id.redo)
    TextView mTvRedo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note);

        ButterKnife.bind(this);
        
        registerEvents();
    }

    private void registerEvents() {
        mTvUndo.setOnClickListener(v -> {
            mEditText.undo();
        });

        mTvRedo.setOnClickListener(v -> {
            mEditText.redo();
        });
    }

}
