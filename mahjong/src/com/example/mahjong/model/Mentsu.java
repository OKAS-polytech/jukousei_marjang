package com.example.mahjong.model;

import java.util.List;

/**
 * 麻雀の参加者（プレイヤーやCPU）の基本となる抽象クラスです。
 * 参加者が共通して持つべき状態や、実行すべき基本的なアクションを定義します。
 * {@link Player} クラスはこのクラスを継承します。
 */
public abstract class Mentsu implements Observer, Strategy {
    /** 参加者の手牌 */
    private List<TileType> hand;
    /** リーチ状態かどうか */
    private boolean riichi;
    /** 門前（メンゼン）状態かどうか */
    private boolean menzen;
    /** 副露（鳴き）をしているかどうか */
    private boolean call;
    /** 副露または暗槓した牌 */
    private int[] openedTiles;
    /** カンをしているかどうか */
    private boolean kan;
    /** 持ち点 */
    private int point;
    /** あがり判定結果 */
    private List<Integer> agari;

    /**
     * 牌をツモるアクションを定義します。
     *
     * @param tsumo ツモした牌のリスト（通常は1枚）
     */
    abstract void tsumo(List<TileType> tsumo);

    @Override
    public void update(SubjectTable table) {

    }

    /**
     * 捨てる牌を選択する戦略を定義します。
     *
     * @return 選択された捨て牌のリスト
     */
    @Override
    public abstract List<TileType> selectDiscard();

    /**
     * 他のプレイヤーの捨て牌に対して鳴くかどうかを判断し、実行します。
     *
     * @param discard 他のプレイヤーの捨て牌
     */
    public abstract void call(TileType discard);

    /**
     * ポンを実行します。
     *
     * @param discard ポンする対象の捨て牌
     */
    public abstract void pon(List<TileType> discard);

    /**
     * チーを実行します。
     *
     * @param discard チーする対象の捨て牌
     */
    public abstract void chii(List<TileType> discard);

    /**
     * カンを実行します。
     *
     * @param discard カンする対象の牌
     */
    public abstract void kan(List<TileType> discard);

}
