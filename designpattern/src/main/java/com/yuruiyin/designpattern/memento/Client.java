package com.yuruiyin.designpattern.memento;

/**
 * <p>Title: 客户端</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2018</p>
 * <P>Company: 17173</p>
 *
 * @author yuruiyin
 * @version 2018/7/31
 */
public class Client {

    public static void main(String[] args) {
        // 创建游戏对象
        CallOfDuty game = new CallOfDuty();
        // 1. 打游戏
        game.play();

        Caretaker caretaker = new Caretaker();
        // 2. 游戏存档
        caretaker.archive(game.createMemonto());
        // 3. 退出游戏
        game.quit();
        // 4. 恢复游戏
        CallOfDuty newGame = new CallOfDuty();
        newGame.restore(caretaker.getMemonto());
    }

}
