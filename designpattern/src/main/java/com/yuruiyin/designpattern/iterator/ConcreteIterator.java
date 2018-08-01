package com.yuruiyin.designpattern.iterator;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2018</p>
 * <P>Company: 17173</p>
 *
 * @author yuruiyin
 * @version 2018/8/1
 */
public class ConcreteIterator<T> implements Iterator<T> {

    private List<T> mList = new ArrayList<T>();
    private int cursor = 0;

    public ConcreteIterator(List<T> list) {
        mList = list;
    }

    @Override
    public boolean hasNext() {
        return cursor != mList.size();
    }

    @Override
    public T next() {
        T obj = null;
        if (this.hasNext()) {
            obj = this.mList.get(cursor++);
        }
        return obj;
    }
}
