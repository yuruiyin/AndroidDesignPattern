package com.yuruiyin.designpattern.strategy;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2018</p>
 * <P>Company: 17173</p>
 *
 * @author yuruiyin
 * @version 2018/7/27
 */
public interface CalculateStrategy {

    /**
     * 按距离计算价格
     * @param km 公里数
     * @return 返回价格
     */
    int calculatePrice(int km);

}
