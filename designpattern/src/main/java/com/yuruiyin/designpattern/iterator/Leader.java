package com.yuruiyin.designpattern.iterator;

/**
 * <p>Title: 责任链模式之公司报销流程</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2018</p>
 * <P>Company: 17173</p>
 *
 * @author yuruiyin
 * @version 2018/7/30
 */
public abstract class Leader {

    protected Leader nextHandler;

    public final void handleRequest(int money) {
        if (money <= limit()) {
            handle(money);
        } else {
            if (null != nextHandler) {
                nextHandler.handleRequest(money);
            }
        }
    }

    /**
     * 自身能批复的额度
     * @return 额度
     */
    public abstract int limit();

    /**
     * 处理报账行为
     * @param money 具体金额
     */
    public abstract void handle(int money);

}
