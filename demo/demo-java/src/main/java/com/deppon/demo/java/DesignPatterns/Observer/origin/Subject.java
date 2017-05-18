package com.deppon.demo.java.DesignPatterns.Observer.origin;

public interface Subject {

	public void registerObserver(Observer o);

	public void removeObserver(Observer o);

	public void notifyObserver();
}