package model.data;

/**
 * 麻雀牌を定義する列挙型です。
 * 牌のID、表示名、役判定に使用する情報を格納します。
 */
public enum TileType implements Comparable<TileType> {
    // 萬子 (マンズ)
    MAN1(0, "M1", "一", 1), MAN2(1, "M2", "二", 1), MAN3(2, "M3", "三", 1), MAN4(3, "M4", "四", 1), MAN5(4, "M5", "五", 1),
    MAN6(5, "M6", "六", 1), MAN7(6, "M7", "七", 1), MAN8(7, "M8", "八", 1), MAN9(8, "M9", "九", 1),

    // 筒子 (ピンズ)
    PIN1(9, "P1", "①", 2), PIN2(10, "P2", "②", 2), PIN3(11, "P3", "③", 2), PIN4(12, "P4", "④", 2),
    PIN5(13, "P5", "⑤", 2),
    PIN6(14, "P6", "⑥", 2), PIN7(15, "P7", "⑦", 2), PIN8(16, "P8", "⑧", 2), PIN9(17, "P9", "⑨", 2),

    // 索子 (ソウズ)
    SOU1(18, "S1", "1", 3), SOU2(19, "S2", "2", 3), SOU3(20, "S3", "3", 3), SOU4(21, "S4", "4", 3),
    SOU5(22, "S5", "5", 3),
    SOU6(23, "S6", "6", 3), SOU7(24, "S7", "7", 3), SOU8(25, "S8", "8", 3), SOU9(26, "S9", "9", 3),

    // 字牌 (ジハイ)
    EAST(27, "E", "東", 4), SOUTH(28, "S", "南", 4), WEST(29, "W", "西", 4), NORTH(30, "N", "北", 4),
    HAKU(31, "WHT", "白", 5), HATSU(32, "GRN", "發", 5), CHUN(33, "RED", "中", 5);

    // --- フィールド、コンストラクタ、メソッド ---

    private final int id;
    private final String name;// M1,M2...
    private final String name2;// 一、二...
    private final int tileType;// 萬子は1、筒子は2～三元牌は5

    /**
     * Enum定数に対応するIDを設定するコンストラクタ
     * 
     * @param id       0から33の牌の一意なID
     * @param name     牌の短い名前（略記）
     * @param name2    牌の表示名（漢字、記号）
     * @param tileType 牌の種類
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
     * IDを引数にその牌を取得します。
     * 
     * @param ID
     * @return 牌
     */
    public static TileType getTile(int id) {
        for (TileType tile : TileType.values()) {
            if (tile.id == id) {
                return tile;
            }
        }
        throw new IllegalArgumentException("Invalid JudgeResult ID: " + id);
    }

    /**
     * 表示名を引数にその牌を取得します。
     * 
     * @param 表示名
     * @return 牌
     */
    public static TileType getTile(String name2) {
        for (TileType tile : TileType.values()) {
            if (tile.name2.equals(name2)) {
                return tile;
            }
        }
        throw new IllegalArgumentException("Invalid JudgeResult ID: " + name2);
    }

    /**
     * 牌の一意なIDを取得します。
     * ordinal()メソッドがあるのでいらないかもしれません。
     * 
     * @return 牌のID
     */
    public int getId() {
        return this.id;
    }

    /**
     * 牌の短い名前（略記）を取得します。
     * 
     * @return 牌の略記名
     */
    public String getName() {
        return this.name;
    }

    /**
     * 牌の表示名（漢字など）を取得します。
     * 
     * @return 牌の表示名
     */
    public String getName2() {
        return this.name2;
    }

    /**
     * 
     */
    public int getTileType() {
        return this.tileType;
    }

}
