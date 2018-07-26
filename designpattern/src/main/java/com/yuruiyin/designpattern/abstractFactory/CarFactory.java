package com.yuruiyin.designpattern.abstractFactory;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2018</p>
 * <P>Company: 17173</p>
 *
 * @author yuruiyin
 * @version 2018/7/26
 */
public abstract class CarFactory {

    /**
     * 生产轮胎
     * @return 轮胎
     */
    public abstract ITire createTire();

    /**
     * 生产发动机
     * @return 发动机
     */
    public abstract IEngine createEngine();

    /**
     * 生产制动系统
     * @return 制动系统
     */
    public abstract IBrake createBrake();

}
