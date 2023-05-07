package com.rapid.api;

import java.util.Enumeration;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import javax.ws.rs.core.Context;
import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping(path="/weather", produces="application/json")
public class WeatherAPIControllerStr {
	@PostMapping("/forecast-summary-str")
	public ResponseEntity<String> processWeatherSummary(
			@RequestParam(value = "location", defaultValue = "Mumbai") String location,
			@Context HttpServletRequest request) {
		System.out.println("in summary : "+location);
		RestTemplate restTemplate = new RestTemplate();
	    String url = "https://forecast9.p.rapidapi.com/rapidapi/forecast/"+location+"/summary/";
	    System.out.println("URL : "+url);
	    ResponseEntity<String> response = null;
	    try {
	    	HttpHeaders headers = createHttpHeaders(request);
	    	HttpEntity<String> entity = new HttpEntity<>("body", headers);
	    	response = restTemplate.exchange(url, HttpMethod.GET, entity, String.class);
	    	
	    	System.out.println("Result - status ("+ response.getStatusCode() + ") has body: " + response.hasBody());
	    	System.out.println("res-->>{}{} : "+response.getBody());
	    	
		} catch (Exception e) {
			System.out.println("exception: "+e.getMessage());
			response = new ResponseEntity<String>(e.getMessage(), HttpStatus.NOT_FOUND);
		}
		return response;
	}
	@PostMapping("/hourly-details-str")
	public ResponseEntity<String> processHourlyDetails(
			@RequestParam(value = "locationHourly", defaultValue = "Mumbai") String locationHourly,
			@Context HttpServletRequest request) {
		System.out.println("in hourly : "+locationHourly);
		RestTemplate restTemplate = new RestTemplate();
	    String url = "https://forecast9.p.rapidapi.com/rapidapi/forecast/"+locationHourly+"/hourly/";
	    System.out.println("H URL : "+url);
	    ResponseEntity<String> response = null;
	    try {
	    	HttpHeaders headers = createHttpHeaders(request);
	    	HttpEntity<String> entity = new HttpEntity<>("hourlybody", headers);
	    	response = restTemplate.exchange(url, HttpMethod.GET, entity, String.class);
	    	
	    	System.out.println("HResult - status ("+ response.getStatusCode() + ") has body: " + response.hasBody());
	    	System.out.println("Hres-->>{}{} : "+response.getBody());
		} catch (Exception e) {
			System.out.println("Hexception: "+e.getMessage());
			response = new ResponseEntity<String>(e.getMessage(), HttpStatus.NOT_FOUND);
		}
		return response;
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
