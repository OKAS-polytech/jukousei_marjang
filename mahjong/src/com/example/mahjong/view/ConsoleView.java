package com.example.mahjong.view;

import java.util.List;
import java.util.Scanner;
import com.example.mahjong.model.TileType;
import com.example.mahjong.model.Hands;

/**
 * CUI（コンソールユーザーインターフェース）を担当するビュークラスです。
 * ゲームの状態を表示し、ユーザーからのキーボード入力を受け付けます。
 */
public class ConsoleView {
    /** ユーザーからの入力を受け取るためのスキャナー */
    private Scanner scanner;

    /**
     * ConsoleViewの新しいインスタンスを生成します。
     */
    public ConsoleView() {
        this.scanner = new Scanner(System.in);
    }

    /**
     * ゲーム開始時のウェルカムメッセージを表示します。
     */
    public void showWelcomeMessage() {
        System.out.println("麻雀v0.3.0");
        System.out.println("一雀頭四面子作れたらあがりだよ");
        System.out.println("タンヤオとチートイツがわかるよ");
    }

    /**
     * ユーザーにゲームを開始するかどうかを尋ねます。
     * 'y'が入力されるまで、または'n'が入力されるまで入力を求め続けます。
     *
     * @return ユーザーがゲームの開始を同意した場合は`true`、拒否した場合は`false`
     */
    public boolean promptToStartGame() {
        System.out.println("はじめる？ (y/n)");
        do {
            String input = scanner.nextLine();
            if ("y".equalsIgnoreCase(input)) {
                System.out.println("りょうかい！");
                System.out.println("\n牌は14枚あるよ\n1~14を入力して切りたい牌を指定してね");
                return true;
            } else if ("n".equalsIgnoreCase(input)) {
                System.out.println("またね！");
                return false;
            } else {
                System.out.println("無効な入力です。'y' または 'n' を入力してください。");
            }
        } while (true);
    }

    /**
     * プレイヤーの手牌をコンソールに表示します。
     * 各牌には、選択しやすいように1から始まるインデックスが付与されます。
     *
     * @param hand 表示する手牌のリスト
     */
    public void displayHand(List<TileType> hand) {
        System.out.println("現在のあなたの手牌:");
        for (int i = 0; i < hand.size(); i++) {
            System.out.print((i + 1) + ":" + hand.get(i) + " ");
        }
        System.out.println();
    }

    /**
     * プレイヤーがツモした牌を表示します。
     *
     * @param tile ツモした牌
     */
    public void showDrawnTile(TileType tile) {
        System.out.println(tile + "をツモってきたよ");
    }

    /**
     * ユーザーに捨てる牌のインデックスを入力させます。
     * 有効な数値（1から手牌のサイズまで）が入力されるまで、入力を求め続けます。
     *
     * @param handSize 現在の手牌の枚数
     * @return ユーザーによって選択された、捨てる牌の0から始まるインデックス
     */
    public int getDiscardTileIndex(int handSize) {
        int tileIndex = -1;
        do {
            System.out.println("なに切る？ (1-" + handSize + "の数字を入力)");
            try {
                String line = scanner.nextLine();
                tileIndex = Integer.parseInt(line) - 1;
                if (tileIndex < 0 || tileIndex >= handSize) {
                    System.out.println("無効な数字です。手牌の範囲で入力してください。");
                    tileIndex = -1;
                }
            } catch (NumberFormatException e) {
                System.out.println("数字を入力してください。");
            }
        } while (tileIndex == -1);
        return tileIndex;
    }

    /**
     * 捨てられた牌をコンソールに表示します。
     *
     * @param tile 捨てられた牌
     */
    public void showDiscardedTile(TileType tile) {
        System.out.println(tile + "を切るよ\n");
    }

    /**
     * あがりの結果（役）をコンソールに表示します。
     *
     * @param hands 成立した役のリスト
     */
    public void showAgari(List<Hands> hands) {
        System.out.println("あがり！！");
        if (hands.isEmpty()) {
            System.out.println("役は...無し！");
        } else {
            System.out.println("役は..." + hands + "！");
        }
    }

    /**
     * テンパイ状態であることをコンソールに表示します。
     */
    public void showTenpai() {
        System.out.println("テンパイ！");
    }

    /**
     * スキャナーリソースを解放します。
     * アプリケーションの終了時に呼び出す必要があります。
     */
    public void close() {
        scanner.close();
    }
}
