package com.yuruiyin.designpattern.observer;

import java.util.Observable;
import java.util.Observer;

/**
 * <p>Title: 观察者：程序员</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2018</p>
 * <P>Company: 17173</p>
 *
 * @author yuruiyin
 * @version 2018/7/31
 */
public class Coder implements Observer {

    private String name;

    public Coder(String name) {
        this.name = name;
    }

    @Override
    public void update(Observable observable, Object arg) {
        System.out.println("Hi, " + name + ", 开发技术前线更新啦， 内容：" + arg);
    }
}
