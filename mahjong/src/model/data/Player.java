package model.data;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 * 麻雀の面子の一人、プレイヤーを定義するクラスです。
 * 面子クラスを継承、オブザーバーインターフェースをインプリメントします。
 */
public class Player extends Mentsu implements Observer {
	private static final int id = 0; // プレイヤーid。プレイ人数増やすならstatic finalをなくす
	private int wind; //自風(東,1 南,2 西,3 北,4)
	private List<TileType> hand;// 手牌
	private boolean riichi; // リーチ
	private boolean menzen; // メンゼン
	private boolean call; // 副露の有無
	private int[] openedTiles; // 副露または暗槓した手牌コードを格納
	private boolean kan; // カンの有無
	private int point; // 点棒
	private List<Integer> agari;// あがり情報が入ったリスト

	// コンストラクタ
	public Player() {
		super();
		this.hand = new ArrayList<TileType>();
	}

	// メソッド

	public void haipai(List<TileType> haipai) {
		if (this.hand.isEmpty()) {
			this.hand = haipai;
		} else {
			System.out.println("すでに牌は配られてるよ");
		}
	}

	public void agari() {
		System.out.println("あがり！！");
		List<Hands> hands = Judge.judgeHand(this.hand);
		hands.removeIf(tile -> tile.getId() == 99);
		if(hands.isEmpty()) {
			System.out.println("役は...無し！");
		}else {
			System.out.println("役は..."+Judge.judgeHand(this.hand)+"！");
		}
		
		System.exit(0);
		
		
	}

	public void sortHand(List<TileType> hand) {
		/**
		 * 手牌を萬子、筒子、索子、風牌、三元牌の順でソートする
		 * 
		 * @param ソート前の手牌リスト
		 * @return ソート後の手牌リスト
		 */
		Collections.sort(hand);
	}

	@Override
	public void update(SubjectTable table) {
		canCall(table);

	}

	/**
	 * 副露できるかを判断するメソッドです。
	 * 
	 * @param table
	 */
	public void canCall(SubjectTable table) {
		if (!table.getDiscard().containsKey((Integer)this.wind)) {
			Map<Integer, List<TileType>> callingTile = table.getDiscard();
			List<Boolean> canCalls = Judge.canCall(this.hand, callingTile, this.wind);
			if (canCalls.get(0)) {
				System.out.print(callingTile + "ポン？");
			}
			if (canCalls.get(1)) {
				System.out.print(callingTile + "チー？");
			}
			if (canCalls.get(2)) {
				System.out.print(callingTile + "カン？");
			}
			if (canCalls.get(3)) {
				System.out.print(callingTile + "ロン？");
			}
			if (canCalls.get(0) || canCalls.get(1) || canCalls.get(2) || canCalls.get(3)) {
				char callSelect = callSelect();
				switch (callSelect) {
					case 'p':
						pon(table.getDiscardList());
						break;
					case 'c':
						chii(table.getDiscardList());
						break;
					case 'k':
						kan(table.getDiscardList());
						break;
					case 'r':
						agari();
						break;
					case 'n':
						System.out.println("スルーしたよ");
						break;
				}
			}
		}
	}

	public char callSelect() {
		System.out.println("(p:ポン c:チー k:カン r:ロン n:しない)");
		char callSelect = 'n';
		do {
			try {
				Scanner scn = new Scanner(System.in);
				String str = scn.nextLine();
				callSelect = str.charAt(0);
				if (callSelect != 'p' && callSelect != 'c' && callSelect != 'k' && callSelect != 'r'
						&& callSelect != 'n') {
					System.out.println("無効な文字だよ");
					continue;
				} else {
					break;
				}
			} catch (InputMismatchException a) {
				System.out.println("無効な入力だよ");
			}
		} while (true);
		return callSelect;
	}

	@Override
	public void tsumo(List<TileType> tsumo) {
		sortHand(this.hand);
		this.hand.addAll(tsumo);
		System.out.println(tsumo + "をツモってきたよ");
		this.agari = Judge.judgeDuringTheGame(this.hand);
		System.out.println(this.hand);
		if (this.agari.get(0) == 1) {
			agari();
		}
		if (this.agari.get(1) == 1) {
			System.out.println("テンパイ！");
		}
	}

	/**
	 * 打牌するメソッドです。
	 * 
	 * @return 打牌マップ
	 */
	public Map<Integer, List<TileType>> discard() {
		Map<Integer, List<TileType>> discard = new HashMap<>();
		discard.put(this.wind, selectDiscard());
		return discard;
	}

	@Override
	/**
	 * 打牌選択をするメソッドです。
	 * 
	 * @return 打牌
	 */
	public List<TileType> selectDiscard() {
		if (this.hand.size() <= 0 || this.hand.size() >= 15) {
			throw new IllegalArgumentException("枚数おかしくない？");
		}
		System.out.println("なに切る？");
		int nanikiru = 13;

		do {
			try {
				Scanner scn = new Scanner(System.in);
				int i = scn.nextInt() - 1;
				if (i < 0 || i >= 14) {
					System.out.println("無効な数字だよ");
					continue;
				} else {
					nanikiru = i;
					break;
				}
			} catch (InputMismatchException a) {
				System.out.println("無効な入力だよ");
			}
		} while (true);
		System.out.println(this.hand.get(nanikiru) + "を切るよ\n");

		// 牌山の先頭から指定枚数のリストをサブリストとして取得
		List<TileType> discardList = new ArrayList<>(this.hand.subList(nanikiru, nanikiru + 1));

		// 牌山から取り出した牌を削除
		// 注意: subListのclear()は元のリストからも要素を削除します
		this.hand.subList(nanikiru, nanikiru + 1).clear();

		return discardList;

	}

	@Override
	public int hashCode() {
		return this.id;
	}

	@Override
	public boolean equals(Object o) {
		if (o == null) {
			return false;
		}
		if (o instanceof Player == false) {
			return false;
		}
		if (this.id == ((Player) o).id) {
			return true;
		}
		return false;
	}

	@Override
	public void call(TileType discard) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Unimplemented method 'call'");
	}

	@Override
	/**ポンをするメソッドです。
	 * 
	 */
	public void pon(List<TileType> discard) {
		this.hand.addAll(discard);
	}

	@Override
	public void chii(List<TileType> discard) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Unimplemented method 'chii'");
	}

	@Override
	public void kan(List<TileType> discard) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Unimplemented method 'kan'");
	}

	public List<TileType> getHand() {
		return hand;
	}

	public void setHand(List<TileType> hand) {
		this.hand = hand;
	}

	public boolean isRiichi() {
		return riichi;
	}

	public void setRiichi(boolean riichi) {
		this.riichi = riichi;
	}

	public boolean isMenzen() {
		return menzen;
	}

	public void setMenzen(boolean menzen) {
		this.menzen = menzen;
	}

	public boolean isCall() {
		return call;
	}

	public void setCall(boolean call) {
		this.call = call;
	}

	public int[] getOpenedTiles() {
		return openedTiles;
	}

	public void setOpenedTiles(int[] openedTiles) {
		this.openedTiles = openedTiles;
	}

	public boolean isKan() {
		return kan;
	}

	public void setKan(boolean kan) {
		this.kan = kan;
	}

	public int getPoint() {
		return point;
	}

	public void setPoint(int point) {
		this.point = point;
	}

}
