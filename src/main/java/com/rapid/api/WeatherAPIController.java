	package com.rapid.api;
	
	import java.util.Enumeration;
	import org.json.JSONArray;
	import org.json.JSONObject;
	import org.springframework.http.HttpEntity;
	import org.springframework.http.HttpHeaders;
	import org.springframework.http.HttpMethod;
	import org.springframework.http.ResponseEntity;
	import org.springframework.web.bind.annotation.PostMapping;
	import org.springframework.web.bind.annotation.RequestMapping;
	import org.springframework.web.bind.annotation.RequestParam;
	import org.springframework.web.bind.annotation.RestController;
	import org.springframework.web.client.RestTemplate;
	import javax.ws.rs.core.Context;
	
	import com.rapid.api.response.WeatherAPIHourlyResponse;
	import com.rapid.api.response.WeatherAPISummaryResponse;
	
	import jakarta.servlet.http.HttpServletRequest;
	
	@RestController
	@RequestMapping(path="/weather", produces="application/json")
	public class WeatherAPIController {
		@PostMapping("/forecast-summary")
		//@ResponseStatus(code = HttpStatus.CREATED)
		public WeatherAPISummaryResponse processWeatherSummary(
				@RequestParam(value = "location", defaultValue = "Mumbai") String location,
				@Context HttpServletRequest request) {
			System.out.println("in summary : "+location);
	    	WeatherAPISummaryResponse weatherAPISummaryResponse = new WeatherAPISummaryResponse();
			RestTemplate restTemplate = new RestTemplate();
		    String url = "https://forecast9.p.rapidapi.com/rapidapi/forecast/"+location+"/summary/";
		    System.out.println("URL : "+url);
		    try {
		    	HttpHeaders headers = createHttpHeaders(request);
		    	HttpEntity<String> entity = new HttpEntity<>("body", headers);
		    	ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, entity, String.class);
		    	
		    	System.out.println("Result - status ("+ response.getStatusCode() + ") has body: " + response.hasBody());
		    	System.out.println("res-->>{}{} : "+response.getBody());
		    	JSONObject json = new JSONObject();
		    	if(!"".equals(response.getBody())){
		    		json = new JSONObject(response.getBody());
		    		if(response.getStatusCode().toString().contains("200")) {
			    		//setting location
			    		JSONObject jsonLocation = json.getJSONObject("location");
			    		JSONObject jsonLocationCordinates = jsonLocation.getJSONObject("coordinates");
			    		System.out.println("location json ->"+jsonLocation);
			    		weatherAPISummaryResponse.setLocationWithCodeAndCordinates(jsonLocation.get("name").toString()
			    				+"("+jsonLocation.get("code").toString()+")"+" - Cordinates : ("
			    				+jsonLocationCordinates.get("latitude").toString()
			    				+","+jsonLocationCordinates.get("longitude").toString()+")");
			    		//setting forecast
			    		JSONObject jsonForecast = json.getJSONObject("forecast");
			    		JSONArray items = (JSONArray) jsonForecast.get("items");
			    		//today
			    		JSONObject jsonTodayForecast = (JSONObject) items.get(0);
			    		weatherAPISummaryResponse.getToday().setForecastDate(jsonTodayForecast.get("date").toString());
			    		String temp = "Min Temp= "+((JSONObject) jsonTodayForecast.get("temperature")).get("min").toString()
			    				+"°C & Max Temp= "+((JSONObject) jsonTodayForecast.get("temperature")).get("max").toString()+"°C";
			    		weatherAPISummaryResponse.getToday().setMinMaxTemp(temp);
			    		String wind = "Min = "+((JSONObject) jsonTodayForecast.get("wind")).get("min").toString()
			    				+" Km/Hr & Max = "+((JSONObject) jsonTodayForecast.get("wind")).get("max").toString()+" Km/Hr";
			    		weatherAPISummaryResponse.getToday().setWindMinMaxSpeed(wind);
			    		String sunRise = "Rise: "+((JSONObject) jsonTodayForecast.get("astronomy")).get("sunrise").toString();
			    		weatherAPISummaryResponse.getToday().setSunRise(sunRise);
			    		String sunSet = "Set: "+((JSONObject) jsonTodayForecast.get("astronomy")).get("sunset").toString();
			    		weatherAPISummaryResponse.getToday().setSunSet(sunSet);
			    		//tomorrow
			    		JSONObject jsonTomorrowForecast = (JSONObject) items.get(1);
			    		weatherAPISummaryResponse.getTomorrow().setForecastDate(jsonTomorrowForecast.get("date").toString());
			    		temp = "Min Temp= "+((JSONObject) jsonTomorrowForecast.get("temperature")).get("min").toString()
			    				+"°C & Max Temp= "+((JSONObject) jsonTomorrowForecast.get("temperature")).get("max").toString()+"°C";
			    		weatherAPISummaryResponse.getTomorrow().setMinMaxTemp(temp);
			    		wind = "Min = "+((JSONObject) jsonTomorrowForecast.get("wind")).get("min").toString()
			    				+" Km/Hr & Max = "+((JSONObject) jsonTomorrowForecast.get("wind")).get("max").toString()+" Km/Hr";
			    		weatherAPISummaryResponse.getTomorrow().setWindMinMaxSpeed(wind);
			    		sunRise = "Rise: "+((JSONObject) jsonTomorrowForecast.get("astronomy")).get("sunrise").toString();
			    		weatherAPISummaryResponse.getTomorrow().setSunRise(sunRise);
			    		sunSet = "Set: "+((JSONObject) jsonTomorrowForecast.get("astronomy")).get("sunset").toString();
			    		weatherAPISummaryResponse.getTomorrow().setSunSet(sunSet);
			    		System.out.println("weatherAPISummaryResponse json ->"+weatherAPISummaryResponse);
			    	}
		    	}
		    	
			} catch (Exception e) {
				System.out.println("exception: "+e.getMessage());
			}
			return weatherAPISummaryResponse;
		}
		@PostMapping("/hourly-details")
		//@ResponseStatus(code = HttpStatus.CREATED)
		public WeatherAPIHourlyResponse processHourlyDetails(@RequestParam(value = "locationHourly", defaultValue = "Mumbai") String locationHourly,
				@Context HttpServletRequest request) {
			Enumeration<String> headerNames = request.getHeaderNames();
			System.out.println(headerNames);
			System.out.println("in hourly : "+locationHourly);
			WeatherAPIHourlyResponse weatherAPIHourlyResponse = new WeatherAPIHourlyResponse();
			RestTemplate restTemplate = new RestTemplate();
		    String url = "https://forecast9.p.rapidapi.com/rapidapi/forecast/"+locationHourly+"/hourly/";
		    System.out.println("H URL : "+url);
		    try {
		    	HttpHeaders headers = createHttpHeaders(request);
		    	HttpEntity<String> entity = new HttpEntity<>("hourlybody", headers);
		    	ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, entity, String.class);
		    	
		    	System.out.println("HResult - status ("+ response.getStatusCode() + ") has body: " + response.hasBody());
		    	System.out.println("Hres-->>{}{} : "+response.getBody());
		    	
		    	JSONObject json = new JSONObject();
		    	if(!"".equals(response.getBody())){
		    		json = new JSONObject(response.getBody());
		    		if(response.getStatusCode().toString().contains("200")) {
			    		//setting location
			    		JSONObject jsonLocation = json.getJSONObject("location");
			    		JSONObject jsonLocationCordinates = jsonLocation.getJSONObject("coordinates");
			    		System.out.println("location json ->"+jsonLocation);
			    		weatherAPIHourlyResponse.setLocationWithCodeAndCordinates(jsonLocation.get("name").toString()
			    				+"("+jsonLocation.get("code").toString()+")"+" - Cordinates : ("
			    				+jsonLocationCordinates.get("latitude").toString()
			    				+","+jsonLocationCordinates.get("longitude").toString()+")");
			    		//setting forecast
			    		JSONObject jsonForecast = json.getJSONObject("forecast");
			    		JSONArray items = (JSONArray) jsonForecast.get("items");
			    		//Now
			    		JSONObject jsonNowForecast = (JSONObject) items.get(7);
			    		weatherAPIHourlyResponse.getNow().setForecastDate(jsonNowForecast.get("date").toString());
			    		String temp = "Avg Temp= "+((JSONObject) jsonNowForecast.get("temperature")).get("avg").toString()+"°C.";
			    		weatherAPIHourlyResponse.getNow().setAvgTemp(temp);
			    		weatherAPIHourlyResponse.getNow().setPressure("Pressure: "+jsonNowForecast.get("pressure").toString());
			    		weatherAPIHourlyResponse.getNow().setRelativeHumidity("Rel Humidity: "+jsonNowForecast.get("relativeHumidity").toString());
			    		weatherAPIHourlyResponse.getNow().setIsNight("Is Night: "+jsonNowForecast.get("isNight").toString());
			    		//Next Hour
			    		JSONObject jsonNextHourForecast = (JSONObject) items.get(8);
			    		weatherAPIHourlyResponse.getNextHour().setForecastDate(jsonNextHourForecast.get("date").toString());
			    		temp = "Avg Temp= "+((JSONObject) jsonNextHourForecast.get("temperature")).get("avg").toString()+"°C.";
			    		weatherAPIHourlyResponse.getNextHour().setAvgTemp(temp);
			    		weatherAPIHourlyResponse.getNextHour().setPressure("Pressure: "+jsonNextHourForecast.get("pressure").toString());
			    		weatherAPIHourlyResponse.getNextHour().setRelativeHumidity("Rel Humidity: "+jsonNextHourForecast.get("relativeHumidity").toString());
			    		weatherAPIHourlyResponse.getNextHour().setIsNight("Is Night: "+jsonNextHourForecast.get("isNight").toString());
			    		System.out.println("weatherAPIHourlyResponse json ->"+weatherAPIHourlyResponse);
			    	}
		    	}
			} catch (Exception e) {
				System.out.println("Hexception: "+e.getMessage());
			}
			return weatherAPIHourlyResponse;
		}
		private HttpHeaders createHttpHeaders(HttpServletRequest request){
		    HttpHeaders headers = new HttpHeaders();
	//	    headers.set("content-type", "application/octet-stream");
	//	    //2407Gmail
	//	    //headers.set("X-RapidAPI-Key", "5fee64386amshfcf92f240350a04p1b431cjsn540c9173318f");
	//	    //luckyGmail
	//	    headers.set("X-RapidAPI-Key", "7c76aad86amsha554455a1c3b232p1deb8bjsnb3a917e7a462");
	//	    //.nittGmail
	//	    //headers.set("X-RapidAPI-Key", "8affd2b020mshd87876270520984p19f447jsnc9ed8b88ec40");
	//	    
	//	    headers.set("X-RapidAPI-Host", "forecast9.p.rapidapi.com");
	//	    System.out.println("in headers");
		    
		    Enumeration<String> headerNames = request.getHeaderNames();
		    int i=0;
			while(headerNames.hasMoreElements() && i<3) {
				String key = headerNames.nextElement();
				System.out.println(key+"="+request.getHeader(key));
				headers.set(key, request.getHeader(key));
				i++;
			}
		    return headers;
		}
	}
