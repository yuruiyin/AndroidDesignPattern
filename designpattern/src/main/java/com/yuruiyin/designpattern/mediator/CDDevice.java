package com.yuruiyin.designpattern.mediator;

/**
 * <p>Title: 光驱同事</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2018</p>
 * <P>Company: 17173</p>
 *
 * @author yuruiyin
 * @version 2018/8/2
 */
public class CDDevice extends Colleague {

    private String data; // 音视频数据

    public CDDevice(Mediator mediator) {
        super(mediator);
    }

    /**
     * 读取音视频数据
     * @return 音视频数据
     */
    public String getData() {
        return data;
    }

    /**
     * 加载音视频数据
     */
    public void load() {
        data = "视频数据,音频数据";

        mMediator.changed(this);
    }

}
