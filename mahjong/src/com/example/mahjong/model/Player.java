package com.example.mahjong.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 麻雀のプレイヤー一人ひとりを表現するモデルクラスです。
 * プレイヤーの手牌、状態（リーチ、鳴きなど）、点数といった情報を保持し、
 * ツモや打牌などの基本的なアクションを定義します。
 * このクラスはUIロジックを含まず、純粋なデータとロジックのみを扱います。
 */
public class Player extends Mentsu implements Observer {
	/** プレイヤーを一意に識別するためのID。現状は一人プレイのため固定値。 */
	private static final int id = 0;
	/** プレイヤーの自風（東・南・西・北）。 */
	private int wind;
	/** プレイヤーが現在保持している手牌のリスト。 */
	private List<TileType> hand;
	/** リーチしているかどうかを示すフラグ。 */
	private boolean riichi;
	/** 門前（メンゼン）状態かどうかを示すフラグ。 */
	private boolean menzen;
	/** 副露（鳴き）を行っているかどうかを示すフラグ。 */
	private boolean call;
	/** 副露または暗槓した牌の情報を格納する配列。 */
	private int[] openedTiles;
	/** カンを行っているかどうかを示すフラグ。 */
	private boolean kan;
	/** プレイヤーの現在の持ち点。 */
	private int point;
	/** あがり判定の結果を格納するリスト。 */
	private List<Integer> agari;

	/**
	 * Playerの新しいインスタンスを生成します。
	 * 手牌リストを空の状態で初期化します。
	 */
	public Player() {
		super();
		this.hand = new ArrayList<>();
	}

	/**
	 * プレイヤーに配牌を配ります。
	 * このメソッドはゲーム開始時に一度だけ呼び出されることを想定しています。
	 *
	 * @param haipai 配られる13枚の牌のリスト
	 */
	public void haipai(List<TileType> haipai) {
		if (this.hand.isEmpty()) {
			this.hand = haipai;
		}
	}

	/**
	 * 現在の手牌であがれる役を判定します。
	 *
	 * @return 成立している役（{@link Hands}）のリスト。役がない場合は空のリスト。
	 */
	public List<Hands> agari() {
		List<Hands> hands = Judge.judgeHand(this.hand);
		hands.removeIf(tile -> tile.getId() == 99); // 'NONE' hand type removal
		return hands;
	}

	/**
	 * 手牌を麻雀のルールに基づいた順序（萬子→筒子→索子→字牌）でソートします。
	 *
	 * @param hand ソート対象の手牌リスト
	 */
	public void sortHand(List<TileType> hand) {
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
	/**
	 * 牌を1枚ツモり、手牌に加えます。
	 * ツモ後に手牌は自動的にソートされ、あがり状態が判定されます。
	 *
	 * @param tsumo ツモしてきた牌を含むリスト（通常は1枚）
	 */
	@Override
	public void tsumo(List<TileType> tsumo) {
		this.hand.addAll(tsumo);
		sortHand(this.hand);
		this.agari = Judge.judgeDuringTheGame(this.hand);
		if (this.agari.get(0) == 1) {
			// The decision to declare "agari" is now up to the controller
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
