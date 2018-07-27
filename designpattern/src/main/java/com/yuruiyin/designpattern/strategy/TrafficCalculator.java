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
public class TrafficCalculator {

    private CalculateStrategy mStrategy;

    public void setStrategy(CalculateStrategy strategy) {
        this.mStrategy = strategy;
    }

    public int calculatePrice(int km) {
        return mStrategy.calculatePrice(km);
    }

    public static void main(String[] args) {
        TrafficCalculator trafficCalculator = new TrafficCalculator();

        // 设置计算价格策略为公交策略
        trafficCalculator.setStrategy(new BusStrategy());
        System.out.println("公交车乘16公里的价格是： " + trafficCalculator.calculatePrice(16));

        // 设置计算价格策略为地铁策略
        trafficCalculator.setStrategy(new SubwayStrategy());
        System.out.println("地铁乘16公里的价格是： " + trafficCalculator.calculatePrice(16));
    }

}
