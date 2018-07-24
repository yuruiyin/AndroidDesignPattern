package com.yuruiyin.designpattern.singleton;

/**
 * <p>Title: 懒汉单例模式</p>
 * <p>Description: 只有用到的时候才实例化</p>
 * <p>Copyright: Copyright (c) 2018</p>
 * <P>Company: 17173</p>
 *
 * @author yuruiyin
 * @version 2018/7/24
 */
public class LazySingleton {

    private static LazySingleton mInstance;

    private LazySingleton() {

    }

    public static LazySingleton getInstance() {
        if (mInstance == null) {
            mInstance = new LazySingleton();
        }
        return mInstance;
    }

}
