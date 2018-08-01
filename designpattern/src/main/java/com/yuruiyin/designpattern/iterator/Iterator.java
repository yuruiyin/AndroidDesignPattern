package com.yuruiyin.designpattern.iterator;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2018</p>
 * <P>Company: 17173</p>
 *
 * @author yuruiyin
 * @version 2018/8/1
 */
public interface Iterator<T> {

    /**
     * 是否还有下一个元素
     * @return true表示有，false表示没有
     */
    boolean hasNext();

    /**
     * 返回当前位置的元素并将位置后移
     * @return 当前位置的元素
     */
    T next();

}
