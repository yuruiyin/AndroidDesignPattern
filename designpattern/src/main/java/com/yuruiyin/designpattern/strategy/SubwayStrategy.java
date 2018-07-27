package com.yuruiyin.designpattern.strategy;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2018</p>
 * <P>Company: 17173</p>
 *
 * @author yuruiyin
 * @version 2018/7/27
 */
public class SubwayStrategy implements CalculateStrategy {

    /**
     * 北京地铁，6公里（含）内3元；6~12公里（含）4元；12~22公里（含）5元；22~32公里（含）6元。
     * @param km 公里数
     * @return 地铁价格
     */
    @Override
    public int calculatePrice(int km) {
        if (km <= 6) {
            return 3;
        }
        if (km <= 12) {
            return 4;
        }
        if (km <= 22) {
            return 5;
        }
        if (km <= 32) {
            return 6;
        }
        return 7;
    }
}
