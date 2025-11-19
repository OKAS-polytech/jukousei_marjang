package com.example.mahjong.view;

import java.util.List;
import java.util.Scanner;
import com.example.mahjong.model.TileType;
import com.example.mahjong.model.Hands;

public class ConsoleView {
    private Scanner scanner;

    public ConsoleView() {
        this.scanner = new Scanner(System.in);
    }

    public void showWelcomeMessage() {
        System.out.println("麻雀v0.3.0");
        System.out.println("一雀頭四面子作れたらあがりだよ");
        System.out.println("タンヤオとチートイツがわかるよ");
    }

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

    public void displayHand(List<TileType> hand) {
        System.out.println("現在のあなたの手牌:");
        for (int i = 0; i < hand.size(); i++) {
            System.out.print((i + 1) + ":" + hand.get(i) + " ");
        }
        System.out.println();
    }

    public void showDrawnTile(TileType tile) {
        System.out.println(tile + "をツモってきたよ");
    }

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

    public void showDiscardedTile(TileType tile) {
        System.out.println(tile + "を切るよ\n");
    }

    public void showAgari(List<Hands> hands) {
        System.out.println("あがり！！");
        if (hands.isEmpty()) {
            System.out.println("役は...無し！");
        } else {
            System.out.println("役は..." + hands + "！");
        }
    }

    public void showTenpai() {
        System.out.println("テンパイ！");
    }

    public void close() {
        scanner.close();
    }
}
