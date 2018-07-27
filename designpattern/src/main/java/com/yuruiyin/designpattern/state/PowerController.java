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
public interface PowerController {

    /**
     * 开机
     */
    void powerOn();

    /**
     * 关机
     */
    void powerOff();

}
