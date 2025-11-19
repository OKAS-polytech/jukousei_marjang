package model.data;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public abstract class SubjectTable {
	// フィールド
	private List<Observer> observers = new ArrayList<>();

	// メソッド
	public void addObserver(Observer observer) {
		this.observers.add(observer);
	}

	public void deleteObserver(Observer observer) {
		this.observers.remove(observer);
	}

	public void notifyObservers() {
		for (Observer observer : this.observers) {
			observer.update(this);
		}
	}

	public abstract Map<Integer, List<TileType>> getDiscard();
	
	public abstract List<TileType> getDiscardList();

	public abstract TileType getDiscardTile();

	public abstract void execute();

}
