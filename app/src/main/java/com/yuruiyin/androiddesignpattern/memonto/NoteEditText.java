package com.yuruiyin.androiddesignpattern.memonto;

import android.content.Context;
import android.util.AttributeSet;

import com.jakewharton.rxbinding2.widget.RxTextView;

import java.util.concurrent.TimeUnit;

import io.reactivex.android.schedulers.AndroidSchedulers;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2018</p>
 * <P>Company: 17173</p>
 *
 * @author yuruiyin
 * @version 2018/7/31
 */
public class NoteEditText extends android.support.v7.widget.AppCompatEditText {

    // 备忘录管理器
    private NoteCaretaker mNoteCaretaker = new NoteCaretaker();

    private String lastEditTextContent;

    private boolean isUndoRedo;

    public NoteEditText(Context context) {
        super(context);
        init();
    }

    public NoteEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public NoteEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        // 监听EditText的变化，然后保存到备忘录中
        RxTextView.textChangeEvents(this)
                .debounce(100, TimeUnit.MILLISECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .map(textViewTextChangeEvent -> textViewTextChangeEvent.text().toString())
                .subscribe(content -> {
                    if (content.equals(lastEditTextContent)) {
                        isUndoRedo = false;
                        return;
                    }
                    if (isUndoRedo) {
                        isUndoRedo = false;
                        return;
                    }
                    mNoteCaretaker.saveMemonto(createMemonto());
                    lastEditTextContent = content;
                }, throwable -> {
                    // do nothing
                });
    }

    /**
     * 创建备忘录对象，即存储编辑器的文本内容和光标位置
     * @return 备忘录对象
     */
    public Memonto createMemonto() {
        Memonto noteMemonto = new Memonto();
        noteMemonto.text = getText().toString();
        noteMemonto.cursor = getSelectionStart();
        return noteMemonto;
    }

    public void undo() {
        restore(mNoteCaretaker.getPrevMemonto());
    }

    public void redo() {
        restore(mNoteCaretaker.getNextMemonto());
    }

    /**
     * 从备忘录中恢复数据
     */
    private void restore(Memonto memonto) {
        isUndoRedo = true;
        setText(memonto.text);
        setSelection(memonto.cursor);
    }

}
