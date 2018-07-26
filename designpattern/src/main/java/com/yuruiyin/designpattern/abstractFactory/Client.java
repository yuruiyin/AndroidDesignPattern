package com.yuruiyin.designpattern.abstractFactory;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2018</p>
 * <P>Company: 17173</p>
 *
 * @author yuruiyin
 * @version 2018/7/26
 */
public class Client {

    public static void main(String[] args) {
        // 构造一个生产Q3的工厂
        CarFactory q3Factory = new Q3Factory();
        q3Factory.createTire().tire();
        q3Factory.createEngine().engine();
        q3Factory.createBrake().brake();

        System.out.println("------------------------------------");

        // 构造一个生产Q7的工厂
        CarFactory q7Factory = new Q7Factory();
        q7Factory.createTire().tire();
        q7Factory.createEngine().engine();
        q7Factory.createBrake().brake();
    }

}
