package com.yuruiyin.designpattern.memento;

/**
 * <p>Title:使命召唤游戏当中的备忘录模式的Originator</p>
 * <p>Description: 负责创建一个备忘录，可以记录、恢复自身的内部状态 </p>
 * <p>Copyright: Copyright (c) 2018</p>
 * <P>Company: 17173</p>
 *
 * @author yuruiyin
 * @version 2018/7/31
 */
public class CallOfDuty {
    // 关卡
    private int mCheckpoint = 1;
    // 人物的生命值
    private int mLifeValue = 100;
    // 武器
    private String mWeapon = "沙漠之鹰";

    /**
     * 玩游戏
     */
    public void play() {
        System.out.println("玩游戏：" + String.format("第%d关", mCheckpoint) + " 奋战杀敌中");
        mLifeValue -= 10;
        System.out.println("进度升级啦");
        mCheckpoint++;
        System.out.println("到达 " + String.format("第%d关", mCheckpoint));
    }

    /**
     * 退出游戏
     */
    public void quit() {
        System.out.println("-----------------");
        System.out.println("退出前的游戏属性：" + this.toString());
        System.out.println("退出游戏");
        System.out.println("-----------------");
    }

    /**
     * 创建备忘录
     * @return 备忘录
     */
    public Memonto createMemonto() {
        Memonto memonto = new Memonto();
        memonto.mCheckpoint = mCheckpoint;
        memonto.mLifeValue = mLifeValue;
        memonto.mWeapon = mWeapon;
        return memonto;
    }

    /**
     * 恢复游戏
     * @param memonto 备忘录
     */
    public void restore(Memonto memonto) {
        this.mCheckpoint = memonto.mCheckpoint;
        this.mLifeValue = memonto.mLifeValue;
        this.mWeapon = memonto.mWeapon;
        System.out.println("恢复后的游戏属性： " + this.toString());
    }

    @Override
    public String toString() {
        return "CallOfDuty [mCheckpoint=" + mCheckpoint + ", mLifeValue=" + mLifeValue
                + ", mWeapon=" + mWeapon + "]";
    }
}
