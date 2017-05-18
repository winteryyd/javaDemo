package com.deppon.demo.java.DesignPatterns.Observer.origin;

public class CurrentDisplay implements Observer, DisplayElement {
	private float temperature;
	private float humidity;
	private Subject weatherDate;

	public CurrentDisplay(Subject weatherDate) {
		this.weatherDate = weatherDate;
		weatherDate.registerObserver(this);
	}

	public void update(float temperature, float humidity, float pressure) {
		this.temperature = temperature;
		this.humidity = humidity;
		display();
	}

	public void display() {
		System.out.println("目前状况是温度：" + temperature + "度     " + "湿度："
				+ humidity + "%");
	}
}