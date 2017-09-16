package au.com.tla.flight.service;

import java.util.List;

import au.com.tla.flight.model.Flight;

public interface FlightManager {
	int createFlight(Flight flight);
	
	void deleteFlight(Flight flight);
	
	Flight getFlightById(int flightId); 
	
	List<Flight> getFlights();
}
