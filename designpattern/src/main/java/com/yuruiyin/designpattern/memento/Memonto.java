package com.yuruiyin.designpattern.memento;

/**
 * <p>Title: 备忘录类</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2018</p>
 * <P>Company: 17173</p>
 *
 * @author yuruiyin
 * @version 2018/7/31
 */
public class Memonto {
    public int mCheckpoint;
    public int mLifeValue;
    public String mWeapon;

    @Override
    public String toString() {
        return "Memonto [mCheckpoint=" + mCheckpoint + ", mLifeValue=" + mLifeValue
                + ", mWeapon=" + mWeapon + "]";
    }
}
