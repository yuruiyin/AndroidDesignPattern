package com.yuruiyin.designpattern.singleton;

/**
 * <p>Title: 饿汉单例模式</p>
 * <p>Description: 在声明静态对象的时候就初始化了实例</p>
 * <p>Copyright: Copyright (c) 2018</p>
 * <P>Company: 17173</p>
 *
 * @author yuruiyin
 * @version 2018/7/24
 */
public class HungrySingleton {

    private static final HungrySingleton mInstance = new HungrySingleton();

    private HungrySingleton() {

    }

    public static HungrySingleton getInstance() {
        return mInstance;
    }

}
