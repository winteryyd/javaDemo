package com.deppon.demo.java.DesignPatterns.Observer.origin;

public class StatisticsDisplay implements Observer, DisplayElement {
	private float temperature;
	private Subject weatherDate;
	private float max = 0;
	private float min = 100;
	private float sum = 0;
	private int i = 0;

	public StatisticsDisplay(Subject weatherDate) {
		this.weatherDate = weatherDate;
		weatherDate.registerObserver(this);
	}

	public void update(float temperature, float humidity, float pressure) {
		this.temperature = temperature;
		i++;
		sum = sum + temperature;
		if (temperature > max) {
			max = temperature;
		}
		if (temperature < min) {
			min = temperature;
		}
		display();
	}

	public void display() {
		System.out.println("平均温度是：" + (sum / i) + "最高温度是：" + max + "最低温度是："
				+ min);
	}
}