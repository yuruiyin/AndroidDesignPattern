package com.yuruiyin.designpattern.Interpreter;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2018</p>
 * <P>Company: 17173</p>
 *
 * @author yuruiyin
 * @version 2018/7/30
 */
public abstract class ArithmeticExpression {

    /**
     * 抽象的解析方法
     * 具体的解析逻辑由具体的子类实现
     * @return 解析到具体的值
     */
    public abstract int interpret();

}
