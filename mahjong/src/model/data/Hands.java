package model.data;

/**
 * 麻雀の役を定義する列挙型です。
 * 役のid、名前、翻数を格納します。
 */
public enum Hands {
    TANYAO(0, "タンヤオ", 1, 1), PINFU(1, "ピンフ", 1, 0),CHITOI(2,"七対子",2,2), NONE(99,"",0,0);

    private final int id; // 手役id
    private final String name; // 手役の名前
    private final int han; // 門前での翻数
    private final int calledHan; // 副露した場合の翻数

    Hands(int id, String name, int han, int calledHan) {
        this.id = id;
        this.name = name;
        this.han = han;
        this.calledHan = calledHan;
    }

    @Override
    public String toString() {
        return this.name; // 表示したいフィールドを返す
    }

	public int getId() {
		return this.id;
	}

	public String getName() {
		return this.name;
	}

	public int getHan() {
		return this.han;
	}

	public int getCalledHan() {
		return this.calledHan;
	}
    
    

}
