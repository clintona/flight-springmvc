package au.com.tla.flight.dao;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import au.com.tla.flight.model.Aircraft;

public class AircraftGenericDAOTest {

	private GenericDAO<Aircraft, String> dao;
	

	@Before
	public void setUp() throws Exception {
		this.dao = new GenericDAO<Aircraft, String>(Aircraft.class);
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testRead() {
		// success case - assumes data exists :-( !!
		Aircraft vendor = dao.read("Mentor 4 28");
		assertNotNull(vendor);
		
		// failure case
		vendor = dao.read("INVALID VENDOR!!");
		assertNull(vendor);
	}
	
	@Test
	public void testCreateAndDelete() {
		String key = "Carbon M";
		Aircraft pg = FlightGenerator.makeAircraft(key, "1", "NOVA");
		
		dao.create(pg);
		
		Aircraft fetched = dao.read(key);
		assertEquals(fetched.getModel(), pg.getModel());
		
		verifyDelete(fetched);
	}
	
	@Test
	public void testUpdateAndDelete() {
		String key = "Ra S";
		Aircraft pg = FlightGenerator.makeAircraft(key, "1", "NOVA");
		
		dao.create(pg);
		Aircraft fetched = dao.read(key);
		assertEquals(pg.getModel(), fetched.getModel());
		
		pg.setLTFRating("1-2");
		dao.update(pg);
		fetched = dao.read(key);
		assertEquals(pg.getLTFRating(), fetched.getLTFRating());
		
		verifyDelete(fetched);
	}

	public void verifyDelete(Aircraft man) {
		dao.delete(man);
		Aircraft fetched = dao.read(man.getModel());
		assertNull(fetched);
	}
}
