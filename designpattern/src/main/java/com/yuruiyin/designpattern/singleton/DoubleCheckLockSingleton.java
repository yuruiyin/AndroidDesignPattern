package com.yuruiyin.designpattern.singleton;

/**
 * <p>Title: 双重校验锁单例</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2018</p>
 * <P>Company: 17173</p>
 *
 * @author yuruiyin
 * @version 2018/7/24
 */
public class DoubleCheckLockSingleton {

    // volatile可以保证每次都从主内存去取对象实例，解决DCL失效问题
    private volatile static DoubleCheckLockSingleton mInstance;

    private DoubleCheckLockSingleton() {

    }

    /**
     * 第一个判空时为了减少不必要的同步，提高效率，因为mInstance不为null的时候可直接return那个实例。
     * 第二个判空是为了保证mInstance为null的时候才会创建实例。
     * 然而，mInstance = new DoubleCheckLockSingleton()虽然是一句代码，但是它并不是原子操作
     * 这句代码最终会被编译成多条汇编指令，它大致做了3件事情：
     * (1) 给Singleton的实例分配内存；
     * (2) 调用Singleton()的构造函数，初始化成员字段；
     * (3) 将mInstance对象指向分配的内存空间（此时mInstance就不等于null了）
     * 但是，上面第二和第三条指令的顺讯是无法保证的，因此若存在线程A和线程B，线程A已经执行了new操作之后，mInstance不为null了，
     * 此时，在第二条指令未执行的情况下，线程B直接取走了mInstance，再使用就会出错。
     * @return
     */
    public static DoubleCheckLockSingleton getInstance() {
        if (mInstance == null) {
            synchronized (DoubleCheckLockSingleton.class) {
                if (mInstance == null) {
                    mInstance = new DoubleCheckLockSingleton();
                }
            }
        }
        return mInstance;
    }

}
