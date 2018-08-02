package com.yuruiyin.designpattern.visitor;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2018</p>
 * <P>Company: 17173</p>
 *
 * @author yuruiyin
 * @version 2018/8/2
 */
public class BusinessReport {

    private List<Staff> mStaffs = new ArrayList<>();

    public BusinessReport() {
        mStaffs.add(new Manager("王经理"));
        mStaffs.add(new Engineer("工程师-A"));
        mStaffs.add(new Engineer("工程师-B"));
        mStaffs.add(new Engineer("工程师-C"));
    }

    public void showReport(Visitor visitor) {
        for (Staff staff : mStaffs) {
            staff.accept(visitor);
        }
    }

}
