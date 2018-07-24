package com.yuruiyin.designpattern.singleton;

/**
 * <p>Title: 静态内部类单例模式</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2018</p>
 * <P>Company: 17173</p>
 *
 * @author yuruiyin
 * @version 2018/7/24
 */
public class StaticInnerClassSingleton {

    private static class StaticInnerClassSingletonHolder {
        private static final StaticInnerClassSingleton mInstance = new StaticInnerClassSingleton();
    }

    private StaticInnerClassSingleton() {

    }

    public static StaticInnerClassSingleton getInstance() {
        return StaticInnerClassSingletonHolder.mInstance;
    }

}
