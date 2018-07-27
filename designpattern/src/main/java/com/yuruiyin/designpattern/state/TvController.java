package com.yuruiyin.designpattern.state;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2018</p>
 * <P>Company: 17173</p>
 *
 * @author yuruiyin
 * @version 2018/7/27
 */
public class TvController implements PowerController {

    private TvState mTvState;

    public void setTvState(TvState tvState) {
        this.mTvState = tvState;
    }

    @Override
    public void powerOn() {
        if (mTvState != null && mTvState instanceof PowerOnState) {
            System.out.println("已处于开机状态");
            return;
        }
        setTvState(new PowerOnState());
        System.out.println("开机啦！");
    }

    @Override
    public void powerOff() {
        if (mTvState != null && mTvState instanceof PowerOffState) {
            System.out.println("已处于关机状态");
            return;
        }
        setTvState(new PowerOffState());
        System.out.println("关机啦!");
    }

    public void nextChannel() {
        mTvState.nextChannel();
    }

    public void preChannel() {
        mTvState.preChannel();
    }

    public void turnUp() {
        mTvState.turnUp();
    }

    public void turnDown() {
        mTvState.turnDown();
    }

}
