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
public interface TvState {

    /**
     * 下一个频道
     */
    void nextChannel();

    /**
     * 上一个频道
     */
    void preChannel();

    /**
     * 调高音量
     */
    void turnUp();

    /**
     * 调低音量
     */
    void turnDown();

}
