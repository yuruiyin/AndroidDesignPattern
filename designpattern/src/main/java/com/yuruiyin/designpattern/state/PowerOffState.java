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
public class PowerOffState implements TvState {
    @Override
    public void nextChannel() {
        // nothing to do
    }

    @Override
    public void preChannel() {
        // nothing to do
    }

    @Override
    public void turnUp() {
        // nothing to do
    }

    @Override
    public void turnDown() {
        // nothing to do
    }
}
