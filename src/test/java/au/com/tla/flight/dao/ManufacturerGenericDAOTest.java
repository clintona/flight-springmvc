package au.com.tla.flight.dao;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import au.com.tla.flight.model.Manufacturer;

public class ManufacturerGenericDAOTest {

	private GenericDAO<Manufacturer, String> dao;
	

	@Before
	public void setUp() throws Exception {
		this.dao = new GenericDAO<Manufacturer, String>(Manufacturer.class);
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testRead() {
		// success case
		Manufacturer vendor = dao.read("NOVA");
		assertNotNull(vendor);
		
		// failure case
		vendor = dao.read("INVALID VENDOR!!");
		assertNull(vendor);
	}
	
	@Test
	public void testCreateAndDelete() {
		String key = "TEST";
		Manufacturer newMan = create(key, "http://www.blah.com");
		
		dao.create(newMan);
		
		Manufacturer fetchedMan = dao.read(key);
		assertEquals(newMan.getName(), fetchedMan.getName());
		
		verifyDelete(fetchedMan);
	}
	
	@Test
	public void testUpdateAndDelete() {
		String key = "TEST2";
		Manufacturer newMan = create(key, "http://www.blah.com");
		
		dao.create(newMan);
		Manufacturer fetchedMan = dao.read(key);
		assertEquals(newMan.getName(), fetchedMan.getName());
		
		newMan.setWebsite("http://www.abc.com");
		dao.update(newMan);
		fetchedMan = dao.read(key);
		assertEquals(newMan.getWebsite(), fetchedMan.getWebsite());
		
		verifyDelete(fetchedMan);
	}

	public Manufacturer create(String name, String url) {
		Manufacturer newMan = new Manufacturer(name);
		newMan.setWebsite(url);
		return newMan;
	}
	
	public void verifyDelete(Manufacturer man) {
		dao.delete(man);
		Manufacturer fetchedMan = dao.read(man.getName());
		assertNull(fetchedMan);
	}
}
