package com.yuruiyin.designpattern.mediator;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2018</p>
 * <P>Company: 17173</p>
 *
 * @author yuruiyin
 * @version 2018/8/2
 */
public class Client {

    public static void main(String[] args) {
        // 主板
        MainBoard mediator = new MainBoard();

        // 各个零件
        CDDevice cdDevice = new CDDevice(mediator);
        CPU cpu = new CPU(mediator);
        GraphicsCard graphicsCard = new GraphicsCard(mediator);
        SoundCard soundCard = new SoundCard(mediator);

        // 将各个零件部件安装到主板
        mediator.setCDDevice(cdDevice);
        mediator.setCPU(cpu);
        mediator.setGraphicsCard(graphicsCard);
        mediator.setSoundCard(soundCard);

        cdDevice.load();
    }

}
