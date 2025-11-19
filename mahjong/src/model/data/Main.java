package model.data;

import java.util.Scanner;

/**
 * mainメソッドを持つクラスです。
 * ここから麻雀ゲームを実行できます。
 */
public class Main {
	public static void main(String[] args) {
		System.out.println("麻雀v0.2.6");
		System.out.println("一雀頭四面子作れたらあがりだよ");
		System.out.println("タンヤオとチートイツがわかるよ");
		System.out.println("はじめる？");
		System.out.println("(press y/n)");
		firstAnnouncement();
		Table table = new Table();
		Player player = new Player();

		table.setWall(table.createShuffledWall());
		player.haipai(table.dealHand(table.getWall(), 13));

		do {
			player.tsumo(table.dealHand(table.getWall(), 1));
			table.setDiscard(player.discard());
			table.setDiscard(table.getDiscard());
			
		} while (true);
	}

	public static void firstAnnouncement() {
		boolean isOk = false;
		Scanner scn = new Scanner(System.in);
		do {
			String str = scn.nextLine();
			if (str.equals("y")) {
				System.out.println("りょうかい！");
				System.out.println("\n牌は14枚あるよ\n1~14を入力して切りたい牌を指定してね");
				break;
			} else if (str.equals("n")) {
				System.out.println("ほげほげ");
				scn.close();
				System.exit(0);
			} else {
				System.out.println("無効な文字を受け取ったよ");
				isOk = true;
			}
		} while (isOk);
	}

}
