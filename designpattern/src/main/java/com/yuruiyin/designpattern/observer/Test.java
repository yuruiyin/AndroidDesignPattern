package com.yuruiyin.designpattern.observer;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2018</p>
 * <P>Company: 17173</p>
 *
 * @author yuruiyin
 * @version 2018/7/31
 */
public class Test {

    public static void main(String[] args) {
        // 被观察者
        DevTechFrontier devTechFrontier = new DevTechFrontier();
        // 若干观察者
        Coder coder1 = new Coder("coder1");
        Coder coder2 = new Coder("coder2");
        Coder coder3 = new Coder("coder3");

        // 将观察者注册到被观察者的观察者列表中
        devTechFrontier.addObservers(coder1, coder2, coder3);

        // 发布消息
        devTechFrontier.postNewPublication("新的一期开发技术前线周报发布啦！");
    }

}
