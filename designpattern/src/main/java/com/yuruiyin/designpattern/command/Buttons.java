package com.yuruiyin.designpattern.command;

/**
 * <p>Title: 命令模式中的请求者类</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2018</p>
 * <P>Company: 17173</p>
 *
 * @author yuruiyin
 * @version 2018/7/30
 */
public class Buttons {

    private LeftCommand mLeftCommand;
    private RightCommand mRightCommand;
    private FastFallCommand mFastFallCommand;
    private TransformCommand mTransformCommand;

    public void setLeftCommand(LeftCommand leftCommand) {
        this.mLeftCommand = leftCommand;
    }

    public void setRightCommand(RightCommand rightCommand) {
        this.mRightCommand = rightCommand;
    }

    public void setFastFallCommand(FastFallCommand fastFallCommand) {
        this.mFastFallCommand = fastFallCommand;
    }

    public void setTransformCommand(TransformCommand transformCommand) {
        this.mTransformCommand = transformCommand;
    }

    public void toLeft() {
        mLeftCommand.execute();
    }

    public void toRight() {
        mRightCommand.execute();
    }

    public void fastFall() {
        mFastFallCommand.execute();
    }

    public void transform() {
        mTransformCommand.execute();
    }

}
