package au.com.tla.flight.service;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.test.context.ContextConfiguration;

import au.com.tla.flight.dao.FlightGenerator;
import au.com.tla.flight.dao.GenericDAO;
import au.com.tla.flight.model.Aircraft;
import au.com.tla.flight.model.Flight;

@ContextConfiguration("/springapp-servlet.xml")
public class HibernateFlightManagerTest {

	private HibernateFlightManager flightManager;
	private GenericDAO<Aircraft, String> wingDao;
	
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		this.flightManager = new HibernateFlightManager();
		this.wingDao = new GenericDAO<Aircraft, String>(Aircraft.class);
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testCreateFlight() {
		Aircraft aspen = wingDao.read("Aspen 3 28");
		Flight flight = FlightGenerator.makeFlight(120, 2300, 5.4f, "Mystic", aspen);
		int flightId = flightManager.createFlight(flight);
		
		assertEquals(flightId, flightManager.getFlightById(flightId).getId());
				
	}

	@Test
	public void testCreateThenGetFlights() {
		// To test this, we must ensure there is at least one Flight in the database
		Aircraft aspen = wingDao.read("Aspen 3 28");
		Flight flight = FlightGenerator.makeFlight(120, 2300, 5.4f, "Mystic", aspen);
		flightManager.createFlight(flight);
		
		List<Flight> flights = flightManager.getFlights();
		assertTrue(flights.size() > 0);
		
		flightManager.deleteFlight(flight);
	}

}
