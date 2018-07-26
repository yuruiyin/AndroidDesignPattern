package com.yuruiyin.designpattern.factory;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2018</p>
 * <P>Company: 17173</p>
 *
 * @author yuruiyin
 * @version 2018/7/26
 */
public abstract class AudiFactory {

    public abstract <T extends AudiCar> T createAudiCar(Class<T> clazz);

}
