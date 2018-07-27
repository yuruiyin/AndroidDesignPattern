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
public class BusStrategy implements CalculateStrategy {

    /**
     * 北京公交车，10公里以内1块钱，后面每5公里加1块钱
      * @param km 公里数
     * @return 公交价格
     */
    @Override
    public int calculatePrice(int km) {
        if (km <= 0) {
            return 0;
        }
        if (km <= 10) {
            return 1;
        }

        return 1 + (km - 6) / 5;
    }
}
