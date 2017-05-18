package com.deppon.demo.java.DesignPatterns.Observer.origin;

public class WeatherStation {

	public static void main(String[] args) {
		WeatherData weatherData = new WeatherData(); // 建立一个WeatherData对象
		CurrentDisplay currentDisplay = new CurrentDisplay(weatherData); // 建立布告板，并把WeatherData传给它们
		StatisticsDisplay statisticsDisplay = new StatisticsDisplay(weatherData);

		weatherData.setMeasurements(80, 65, 30.4f); // 模拟新的气象测量
		weatherData.setMeasurements(82, 70, 29.2f);
		weatherData.setMeasurements(78, 90, 29.2f);
	}
}