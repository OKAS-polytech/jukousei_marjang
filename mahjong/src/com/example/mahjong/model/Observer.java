package com.example.mahjong.model;

/**
 * ObserverデザインパターンにおけるObserver（観察者）を定義するインターフェースです。
 * Subject（観察対象）の状態が変化した際に通知を受け取ります。
 *
 * @see SubjectTable
 */
public interface Observer {
    /**
     * 観察対象である{@link SubjectTable}から状態の更新通知を受け取った際に呼び出されます。
     *
     * @param table 状態が更新されたSubjectオブジェクト
     */
    void update(SubjectTable table);
}
