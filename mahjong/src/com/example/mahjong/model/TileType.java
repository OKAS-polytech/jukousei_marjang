package com.example.mahjong.model;

/**
 * 34種類の麻雀牌を定義する列挙型です。
 * 各牌は、一意のID、ソート順、および表示名を保持します。
 * {@link Comparable} を実装しているため、牌はIDに基づいて自然順序でソートされます。
 */
public enum TileType implements Comparable<TileType> {
    // Manzu (Characters)
    /** 萬子の一 */ MAN1(0, "M1", "一", 1),
    /** 萬子の二 */ MAN2(1, "M2", "二", 1),
    /** 萬子の三 */ MAN3(2, "M3", "三", 1),
    /** 萬子の四 */ MAN4(3, "M4", "四", 1),
    /** 萬子の五 */ MAN5(4, "M5", "五", 1),
    /** 萬子の六 */ MAN6(5, "M6", "六", 1),
    /** 萬子の七 */ MAN7(6, "M7", "七", 1),
    /** 萬子の八 */ MAN8(7, "M8", "八", 1),
    /** 萬子の九 */ MAN9(8, "M9", "九", 1),

    // Pinzu (Circles)
    /** 筒子の一 */ PIN1(9, "P1", "①", 2),
    /** 筒子の二 */ PIN2(10, "P2", "②", 2),
    /** 筒子の三 */ PIN3(11, "P3", "③", 2),
    /** 筒子の四 */ PIN4(12, "P4", "④", 2),
    /** 筒子の五 */ PIN5(13, "P5", "⑤", 2),
    /** 筒子の六 */ PIN6(14, "P6", "⑥", 2),
    /** 筒子の七 */ PIN7(15, "P7", "⑦", 2),
    /** 筒子の八 */ PIN8(16, "P8", "⑧", 2),
    /** 筒子の九 */ PIN9(17, "P9", "⑨", 2),

    // Souzu (Bamboos)
    /** 索子の一 */ SOU1(18, "S1", "1", 3),
    /** 索子の二 */ SOU2(19, "S2", "2", 3),
    /** 索子の三 */ SOU3(20, "S3", "3", 3),
    /** 索子の四 */ SOU4(21, "S4", "4", 3),
    /** 索子の五 */ SOU5(22, "S5", "5", 3),
    /** 索子の六 */ SOU6(23, "S6", "6", 3),
    /** 索子の七 */ SOU7(24, "S7", "7", 3),
    /** 索子の八 */ SOU8(25, "S8", "8", 3),
    /** 索子の九 */ SOU9(26, "S9", "9", 3),

    // Jihai (Honors/Terminals)
    /** 字牌の東 */ EAST(27, "E", "東", 4),
    /** 字牌の南 */ SOUTH(28, "S", "南", 4),
    /** 字牌の西 */ WEST(29, "W", "西", 4),
    /** 字牌の北 */ NORTH(30, "N", "北", 4),
    /** 字牌の白 */ HAKU(31, "WHT", "白", 5),
    /** 字牌の發 */ HATSU(32, "GRN", "發", 5),
    /** 字牌の中 */ CHUN(33, "RED", "中", 5);

    /** 牌を一意に識別するためのID (0-33) */
    private final int id;
    /** 牌の短い名前（例: "M1", "P5", "E"） */
    private final String name;
    /** 牌の表示名（例: "一", "⑤", "東"） */
    private final String name2;
    /** 牌の種類を示すコード（1:萬子, 2:筒子, 3:索子, 4:風牌, 5:三元牌） */
    private final int tileType;

    /**
     * TileType enumのコンストラクタです。
     *
     * @param id       牌の一意なID (0-33)
     * @param name     牌の短い名前
     * @param name2    牌の表示名
     * @param tileType 牌の種類コード
     */
    TileType(int id, String name, String name2, int tileType) {
        this.id = id;
        this.name = name;
        this.name2 = name2;
        this.tileType = tileType;
    }

    /*
     * なんかコメントアウトしても動く。黙示的にOverrideしてんのかな？
     *
     * @Override
     * public int compareTo(TileType other) {
     * return Integer.compare(this.id, other.id);
     * // または return this.id - other.id;
     * }
     */

    @Override
    public String toString() {
        return this.name2; // 表示したいフィールドを返す
    }

    /**
     * 指定されたIDに対応する{@code TileType}定数を返します。
     *
     * @param id 検索する牌のID (0-33)
     * @return 指定されたIDを持つ{@code TileType}
     * @throws IllegalArgumentException 指定されたIDが無効な場合
     */
    public static TileType getTile(int id) {
        for (TileType tile : TileType.values()) {
            if (tile.id == id) {
                return tile;
            }
        }
        throw new IllegalArgumentException("Invalid TileType ID: " + id);
    }

    /**
     * 指定された表示名に対応する{@code TileType}定数を返します。
     *
     * @param name2 検索する牌の表示名
     * @return 指定された表示名を持つ{@code TileType}
     * @throws IllegalArgumentException 指定された表示名が無効な場合
     */
    public static TileType getTile(String name2) {
        for (TileType tile : TileType.values()) {
            if (tile.name2.equals(name2)) {
                return tile;
            }
        }
        throw new IllegalArgumentException("Invalid TileType name2: " + name2);
    }

    /**
     * 牌の一意なID（0-33）を返します。
     * このIDは牌のソート順と一致します。
     *
     * @return 牌のID
     */
    public int getId() {
        return this.id;
    }

    /**
     * 牌の短い名前（"M1", "E"など）を返します。
     *
     * @return 牌の短い名前
     */
    public String getName() {
        return this.name;
    }

    /**
     * 牌の表示名（"一", "東"など）を返します。
     *
     * @return 牌の表示名
     */
    public String getName2() {
        return this.name2;
    }

    /**
     * 牌の種類を示すコードを返します。
     * (1:萬子, 2:筒子, 3:索子, 4:風牌, 5:三元牌)
     *
     * @return 牌の種類コード
     */
    public int getTileType() {
        return this.tileType;
    }

}
