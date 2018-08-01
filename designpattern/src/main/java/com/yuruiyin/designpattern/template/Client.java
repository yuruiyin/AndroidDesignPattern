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
public class Client {

    public static void main(String[] args) {
        AbstractComputer computer = new CoderComputer();
        computer.startUp();

        computer = new MilitaryComputer();
        computer.startUp();
    }

}
