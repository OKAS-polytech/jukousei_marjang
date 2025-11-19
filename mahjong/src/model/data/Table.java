package model.data;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Table extends SubjectTable {

    // フィールド
    private int round; // 局数
    
    private Map<Mentsu, Integer> points; // 点数管理
    private static final int initialPoints = 25000; // 初期点数
    private static final int totalPoints = 100000; // 総点数
    
    private int dealer; // 親の位置
    private int fieldWind; // 場風（0,東 1,南 2,西 3,北）
    private int MentsuCount; // 面子の数

    private List<TileType> wall; // 牌山
    private Map<Integer, List<TileType>> discard = new HashMap<>(); // 打牌者の風をキー値とした打牌

    private TileType dora; // ドラ表示牌
    private int turn; // 巡目
    private int honba; // ◯本場
    private int riichiStick; // リーチ棒の本数

    private Map<Mentsu, List<TileType>> discardTiles = new HashMap<>();// インスタンスをキー値にした捨て牌マップ
    private Map<Mentsu, List<TileType>> pon = new HashMap<>(); // ポン
    private Map<Mentsu, List<TileType>> chii = new HashMap<>(); // チー
    private Map<Mentsu, List<TileType>> kan = new HashMap<>(); // カン

    // コンストラクタ
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

    public void sortHand(List<TileType> hand) {
        /**
         * 手牌を萬子、筒子、索子、風牌、三元牌の順でソートする
         * 
         * @param ソート前の手牌リスト
         * @return ソート後の手牌リスト
         */
        Collections.sort(hand);
    }

    public List<TileType> createWall() {
        /**
         * 136枚の牌を格納した牌山リストを生成する
         * 
         * @return シャッフル前の牌山リスト
         */
        List<TileType> wall = new ArrayList<>();

        // 34種類の牌すべてを取得
        for (TileType tile : TileType.values()) {
            // 各牌を4枚ずつ追加
            for (int i = 0; i < 4; i++) {
                wall.add(tile);
            }
        }
        return wall;
    }

    /**
     * シャッフルされた牌山リストを生成する
     * 
     * @return
     */
    public List<TileType> createShuffledWall() {
        List<TileType> wall = createWall();

        // 牌山をシャッフル
        Collections.shuffle(wall);

        return wall;
    }

    /**
     * 牌山から指定された枚数（配牌）を取り出し、牌山から削除する
     * 
     * @param wall  シャッフルされた牌山リスト
     * @param count 取り出す牌の枚数
     * @return 取り出した手牌リスト
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
