package com.yuruiyin.designpattern.factory;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2018</p>
 * <P>Company: 17173</p>
 *
 * @author yuruiyin
 * @version 2018/7/26
 */
public class AudiQ7 extends AudiCar {
    @Override
    public void drive() {
        System.out.println("Q7 启动啦！");
    }

    @Override
    public void selfNavigation() {
        System.out.println("Q7 开始自动巡航啦！");
    }
}
