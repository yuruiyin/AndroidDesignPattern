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
public class Client {

    public static void main(String[] args) {
        TvController tvController = new TvController();
        // 设置开机状态
        tvController.powerOn();
        // 下一频道
        tvController.nextChannel();
        // 上一频道
        tvController.preChannel();
        // 调高音量
        tvController.turnUp();
        // 调低音量
        tvController.turnDown();
        // 再次开机
        tvController.powerOn();
        // 关机
        tvController.powerOff();
        // 关机状态下调高音量
        tvController.turnUp();
        // 再次关机
        tvController.powerOff();
    }

}
