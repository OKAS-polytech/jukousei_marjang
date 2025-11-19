package com.example.mahjong;

import com.example.mahjong.controller.GameController;

/**
 * 麻雀アプリケーションのエントリーポイントです。
 * このクラスの`main`メソッドからゲームが開始されます。
 */
public class Main {
    /**
     * アプリケーションのメインメソッドです。
     * {@link GameController}のインスタンスを生成し、ゲームを開始します。
     *
     * @param args コマンドライン引数（未使用）
     */
    public static void main(String[] args) {
        GameController gameController = new GameController();
        gameController.startGame();
    }
}
