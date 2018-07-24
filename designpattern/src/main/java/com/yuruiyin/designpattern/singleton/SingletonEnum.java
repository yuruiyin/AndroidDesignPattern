package com.yuruiyin.designpattern.singleton;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2018</p>
 * <P>Company: 17173</p>
 *
 * @author yuruiyin
 * @version 2018/7/24
 */

interface EnumSingleton {
    void doSomething();
}

public enum SingletonEnum implements EnumSingleton {

    INSTANCE {
        @Override
        public void doSomething() {
            System.out.println("枚举单例，doSomething");
        }
    };

    public static EnumSingleton getInstance() {
        return INSTANCE;
    }

}



