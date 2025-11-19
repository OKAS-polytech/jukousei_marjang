package com.example.mahjong;

import com.example.mahjong.controller.GameController;

/**
 * mainメソッドを持つクラスです。
 * ここから麻雀ゲームを実行できます。
 */
public class Main {
    public static void main(String[] args) {
        GameController gameController = new GameController();
        gameController.startGame();
    }
}
