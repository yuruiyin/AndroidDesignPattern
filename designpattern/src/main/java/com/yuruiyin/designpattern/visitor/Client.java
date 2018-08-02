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
public class Client {

    public static void main(String[] args) {
        // 构建报表
        BusinessReport report = new BusinessReport();
        System.out.println("============ 给CEO看的报表 ===========");
        report.showReport(new CEOVisitor());
        System.out.println("============ 给CTO看的报表 ===========");
        report.showReport(new CTOVisitor());
    }

}
