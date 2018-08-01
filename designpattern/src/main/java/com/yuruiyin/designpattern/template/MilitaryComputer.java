package com.yuruiyin.designpattern.template;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2018</p>
 * <P>Company: 17173</p>
 *
 * @author yuruiyin
 * @version 2018/8/1
 */
public class MilitaryComputer extends AbstractComputer {

    @Override
    protected void checkHardware() {
        super.checkHardware();
        System.out.println("检查硬件防火墙");
    }

    @Override
    protected void login() {
        System.out.println("进行指纹识别等复杂的登录验证");
    }
}
