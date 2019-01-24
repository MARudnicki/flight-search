package com.tokigames;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import com.tokigames.model.Flight;

public class AvailableFlightApplicationTests extends GeneralTest {

	@Override
	@Before
	public void setUp() {
		super.setUp();
	}

	@Test
	public void retrieveCheapFlights() throws Exception {
		String uri = "/?provider=cheap";
		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri).accept(MediaType.APPLICATION_JSON_VALUE))
				.andReturn();

		int status = mvcResult.getResponse().getStatus();
		assertEquals(200, status);
		String content = mvcResult.getResponse().getContentAsString();
		Flight[] listFlight = mapFromJson(content, Flight[].class);
		for (Flight flight : listFlight) {
			assertEquals("cheap", flight.getProvider());
		}
	}

	@Test
	public void retrieveBusinessFlights() throws Exception {
		String uri = "/?provider=business";
		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri).accept(MediaType.APPLICATION_JSON_VALUE))
				.andReturn();

		int status = mvcResult.getResponse().getStatus();
		assertEquals(200, status);
		String content = mvcResult.getResponse().getContentAsString();
		Flight[] listFlight = mapFromJson(content, Flight[].class);
		for (Flight flight : listFlight) {
			assertEquals("business", flight.getProvider());
		}
	}

	@Test
	public void filterByDeparture() throws Exception {
		String uri = "/?departure=Victoria";
		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri).accept(MediaType.APPLICATION_JSON_VALUE))
				.andReturn();

		int status = mvcResult.getResponse().getStatus();
		assertEquals(200, status);
		String content = mvcResult.getResponse().getContentAsString();
		Flight[] listFlight = mapFromJson(content, Flight[].class);
		for (Flight flight : listFlight) {
			assertEquals("Victoria", flight.getDeparture());
		}
	}

	@Test
	public void filterByArrival() throws Exception {
		String uri = "/?arrival=Sarmiento";
		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri).accept(MediaType.APPLICATION_JSON_VALUE))
				.andReturn();

		int status = mvcResult.getResponse().getStatus();
		assertEquals(200, status);
		String content = mvcResult.getResponse().getContentAsString();
		Flight[] listFlight = mapFromJson(content, Flight[].class);
		for (Flight flight : listFlight) {
			assertEquals("Sarmiento", flight.getArrival());
		}
	}

	@Test
	public void sortByArrivalTime() throws Exception {
		String uri = "/?sortBy=arrivalTime";
		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri).accept(MediaType.APPLICATION_JSON_VALUE))
				.andReturn();

		int status = mvcResult.getResponse().getStatus();
		assertEquals(200, status);
		String content = mvcResult.getResponse().getContentAsString();
		Flight[] listFlight = mapFromJson(content, Flight[].class);
		long arrivalTime = -1;
		for (Flight flight : listFlight) {
			assertTrue(flight.getArrivalTime() >= arrivalTime);
			arrivalTime = flight.getArrivalTime();
		}
	}

}
