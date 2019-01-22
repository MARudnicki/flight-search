package com.tokigames.model;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 * Created by loctran on 21/01/2019.
 */
public class FlightMapper {

	public static DateFormat fmt = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
	public static DateFormat fmt2 = new SimpleDateFormat("yyyy-MM-dd HH:mm");

	public static Flight fromCheap(Cheap cheap) {
		Flight flight = new Flight();
		flight.setId(cheap.getId().toString());
		flight.setDeparture(cheap.getDeparture());
		flight.setArrival(cheap.getArrival());
		flight.setDepartureTime(cheap.getDepartureTime());
		flight.setArrivalTime(cheap.getArrivalTime());
		flight.setProvider("cheap");
		
		flight.setDepartureTimeFmt(fmt2.format(flight.getDepartureTime()));
		flight.setArrivalTimeFmt(fmt2.format(flight.getArrivalTime()));
		return flight;
	}

	public static Flight fromBusiness(Business business) {
		Flight flight = new Flight();
		flight.setId(business.getUuid());
		if(business.getFlight() != null) {
			String[] terminals = business.getFlight().split(" -> ");
			flight.setDeparture(terminals[0]);
			flight.setArrival(terminals[1]);
		}
		try {
			flight.setDepartureTime(fmt.parse(business.getDeparture()).getTime());
			flight.setArrivalTime(fmt.parse(business.getArrival()).getTime());
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		flight.setDepartureTimeFmt(fmt2.format(flight.getDepartureTime()));
		flight.setArrivalTimeFmt(fmt2.format(flight.getArrivalTime()));
		
		flight.setProvider("business");
		return flight;
	}
}
