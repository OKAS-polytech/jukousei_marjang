package com.example.mahjong.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * ObserverデザインパターンにおけるSubject（観察対象）を定義する抽象クラスです。
 * このクラスを継承するオブジェクト（{@link Table}など）の状態変化を、
 * 登録されたObserver（{@link Observer}）に通知する役割を持ちます。
 */
public abstract class SubjectTable {
	/** このSubjectを観察しているObserverのリスト */
	private final List<Observer> observers = new ArrayList<>();

	/**
	 * このSubjectに新しいObserverを登録します。
	 *
	 * @param observer 登録するObserverオブジェクト
	 */
	public void addObserver(Observer observer) {
		this.observers.add(observer);
	}

	/**
	 * このSubjectから指定されたObserverの登録を解除します。
	 *
	 * @param observer 登録を解除するObserverオブジェクト
	 */
	public void deleteObserver(Observer observer) {
		this.observers.remove(observer);
	}

	/**
	 * 登録されているすべてのObserverに状態の更新を通知します。
	 */
	public void notifyObservers() {
		for (Observer observer : this.observers) {
			observer.update(this);
		}
	}

	/**
	 * 現在の捨て牌の情報を取得します。
	 *
	 * @return プレイヤーの風をキー、捨て牌リストを値とするマップ
	 */
	public abstract Map<Integer, List<TileType>> getDiscard();

	/**
	 * 直近に捨てられた牌のリストを取得します。
	 *
	 * @return 捨て牌のリスト
	 */
	public abstract List<TileType> getDiscardList();

	/**
	 * 直近に捨てられた牌を1枚取得します。

	 * @return 捨て牌
	 */
	public abstract TileType getDiscardTile();

	/**
	 * Subjectの状態を更新するなどの主要な処理を実行します。
	 * 具体的な実装はサブクラスに委ねられます。
	 */
	public abstract void execute();
}
