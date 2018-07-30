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
public class TransformCommand implements Command {

    private TetrisMachine mMachine;

    public TransformCommand(TetrisMachine machine) {
        this.mMachine = machine;
    }

    @Override
    public void execute() {
        mMachine.transform();
    }
}
