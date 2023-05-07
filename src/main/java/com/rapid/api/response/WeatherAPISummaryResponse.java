package com.rapid.api.response;


public class WeatherAPISummaryResponse {
	private String locationWithCodeAndCordinates;
	private ForecastDay today = new ForecastDay();
	private ForecastDay tomorrow = new ForecastDay();
	public String getLocationWithCodeAndCordinates() {
		return locationWithCodeAndCordinates;
	}
	public ForecastDay getToday() {
		return today;
	}
	public void setToday(ForecastDay today) {
		this.today = today;
	}
	public ForecastDay getTomorrow() {
		return tomorrow;
	}
	public void setTomorrow(ForecastDay tomorrow) {
		this.tomorrow = tomorrow;
	}
	public void setLocationWithCodeAndCordinates(String locationWithCodeAndCordinates) {
		this.locationWithCodeAndCordinates = locationWithCodeAndCordinates;
	}
	@Override
	public String toString() {
		return "WeatherAPISummaryResponse [locationWithCodeAndCordinates=" + locationWithCodeAndCordinates + ", today="
				+ today + ", tomorrow=" + tomorrow + "]";
	}
}
