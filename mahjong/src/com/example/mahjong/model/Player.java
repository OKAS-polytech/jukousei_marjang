package com.example.mahjong.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
		}
	}

	public List<Hands> agari() {
		List<Hands> hands = Judge.judgeHand(this.hand);
		hands.removeIf(tile -> tile.getId() == 99);
		return hands;
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
		// TODO: UIロジックを分離したため、コントローラーで実装する必要があります
		// if (!table.getDiscard().containsKey((Integer) this.wind)) {
		// 	Map<Integer, List<TileType>> callingTile = table.getDiscard();
		// 	List<Boolean> canCalls = Judge.canCall(this.hand, callingTile, this.wind);
		// }
	}

	@Override
	public void tsumo(List<TileType> tsumo) {
		sortHand(this.hand);
		this.hand.addAll(tsumo);
		this.agari = Judge.judgeDuringTheGame(this.hand);
		if (this.agari.get(0) == 1) {
			// agari(); // Controller should handle this
		}
	}

	/**
	 * 指定されたインデックスの牌を捨てます。
	 * @param tileIndex 捨てる牌の、手牌リスト内でのインデックス。
	 * @return 捨てられた牌。
	 */
	public TileType discard(int tileIndex) {
		if (tileIndex < 0 || tileIndex >= this.hand.size()) {
			throw new IndexOutOfBoundsException("Invalid index for discard: " + tileIndex);
		}
		return this.hand.remove(tileIndex);
	}

	@Override
	public List<TileType> selectDiscard() {
		// This method is now handled by the Controller and View.
		throw new UnsupportedOperationException("selectDiscard should not be called from the model.");
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
