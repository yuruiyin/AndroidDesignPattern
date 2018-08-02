package com.yuruiyin.designpattern.visitor;

import java.util.Random;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2018</p>
 * <P>Company: 17173</p>
 *
 * @author yuruiyin
 * @version 2018/8/2
 */
public class Manager extends Staff {
    private int productCount;
    public Manager(String name) {
        super(name);
        productCount = new Random().nextInt(10);
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    /**
     * 获取一年内做的产品数量
     * @return 产品数量
     */
    public int getProductCount() {
        return productCount;
    }

}
