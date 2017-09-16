package au.com.tla.flight.dao;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import au.com.tla.flight.model.Aircraft;
import au.com.tla.flight.model.Flight;
import au.com.tla.flight.model.Location;

public class FlightGenericDAOTest {

	private GenericDAO<Flight, Integer> dao;
	private GenericDAO<Aircraft, String> wingDao;
	
	
	@Before
	public void setUp() throws Exception {
		this.dao = new GenericDAO<Flight, Integer>(Flight.class);
		this.wingDao = new GenericDAO<Aircraft, String>(Aircraft.class);
		new GenericDAO<Location, Integer>(Location.class);
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testRead() {
		// success case - assumes data exists :-( !!
		//Flight vendor = dao.read(1);
//		assertNotNull(vendor);
		
		// failure case
		Flight fetched = dao.read(-1);
		assertNull(fetched);
	}
	
	@Test
	public void testCreateAndDelete() {
		Aircraft aspen = wingDao.read("Aspen 3 28");
		assertNotNull(aspen);
//		Aircraft aspen = new Aircraft(new Manufacturer("GRADIENT"), "Aspen 4 28", "2");
		Flight f = FlightGenerator.makeFlight(12, 1000, 4.0f, "Mystic", aspen);
		dao.create(f);
		
		Flight fetched = dao.read(f.getId());
		
		assertEquals(fetched.getMaxHeight(), f.getMaxHeight());
		assertEquals(fetched.getDuration(), f.getDuration());
		assertEquals(fetched.getMaxLift(), f.getMaxLift(), 0.01);
		assertEquals(fetched.getLaunchLocation().getName(), f.getLaunchLocation().getName());
		
		verifyDelete(fetched);
	}
	
	@Test
	public void testUpdateAndDelete() {
		Aircraft aspen = wingDao.read("Aspen 3 28");
		Flight f = FlightGenerator.makeFlight(12, 1000, 4.0f, "Mystic", aspen);
		dao.create(f);
		
		Flight fetched = dao.read(f.getId());
		
		fetched.setComments("Testing");
		dao.update(fetched);
		fetched = dao.read(f.getId());
		assertEquals("Testing", fetched.getComments());
		
		verifyDelete(fetched);
	}

	
	public void verifyDelete(Flight f) {
		dao.delete(f);
		Flight fetched = dao.read(f.getId());
		assertNull(fetched);
	}
}
