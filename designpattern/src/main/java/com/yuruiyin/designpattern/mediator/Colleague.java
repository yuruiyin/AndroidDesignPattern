package com.yuruiyin.designpattern.mediator;

/**
 * <p>Title: 抽象同事</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2018</p>
 * <P>Company: 17173</p>
 *
 * @author yuruiyin
 * @version 2018/8/2
 */
public abstract class Colleague {
    // 每一个同事都该知道其中介者
    protected Mediator mMediator;

    public Colleague(Mediator mediator) {
        this.mMediator = mediator;
    }

}
