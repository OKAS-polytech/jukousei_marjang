package com.example.mahjong.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 麻雀のゲーム卓（場）の状態を管理するモデルクラスです。
 * 牌山、捨て牌、ドラ、局数など、ゲーム全体の共有情報を保持します。
 */
public class Table extends SubjectTable {

    /** 現在の局数 */
    private int round;
    /** 各プレイヤーの点数を管理するマップ */
    private Map<Mentsu, Integer> points;
    /** 初期点数 */
    private static final int initialPoints = 25000;
    /** ゲーム全体の総点数 */
    private static final int totalPoints = 100000;
    /** 現在の親プレイヤーの位置 */
    private int dealer;
    /** 現在の場風（0:東, 1:南, 2:西, 3:北） */
    private int fieldWind;
    /** 現在成立している面子の数 */
    private int MentsuCount;
    /** ゲームの牌山。ここから牌がツモられます。 */
    private List<TileType> wall;
    /** 各プレイヤーの捨て牌を管理するマップ。キーはプレイヤーの風。 */
    private Map<Integer, List<TileType>> discard = new HashMap<>();
    /** 現在のドラ表示牌 */
    private TileType dora;
    /** 現在の巡目 */
    private int turn;
    /** 現在の積み棒（本場）の数 */
    private int honba;
    /** 場に出ているリーチ棒の数 */
    private int riichiStick;
    /** インスタンスをキーにした捨て牌マップ（用途要確認） */
    private Map<Mentsu, List<TileType>> discardTiles = new HashMap<>();
    /** 各プレイヤーがポンした牌のマップ */
    private Map<Mentsu, List<TileType>> pon = new HashMap<>();
    /** 各プレイヤーがチーした牌のマップ */
    private Map<Mentsu, List<TileType>> chii = new HashMap<>();
    /** 各プレイヤーがカンした牌のマップ */
    private Map<Mentsu, List<TileType>> kan = new HashMap<>();

    /**
     * Tableの新しいインスタンスを生成します。
     */
    public Table() {
        super();
    }
    
    public TileType call(int wind) {
    	if(this.discard.isEmpty()) {
    		return null;
    	}
    	TileType calling = getDiscardTile();
    	this.discard.clear();
    	System.out.println(wind + "家が副露！");
    	return calling;
    }

    /**
     * 指定された手牌リストをルールに基づいた順序でソートします。
     *
     * @param hand ソートする手牌のリスト
     */
    public void sortHand(List<TileType> hand) {
        Collections.sort(hand);
    }

    /**
     * 136枚の牌すべてを含む、シャッフルされていない牌山を生成します。
     *
     * @return 生成された136枚の牌のリスト
     */
    public List<TileType> createWall() {
        List<TileType> wall = new ArrayList<>();
        for (TileType tile : TileType.values()) {
            // 各牌を4枚ずつ追加
            for (int i = 0; i < 4; i++) {
                wall.add(tile);
            }
        }
        return wall;
    }

    /**
     * ランダムにシャッフルされた牌山を生成します。
     *
     * @return シャッフル済みの136枚の牌のリスト
     */
    public List<TileType> createShuffledWall() {
        List<TileType> wall = createWall();
        Collections.shuffle(wall);
        return wall;
    }

    /**
     * 牌山から指定された枚数の牌を取り出し、その牌を牌山から削除します。
     * 主に配牌やツモの際に使用されます。
     *
     * @param wall  牌を取り出す対象の牌山リスト
     * @param count 取り出す牌の枚数
     * @return 牌山から取り出された牌のリスト
     * @throws IllegalArgumentException 牌山の残りの牌が不足している場合
     */
    public List<TileType> dealHand(List<TileType> wall, int count) {
        if (wall.size() < count) {
            throw new IllegalArgumentException("牌山に残っている牌が不足しています。");
        }

        // 牌山の先頭から指定枚数のリストをサブリストとして取得
        List<TileType> hand = new ArrayList<>(wall.subList(0, count));

        // 牌山から取り出した牌を削除
        // 注意: subListのclear()は元のリストからも要素を削除します
        wall.subList(0, count).clear();
        sortHand(hand);

        return hand;
    }

    public List<TileType> getWall() {
        return wall;
    }

    public void setWall(List<TileType> wall) {
        this.wall = wall;
    }

    @Override
    public Map<Integer, List<TileType>> getDiscard() {
        return this.discard;
    }

    public void setDiscard(Map<Integer, List<TileType>> discard) {
        this.discard = discard;
    }

    public Map<Mentsu, List<TileType>> getDiscardTiles() {
        return this.discardTiles;
    }

    public void setDiscardTiles(Map<Mentsu, List<TileType>> discardTiles) {
        this.discardTiles = discardTiles;
    }

    public TileType getDora() {
        return dora;
    }

    public void setDora(TileType dora) {
        this.dora = dora;
    }

    public int getTurn() {
        return turn;
    }

    public void setTurn(int turn) {
        this.turn = turn;
    }

    public int getHonba() {
        return honba;
    }

    public void setHonba(int honba) {
        this.honba = honba;
    }

    public int getRiichiStick() {
        return riichiStick;
    }

    public void setRiichiStick(int riichiStick) {
        this.riichiStick = riichiStick;
    }

    public Map<Mentsu, List<TileType>> getPon() {
        return pon;
    }

    public void setPon(Map<Mentsu, List<TileType>> pon) {
        this.pon = pon;
    }

    public Map<Mentsu, List<TileType>> getChii() {
        return chii;
    }

    public void setChii(Map<Mentsu, List<TileType>> chii) {
        this.chii = chii;
    }

    public Map<Mentsu, List<TileType>> getKan() {
        return kan;
    }

    public void setKan(Map<Mentsu, List<TileType>> kan) {
        this.kan = kan;
    }

    @Override
    public List<TileType> getDiscardList(){
    	return this.discard.values().iterator().next();
    }

    @Override
    public TileType getDiscardTile() {
        return this.discard.values().iterator().next().get(0);
    }

    @Override
    public void execute() {
        // TODO Auto-generated method stub

    }

}
