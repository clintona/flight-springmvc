package au.com.tla.flight.service;

import java.util.List;

import au.com.tla.flight.dao.GenericDAO;
import au.com.tla.flight.model.Flight;

public class HibernateFlightManager implements FlightManager {

	
	private GenericDAO<Flight, Integer> flightDao;
	
	public HibernateFlightManager() {
		// TODO: replace this with dependency injection?
		this.flightDao = new GenericDAO<Flight, Integer>(Flight.class);
	}
	
	void setFlightDAO(GenericDAO<Flight, Integer> dao) {
		this.flightDao = dao;
	}
	
	
	@Override
	public int createFlight(Flight flight) {
		return flightDao.create(flight);
	}

	@Override
	public List<Flight> getFlights() {
		return (List<Flight>) flightDao.getSession().createCriteria(Flight.class).list();
	}

	
	public void deleteFlight(Flight flight) {
		flightDao.delete(flight);
	}

	public Flight getFlightById(int flightId) {
		return flightDao.read(flightId);
	}
}
