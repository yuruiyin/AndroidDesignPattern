package com.yuruiyin.designpattern.mediator;

/**
 * <p>Title: CPU同事</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2018</p>
 * <P>Company: 17173</p>
 *
 * @author yuruiyin
 * @version 2018/8/2
 */
public class CPU extends Colleague {
    private String dataVideo; // 视频数据
    private String dataSound; // 音频数据

    public CPU(Mediator mediator) {
        super(mediator);
    }

    public String getDataVideo() {
        return dataVideo;
    }

    public String getDataSound() {
        return dataSound;
    }

    public void decodeData(String data) {
        // 分割音、视频数据
        String[] tmp = data.split(",");

        // 解析音视频数据
        dataVideo = tmp[0];
        dataSound = tmp[1];

        // 告诉中介者自身状态改变
        mMediator.changed(this);
    }

}
