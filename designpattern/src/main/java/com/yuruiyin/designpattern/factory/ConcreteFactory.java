package com.yuruiyin.designpattern.factory;

/**
 * <p>Title: 具体工厂类</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2018</p>
 * <P>Company: 17173</p>
 *
 * @author yuruiyin
 * @version 2018/7/26
 */
public class ConcreteFactory extends AudiFactory {
    @Override
    public <T extends AudiCar> T createAudiCar(Class<T> clazz) {
        AudiCar car = null;
        try {
            car = (AudiCar) Class.forName(clazz.getName()).newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return (T) car;
    }
}
