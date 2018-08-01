package com.yuruiyin.designpattern.iterator;

/**
 * <p>Title: 容器接口</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2018</p>
 * <P>Company: 17173</p>
 *
 * @author yuruiyin
 * @version 2018/8/1
 */
public interface Aggregate<T> {

    /**
     * 添加一个元素
     * @param obj 元素
     */
    void add(T obj);

    /**
     * 删除一个元素
     * @param obj 元素
     */
    void remove(T obj);

    /**
     * 获取容器的迭代器
      * @return 迭代器
     */
    Iterator<T> iterator();
}
