package com.yuruiyin.designpattern.visitor;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2018</p>
 * <P>Company: 17173</p>
 *
 * @author yuruiyin
 * @version 2018/8/2
 */
public interface Visitor {

    /**
     * 访问工程师
     * @param engineer 工程师
     */
    void visit(Engineer engineer);

    /**
     * 访问经理
     * @param manager 经理
     */
    void visit(Manager manager);

}
