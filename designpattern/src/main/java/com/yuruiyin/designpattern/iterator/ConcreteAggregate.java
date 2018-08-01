package com.yuruiyin.designpattern.iterator;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>Title: 具体容器类</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2018</p>
 * <P>Company: 17173</p>
 *
 * @author yuruiyin
 * @version 2018/8/1
 */
public class ConcreteAggregate<T> implements Aggregate<T> {
    private List<T> mList = new ArrayList<>();

    @Override
    public void add(T obj) {
        mList.add(obj);
    }

    @Override
    public void remove(T obj) {
        mList.remove(obj);
    }

    @Override
    public Iterator<T> iterator() {
        return new ConcreteIterator<>(mList);
    }
}
