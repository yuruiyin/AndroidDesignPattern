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
public class CoderComputer extends AbstractComputer {
    @Override
    protected void login() {
        System.out.println("程序员只需要进行用户名和密码验证就可以登录了");
    }
}
