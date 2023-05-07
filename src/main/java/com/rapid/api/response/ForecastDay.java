package com.rapid.api.response;
public class ForecastDay {
	private String forecastDate;
	private String minMaxTemp;
	private String windMinMaxSpeed;
	private String sunRise;
	private String sunSet;
	public String getForecastDate() {
		return forecastDate;
	}
	public void setForecastDate(String forecastDate) {
		this.forecastDate = forecastDate;
	}
	public String getMinMaxTemp() {
		return minMaxTemp;
	}
	public void setMinMaxTemp(String minMaxTemp) {
		this.minMaxTemp = minMaxTemp;
	}
	public String getWindMinMaxSpeed() {
		return windMinMaxSpeed;
	}
	public void setWindMinMaxSpeed(String windMinMaxSpeed) {
		this.windMinMaxSpeed = windMinMaxSpeed;
	}
	public String getSunRise() {
		return sunRise;
	}
	public void setSunRise(String sunRise) {
		this.sunRise = sunRise;
	}
	public String getSunSet() {
		return sunSet;
	}
	public void setSunSet(String sunSet) {
		this.sunSet = sunSet;
	}
	@Override
	public String toString() {
		return "ForecastDay [forecastDate=" + forecastDate + ", minMaxTemp=" + minMaxTemp + ", windMinMaxSpeed="
				+ windMinMaxSpeed + ", sunRise=" + sunRise + ", sunSet=" + sunSet + "]";
	}
}
