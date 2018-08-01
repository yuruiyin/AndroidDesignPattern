package com.yuruiyin.androiddesignpattern.memonto;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>Title: 管理备忘录的类</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2018</p>
 * <P>Company: 17173</p>
 *
 * @author yuruiyin
 * @version 2018/7/31
 */
public class NoteCaretaker {
    // 最大存储记录数量
    private static final int MAX = 30;
    // 存储30条记录
    private List<Memonto> mMemontoList = new ArrayList<>(MAX);

    // 当前第几条记录
    private int mIndex = 0;

    /**
     * 保存记录
     * @param memonto 要保存的记录
     */
    public void saveMemonto(Memonto memonto) {
        if (mMemontoList.size() >= MAX) {
            mMemontoList.remove(0);
        }
        mMemontoList.add(memonto);
        mIndex = mMemontoList.size() - 1;
    }

    /**
     * 获取上一个存档信息，相当于撤销功能
     * @return
     */
    public Memonto getPrevMemonto() {
        if (mIndex > 0) {
            --mIndex;
        }
        return mMemontoList.get(mIndex);
    }

    /**
     * 获取下一个存档信息，相当于重做功能
     * @return
     */
    public Memonto getNextMemonto() {
        if (mIndex < mMemontoList.size() - 1) {
            ++mIndex;
        }
        return mMemontoList.get(mIndex);
    }

}
