package headfirst.demo.observer.weather;

import java.util.Observable;
import java.util.Observer;

public class StatisticsDisplay implements Observer, DisplayElement {
	private float maxTemp = 0.0f;
	private float minTemp = 200;
	private float tempSum= 0.0f;
	private int numReadings;
	private WeatherData weatherData;

	public StatisticsDisplay(WeatherData weatherData) {
		this.weatherData = weatherData;
		weatherData.addObserver(this);
	}


	public void display() {
		System.out.println("Avg/Max/Min temperature = " + (tempSum / numReadings)
			+ "/" + maxTemp + "/" + minTemp);
	}

	@Override
	public void update(Observable o, Object arg) {
		if (arg instanceof float[] arr) {
			tempSum += arr[0];
			numReadings++;

			if (arr[0] > maxTemp) {
				maxTemp = arr[0];
			}

			if (arr[0] < minTemp) {
				minTemp = arr[0];
			}
		}
		display();
	}
}
