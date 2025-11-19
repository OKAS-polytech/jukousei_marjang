package model.data;

import java.util.List;

/**
 * 麻雀の面子(CP含むゲームの参加者)が持つべきメソッドを定義するabstractクラスです。
 */
public abstract class Mentsu implements Observer, Strategy {
    // フィールド
    private List<TileType> hand;
    private boolean riichi; // リーチ
    private boolean menzen; // メンゼン
    private boolean call; // 副露の有無
    private int[] openedTiles; // 副露または暗槓した手牌コードを格納
    private boolean kan; // カンの有無
    private int point; // 点棒
    private List<Integer> agari;// あがり情報が入ったリスト

    /**
     * ツモるメソッドです。
     */
    void tsumo(List<TileType> tsumo) {

    }

    @Override
    public void update(SubjectTable table) {

    }

    /**
     * 打牌選択をするメソッドです。
     * 
     * @return 打牌
     */
    public List<TileType> selectDiscard() {
        return selectDiscard();
    }

    /**
     * 副露をするか判断するクラスです。
     * 
     * @param 他家の打牌
     */
    public void call(TileType discard) {

    }

    /**
     * ポンをするメソッドです。
     * 
     * @param 他家の打牌
     */
    public void pon(List<TileType> discard) {
        this.hand.addAll(discard);
    }

    /**
     * チーをするメソッドです。
     * 
     * @param 他家の打牌
     */
    public void chii(List<TileType> discard) {
        this.hand.addAll(discard);
    }

    /**
     * カンをするメソッドです。
     * 
     * @param 他家の打牌
     * @return カンした手牌リスト
     */
    public void kan(List<TileType> discard) {
        this.hand.addAll(discard);
    }

}
