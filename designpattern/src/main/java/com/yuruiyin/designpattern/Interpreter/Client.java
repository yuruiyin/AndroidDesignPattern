package com.yuruiyin.designpattern.Interpreter;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2018</p>
 * <P>Company: 17173</p>
 *
 * @author yuruiyin
 * @version 2018/7/30
 */
public class Client {

    public static void main(String[] args) {
        Calculator c = new Calculator("12 + 14 - 13 + 10 - 2");
        System.out.println(c.calculate());
    }

}
