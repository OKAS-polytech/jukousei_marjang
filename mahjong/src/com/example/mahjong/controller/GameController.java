package com.example.mahjong.controller;

import java.util.Collections;
import java.util.List;

import com.example.mahjong.model.Player;
import com.example.mahjong.model.Table;
import com.example.mahjong.model.TileType;
import com.example.mahjong.model.Hands;
import com.example.mahjong.model.Judge;
import com.example.mahjong.view.ConsoleView;

/**
 * 麻雀ゲームの主要なロジックを制御するコントローラークラスです。
 * Model (Table, Player) と View (ConsoleView) を仲介し、ゲームの進行を管理します。
 */
public class GameController {
    /** ゲーム卓の状態を管理するモデル */
    private Table table;
    /** プレイヤーの状態を管理するモデル */
    private Player player;
    /** ユーザーインターフェースを担当するビュー */
    private ConsoleView view;

    /**
     * GameControllerの新しいインスタンスを生成します。
     * このコンストラクタは、ゲームに必要なモデルとビューのインスタンスを初期化します。
     */
    public GameController() {
        this.table = new Table();
        this.player = new Player();
        this.view = new ConsoleView();
    }

    /**
     * 麻雀ゲームを開始し、管理します。
     * ユーザーに開始を確認した後、配牌を行い、メインのゲームループを開始します。
     * ゲームが終了したら、リソースを解放します。
     */
    public void startGame() {
        view.showWelcomeMessage();
        if (!view.promptToStartGame()) {
            view.close();
            return;
        }

        table.setWall(table.createShuffledWall());
        player.haipai(table.dealHand(table.getWall(), 13));

        gameLoop();

        view.close();
    }

    /**
     * ゲームのメインループを処理します。
     * このループ内で、ツモ、打牌、あがり判定などの一連のゲームフローが実行されます。
     * 現在の実装では、一人のプレイヤーの基本的な操作をシミュレートします。
     */
    private void gameLoop() {
        boolean gameFinished = false;
        while (!gameFinished) {
            // 1. ツモ (牌を1枚引く)
            List<TileType> drawnTileList = table.dealHand(table.getWall(), 1);
            if (drawnTileList.isEmpty()) {
                System.out.println("牌山がなくなりました。流局です。");
                break;
            }
            TileType drawnTile = drawnTileList.get(0);
            player.tsumo(Collections.singletonList(drawnTile));
            view.showDrawnTile(drawnTile);

            // 2. Check for Agari or Tenpai
            List<Integer> judgeResult = Judge.judgeDuringTheGame(player.getHand());
            if (judgeResult.get(0) == 1) { // Agari
                List<Hands> winningHands = player.agari();
                view.showAgari(winningHands);
                gameFinished = true;
                continue;
            }
            if (judgeResult.get(1) == 1) { // Tenpai
                view.showTenpai();
            }

            // 3. Discard a tile
            view.displayHand(player.getHand());
            int discardIndex = view.getDiscardTileIndex(player.getHand().size());
            TileType discardedTile = player.discard(discardIndex);
            // table.setDiscard(...); // The old discard logic needs refactoring
            view.showDiscardedTile(discardedTile);

            // This is a simplified loop. In a real game, you'd handle other players' turns, calls (pon, chi, kan), etc.
            // For now, we are replicating the original single-player loop.
            // The original loop was infinite `while(true)`. We'll break for now after one turn for simplicity.
            // To make it run for more turns, you'd need a proper game end condition.
            // For this refactoring, we will exit after a few turns to avoid an infinite loop.
            if (table.getWall().size() < 80) { // Arbitrary end condition
                 // gameFinished = true;
            }
        }
    }
}
