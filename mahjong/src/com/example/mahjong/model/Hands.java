package com.example.mahjong.model;

/**
 * 麻雀の役を定義する列挙型です。
 * 各役は、ID、日本語名、門前での翻数、副露（鳴き）した場合の翻数を保持します。
 */
public enum Hands {
    /** 断么九（タンヤオ） */
    TANYAO(0, "タンヤオ", 1, 1),
    /** 平和（ピンフ） */
    PINFU(1, "ピンフ", 1, 0),
    /** 七対子（チートイツ） */
    CHITOI(2, "七対子", 2, 2),
    /** 役なし */
    NONE(99, "役なし", 0, 0);

    /** 役を識別するためのID */
    private final int id;
    /** 役の日本語名 */
    private final String name;
    /** 門前（メンゼン）状態での翻数 */
    private final int han;
    /** 副露（フーロ）した場合の翻数。食い下がりがない場合は門前と同じ翻数。 */
    private final int calledHan;

    /**
     * Hands enumのコンストラクタです。
     *
     * @param id        役のID
     * @param name      役の日本語名
     * @param han       門前での翻数
     * @param calledHan 副露した場合の翻数
     */
    Hands(int id, String name, int han, int calledHan) {
        this.id = id;
        this.name = name;
        this.han = han;
        this.calledHan = calledHan;
    }

    /**
     * 役の日本語名を返します。
     *
     * @return 役の名前
     */
    @Override
    public String toString() {
        return this.name;
    }

    /**
     * 役のIDを返します。
     *
     * @return 役ID
     */
	public int getId() {
		return this.id;
	}

    /**
     * 役の日本語名を返します。
     *
     * @return 役の名前
     */
	public String getName() {
		return this.name;
	}

    /**
     * 門前（メンゼン）での翻数を返します。
     *
     * @return 門前での翻数
     */
	public int getHan() {
		return this.han;
	}

    /**
     * 副露（フーロ）した場合の翻数を返します。
     *
     * @return 副露した場合の翻数
     */
	public int getCalledHan() {
		return this.calledHan;
	}



}
