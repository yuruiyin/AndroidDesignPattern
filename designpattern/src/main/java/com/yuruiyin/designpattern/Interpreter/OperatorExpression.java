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
public abstract class OperatorExpression extends ArithmeticExpression {

    protected ArithmeticExpression exp1;
    protected ArithmeticExpression exp2;

    public OperatorExpression(ArithmeticExpression exp1, ArithmeticExpression exp2) {
        this.exp1 = exp1;
        this.exp2 = exp2;
    }

}
