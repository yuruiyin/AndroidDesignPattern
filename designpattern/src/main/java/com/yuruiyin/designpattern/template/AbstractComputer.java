package com.yuruiyin.designpattern.template;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2018</p>
 * <P>Company: 17173</p>
 *
 * @author yuruiyin
 * @version 2018/8/1
 */
public abstract class AbstractComputer {

    protected void powerOn() {
        System.out.println("开启电源");
    }

    protected void checkHardware() {
        System.out.println("硬件检查");
    }

    protected void loadOS() {
        System.out.println("载入操作系统");
    }

    protected void login() {
        System.out.println("小白的计算机无验证，直接进入系统");
    }

    /**
     * 启动计算机
     */
    public final void startUp() {
        System.out.println("---------- 开机 START -----------");
        powerOn();
        checkHardware();
        loadOS();
        login();
        System.out.println("---------- 开机 END -----------");
    }

}
