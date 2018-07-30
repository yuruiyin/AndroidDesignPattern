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
public class LeftCommand implements Command {

    private TetrisMachine mMachine;

    public LeftCommand(TetrisMachine machine) {
        this.mMachine = machine;
    }

    @Override
    public void execute() {
        mMachine.toLeft();
    }
}
