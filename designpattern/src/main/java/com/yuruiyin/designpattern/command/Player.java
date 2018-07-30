package com.yuruiyin.designpattern.command;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2018</p>
 * <P>Company: 17173</p>
 *
 * @author yuruiyin
 * @version 2018/7/30
 */
public class Player {

    public static void main(String[] args) {
        // 首先要有俄罗斯方块游戏，也就是接收者
        TetrisMachine machine = new TetrisMachine();

        // 根据游戏我们构造4种命令
        LeftCommand leftCommand = new LeftCommand(machine);
        RightCommand rightCommand = new RightCommand(machine);
        FastFallCommand fastFallCommand = new FastFallCommand(machine);
        TransformCommand transformCommand = new TransformCommand(machine);

        // 按钮可以执行不同的命令
        Buttons buttons = new Buttons();
        buttons.setLeftCommand(leftCommand);
        buttons.setRightCommand(rightCommand);
        buttons.setFastFallCommand(fastFallCommand);
        buttons.setTransformCommand(transformCommand);

        // 具体按下哪个按钮玩家说了算
        buttons.transform();
        buttons.fastFall();
        buttons.toLeft();
        buttons.toRight();
    }

}
