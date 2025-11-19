package model.data;

/**
 * 麻雀のゲーム進行を定義するクラスです。
 * まだとても曖昧です。
 */
public class GamePlay {
	public boolean canPon;
	public boolean canChii;
	public boolean canKan;

	public GamePlay() {
		this.canPon = false;
		this.canChii = false;
		this.canKan = false;
	}

}
