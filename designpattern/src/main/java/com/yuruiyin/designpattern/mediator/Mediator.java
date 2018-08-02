package com.yuruiyin.designpattern.mediator;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2018</p>
 * <P>Company: 17173</p>
 *
 * @author yuruiyin
 * @version 2018/8/2
 */
public abstract class Mediator {

    /**
     * 同事对象发生改变时通知中介者的方法
     * 在同事对象改变时由中介者去通知其他的同事对象
     *
     * @param colleague
     */
    public abstract void changed(Colleague colleague);

}
