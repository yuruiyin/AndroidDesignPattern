package com.yuruiyin.designpattern.command;

/**
 * <p>Title: 俄罗斯方块：命令模式中的接收者</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2018</p>
 * <P>Company: 17173</p>
 *
 * @author yuruiyin
 * @version 2018/7/30
 */
public class TetrisMachine {

    public void toLeft() {
        System.out.println("向左");
    }

    public void toRight() {
        System.out.println("向右");
    }

    public void fastToBottom() {
        System.out.println("快速落下");
    }

    public void transform() {
        System.out.println("改变形状");
    }

}
