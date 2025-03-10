package com.pluralsight.controller;

import java.util.List;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.pluralsight.model.Ride;

import org.junit.Test;

public class RestControllerTest {

	@Test(timeout=3000)
	public void testCreateRide(){
		RestTemplate restTemplate = new RestTemplate();
		Ride ride = new Ride();
		ride.setName("The Enlightened Path");
		ride.setDuration(44);
		ride = restTemplate.postForObject("http://localhost:8080/ride_tracker/ride",ride, Ride.class);
		System.out.println(ride);
	}


	@Test(timeout=3000)
	public void testGetRides() {
		RestTemplate restTemplate = new RestTemplate();

		ResponseEntity<List<Ride>> ridesResponse = restTemplate.exchange(
				"http://localhost:8080/ride_tracker/rides", HttpMethod.GET,
				null, new ParameterizedTypeReference<List<Ride>>() {
				});
		List<Ride> rides = ridesResponse.getBody();

		for (Ride ride : rides) {
			System.out.println(ride);
		}
	}

	@Test(timeout=3000)
	public void testGetRide(){
		RestTemplate restTemplate = new RestTemplate();
		Ride ride = restTemplate.getForObject("http://localhost:8080/ride_tracker/ride/12",Ride.class);
		System.out.println(ride);
	}

	@Test(timeout=3000)
	public void testUpdateRide(){
		RestTemplate restTemplate = new RestTemplate();
		Ride ride = restTemplate.getForObject("http://localhost:8080/ride_tracker/ride/12",Ride.class);
		ride.setDuration(ride.getDuration() + 1);
		System.out.println(ride);
		restTemplate.put("http://localhost:8080/ride_tracker/ride",ride);
	}

	@Test(timeout=3000)
	public void testBatchUpdate(){
		RestTemplate restTemplate = new RestTemplate();
		restTemplate.getForObject("http://localhost:8080/ride_tracker/batch",Object.class);
	}

	@Test(timeout=3000)
	public void testDelete(){
		RestTemplate restTemplate = new RestTemplate();
		restTemplate.delete("http://localhost:8080/ride_tracker/delete/14");
	}

	@Test(timeout=3000)
	public void testException(){
		RestTemplate restTemplate = new RestTemplate();
		restTemplate.getForObject("http://localhost:8080/ride_tracker/test", Ride.class);
	}
}
