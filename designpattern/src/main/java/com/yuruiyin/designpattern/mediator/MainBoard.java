package com.yuruiyin.designpattern.mediator;

/**
 * <p>Title: 主板 具体中介者</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2018</p>
 * <P>Company: 17173</p>
 *
 * @author yuruiyin
 * @version 2018/8/2
 */
public class MainBoard extends Mediator {
    // 光驱同事
    private CDDevice mCDDevice;
    // CPU同事
    private CPU mCPU;
    // 显卡同事
    private GraphicsCard mGraphicsCard;
    // 声卡同事
    private SoundCard mSoundCard;

    @Override
    public void changed(Colleague colleague) {
        if (colleague == mCDDevice) {
            handleCD((CDDevice) colleague);
        } else if (colleague == mCPU) {
            handleCPU((CPU) colleague);
        }
    }

    private void handleCD(CDDevice cdDevice) {
        mCPU.decodeData(cdDevice.getData());
    }

    private void handleCPU(CPU cpu) {
        mSoundCard.soundPlay(cpu.getDataSound());
        mGraphicsCard.videoPlay(cpu.getDataVideo());
    }

    public void setCDDevice(CDDevice cdDevice) {
        mCDDevice = cdDevice;
    }

    public void setCPU(CPU cpu) {
        mCPU = cpu;
    }

    public void setGraphicsCard(GraphicsCard graphicsCard) {
        mGraphicsCard = graphicsCard;
    }

    public void setSoundCard(SoundCard soundCard) {
        mSoundCard = soundCard;
    }
}
