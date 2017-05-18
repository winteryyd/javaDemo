package com.deppon.demo.java.DesignPatterns.Observer.origin;

import java.util.ArrayList;

public class WeatherData implements Subject {

	private ArrayList observers;
	private float temperature;
	private float pressure;
	private float humidity;

	public WeatherData() {
		observers = new ArrayList(); // 加上一个ArrayList来记录观察者，此ArrayList是在构造器中产生的
	}

	public void registerObserver(Observer o) {
		observers.add(o); // 有观察者注册时，将其加到ArrayList后面
	}

	public void removeObserver(Observer o) {
		int i = observers.indexOf(o); // 观察者取消注册时，将其从ArrayList中删除
		if (i >= 0) {
			observers.remove(i);
		}
	}

	public void notifyObserver() {
		for (int i = 0; i < observers.size(); i++) { // 通知每一位观察者
			Observer observer = (Observer) observers.get(i);
			observer.update(temperature, humidity, pressure);
		}
	}

	public void measurementsChanged() {
		notifyObserver();
	}

	public void setMeasurements(float temperature, float humidity,
			float pressure) {
		this.temperature = temperature;
		this.humidity = humidity;
		this.pressure = pressure;
		measurementsChanged();
	}

}