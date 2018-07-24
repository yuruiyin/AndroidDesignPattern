package com.yuruiyin.designpattern.singleton;

public class SingletonTest {

    public static void main(String[] args) {

        // 非单例
        NotSingleton notSingleton1 = new NotSingleton();
        System.out.println("非单例--notSingleton1：" + notSingleton1.toString());
        NotSingleton notSingleton2 = new NotSingleton();
        System.out.println("非单例--notSingleton2：" + notSingleton2.toString());

        // 饿汉单例
        HungrySingleton hungrySingleton1 = HungrySingleton.getInstance();
        System.out.println("饿汉单例--hungrySingleton1：" + hungrySingleton1.toString());
        HungrySingleton hungrySingleton2 = HungrySingleton.getInstance();
        System.out.println("饿汉单例--hungrySingleton2：" + hungrySingleton2.toString());

        LazySingleton lazySingleton1 = LazySingleton.getInstance();
        System.out.println("懒汉单例--lazySingleton1：" + lazySingleton1.toString());
        LazySingleton lazySingleton2 = LazySingleton.getInstance();
        System.out.println("懒汉单例--lazySingleton2：" + lazySingleton2.toString());

        DoubleCheckLockSingleton doubleCheckLockSingleton1 = DoubleCheckLockSingleton.getInstance();
        System.out.println("Double check lock单例--doubleCheckLockSingleton1：" + doubleCheckLockSingleton1.toString());
        DoubleCheckLockSingleton doubleCheckLockSingleton2 = DoubleCheckLockSingleton.getInstance();
        System.out.println("Double check lock单例--doubleCheckLockSingleton2：" + doubleCheckLockSingleton2.toString());

        StaticInnerClassSingleton staticInnerClassSingleton1 = StaticInnerClassSingleton.getInstance();
        System.out.println("静态内部类单例--staticInnerClassSingleton1：" + staticInnerClassSingleton1.toString());
        StaticInnerClassSingleton staticInnerClassSingleton2 = StaticInnerClassSingleton.getInstance();
        System.out.println("静态内部类单例--staticInnerClassSingleton2：" + staticInnerClassSingleton2.toString());

        EnumSingleton enumSingleton1 = SingletonEnum.getInstance();
        System.out.println("枚举单例--enumSingleton1：" + enumSingleton1.toString());
        EnumSingleton enumSingleton2 = SingletonEnum.getInstance();
        System.out.println("枚举单例--enumSingleton2：" + enumSingleton2.toString());


    }

}
