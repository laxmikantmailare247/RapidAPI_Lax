package com.rapid.api.response;


public class WeatherAPIHourlyResponse {
	private String locationWithCodeAndCordinates;
	private ForecastHour now = new ForecastHour();
	private ForecastHour nextHour = new ForecastHour();
	public String getLocationWithCodeAndCordinates() {
		return locationWithCodeAndCordinates;
	}	
	public void setLocationWithCodeAndCordinates(String locationWithCodeAndCordinates) {
		this.locationWithCodeAndCordinates = locationWithCodeAndCordinates;
	}
	public ForecastHour getNow() {
		return now;
	}
	public void setNow(ForecastHour now) {
		this.now = now;
	}
	public ForecastHour getNextHour() {
		return nextHour;
	}
	public void setNextHour(ForecastHour nextHour) {
		this.nextHour = nextHour;
	}
	@Override
	public String toString() {
		return "WeatherAPIHourlyResponse [locationWithCodeAndCordinates=" + locationWithCodeAndCordinates + ", now="
				+ now + ", nextHour=" + nextHour + "]";
	}
	
}
