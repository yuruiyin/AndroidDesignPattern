package com.yuruiyin.designpattern.observer;

import java.util.Observable;
import java.util.Observer;

/**
 * <p>Title: 被观察者：开发技术前线</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2018</p>
 * <P>Company: 17173</p>
 *
 * @author yuruiyin
 * @version 2018/7/31
 */
public class DevTechFrontier extends Observable {

    public void postNewPublication(String content) {
        // 标识状态或内容发生改变
        setChanged();
        // 通知所有观察者
        notifyObservers(content);
    }

    /**
     * 批量添加观察者
     * @param observers 观察者列表
     */
    public void addObservers(Observer ...observers) {
        for (Observer observer : observers) {
            addObserver(observer);
        }
    }

}
