package com.yuruiyin.designpattern.memento;

/**
 * <p>Title: 管理备忘录</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2018</p>
 * <P>Company: 17173</p>
 *
 * @author yuruiyin
 * @version 2018/7/31
 */
public class Caretaker {
    // 备忘录
    private Memonto mMemonto;

    public void archive(Memonto memonto) {
        this.mMemonto = memonto;
    }

    public Memonto getMemonto() {
        return mMemonto;
    }
}
