package com.tokigames.model;

/**
 * Created by loctran on 21/01/2019.
 */
public class Flight {
	private String id;
	private String provider;
	private String departure;
	private String arrival;
	private Long departureTime;
	private Long arrivalTime;
	private String departureTimeFmt;
	private String arrivalTimeFmt;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getProvider() {
		return provider;
	}

	public void setProvider(String provider) {
		this.provider = provider;
	}

	public String getDeparture() {
		return departure;
	}

	public void setDeparture(String departure) {
		this.departure = departure;
	}

	public String getArrival() {
		return arrival;
	}

	public void setArrival(String arrival) {
		this.arrival = arrival;
	}

	public Long getDepartureTime() {
		return departureTime;
	}

	public void setDepartureTime(Long departureTime) {
		this.departureTime = departureTime;
	}

	public Long getArrivalTime() {
		return arrivalTime;
	}

	public void setArrivalTime(Long arrivalTime) {
		this.arrivalTime = arrivalTime;
	}

	public String getDepartureTimeFmt() {
		return departureTimeFmt;
	}

	public void setDepartureTimeFmt(String departureTimeFmt) {
		this.departureTimeFmt = departureTimeFmt;
	}

	public String getArrivalTimeFmt() {
		return arrivalTimeFmt;
	}

	public void setArrivalTimeFmt(String arrivalTimeFmt) {
		this.arrivalTimeFmt = arrivalTimeFmt;
	}

}
