package com.rapid.api.response;
public class ForecastHour {
	private String forecastDate;
	private String avgTemp;
	private String pressure;
	private String relativeHumidity;
	private String isNight;
	public String getForecastDate() {
		return forecastDate;
	}
	public void setForecastDate(String forecastDate) {
		this.forecastDate = forecastDate;
	}
	public String getAvgTemp() {
		return avgTemp;
	}
	public void setAvgTemp(String avgTemp) {
		this.avgTemp = avgTemp;
	}
	public String getPressure() {
		return pressure;
	}
	public void setPressure(String pressure) {
		this.pressure = pressure;
	}
	public String getRelativeHumidity() {
		return relativeHumidity;
	}
	public void setRelativeHumidity(String relativeHumidity) {
		this.relativeHumidity = relativeHumidity;
	}
	public String getIsNight() {
		return isNight;
	}
	public void setIsNight(String isNight) {
		this.isNight = isNight;
	}
	@Override
	public String toString() {
		return "ForecastHour [forecastDate=" + forecastDate + ", avgTemp=" + avgTemp + ", pressure=" + pressure
				+ ", relativeHumidity=" + relativeHumidity + ", isNight=" + isNight + "]";
	}
}
