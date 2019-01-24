package com.tokigames.controller;

import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.tokigames.model.Business;
import com.tokigames.model.Cheap;
import com.tokigames.model.Flight;
import com.tokigames.model.FlightMapper;

import io.swagger.annotations.ApiParam;

/**
 * Created by loctran on 21/01/2019.
 */

@RestController
@RequestMapping("/")
public class SearchController {

	@Value("${cheap.url}")
	String CHEAP_URL;
	
	@Value("${business.url}")
	String BUSINESS_URL;

	@GetMapping
	public List<Flight> searchFlight(
			@ApiParam(value = "Departure airport name") @RequestParam(required = false) String departure,
			@ApiParam(value = "Arrival airport name") @RequestParam(required = false) String arrival,
			@ApiParam(value = "'cheap' or 'business', empty for both") @RequestParam(required = false) String provider,
			@ApiParam(value = "'departure' or 'arrival' or 'departureTime' or 'arrivalTime', default is 'departureTime'") @RequestParam(required = false, defaultValue="departureTime") String sortBy,
			@ApiParam(value = "Page number, empty to disable paging") @RequestParam(required = false) Integer page,
			@ApiParam(value = "Page size, empty to disable paging") @RequestParam(required = false) Integer size) {

		List<Flight> allFlights = new LinkedList<>();
		RestTemplate restTemplate = new RestTemplate();

		// Search for cheap flights
		if(StringUtils.isEmpty(provider) || "cheap".equalsIgnoreCase(provider)){
			List<Cheap> cheap = Arrays.asList(restTemplate.getForObject(CHEAP_URL, Cheap[].class));
			List<Flight> cheapFlight = cheap.stream().map(p -> FlightMapper.fromCheap(p)).collect(Collectors.toList());
			allFlights.addAll(cheapFlight);
		}

		// Search for business flights
		if(StringUtils.isEmpty(provider) || "business".equalsIgnoreCase(provider)){
			List<Business> business = Arrays.asList(restTemplate.getForObject(BUSINESS_URL, Business[].class));
			List<Flight> businessFlight = business.stream().map(p -> FlightMapper.fromBusiness(p))
					.collect(Collectors.toList());
			allFlights.addAll(businessFlight);
		}
		
		List<Flight> resultFlights = allFlights; 
		
		// Filter by departure
		if(!StringUtils.isEmpty(departure)) {
			resultFlights = resultFlights.stream()
	                .filter(flight -> departure.equalsIgnoreCase(flight.getDeparture()))
	                .collect(Collectors.toList()); 
		}

		// Filter by arrival
		if(!StringUtils.isEmpty(arrival)) {
			resultFlights = resultFlights.stream()
	                .filter(flight -> arrival.equalsIgnoreCase(flight.getArrival()))
	                .collect(Collectors.toList()); 
		}

		// Sorting
		switch(sortBy) {
			case "departure": resultFlights.sort(Comparator.comparing(Flight::getDeparture)); break;
			case "arrival": resultFlights.sort(Comparator.comparing(Flight::getArrival)); break;
			case "arrivalTime": resultFlights.sort(Comparator.comparing(Flight::getArrivalTime)); break;
			default: resultFlights.sort(Comparator.comparing(Flight::getDepartureTime)); break;
		}
		
		// Paging
		if(page != null && page > 0 && size != null && size > 0) {
			int fromIndex = (page - 1) * size;
			int toIndex = fromIndex + size;
			
			// to avoid index out of bounds exception
			if(fromIndex < 0) fromIndex = 0;
			if(toIndex < 0) toIndex = 1;

			if(fromIndex >= resultFlights.size()) fromIndex = resultFlights.size() - 1;
			if(toIndex >= resultFlights.size()) toIndex = resultFlights.size();
			
			resultFlights = resultFlights.subList(fromIndex , toIndex); 
		}
		
		return resultFlights;
	}

}
