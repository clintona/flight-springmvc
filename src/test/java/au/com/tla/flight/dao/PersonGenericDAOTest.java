package au.com.tla.flight.dao;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import au.com.tla.flight.model.Person;

public class PersonGenericDAOTest {

	private GenericDAO<Person, Integer> dao;
	

	@Before
	public void setUp() throws Exception {
		this.dao = new GenericDAO<Person, Integer>(Person.class);
	}

	@After
	public void tearDown() throws Exception {
	}

	//@Test
	public void testRead() {
		// success case - assumes data exists :-( !!
//		Person vendor = dao.read(0);
//		assertNotNull(vendor);
		
		// failure case
		Person vendor = dao.read(-1);
		assertNull(vendor);
	}
	
	@Test
	public void testCreateAndDelete() {
		Person pg = create("Clinton", "Arnall", "1970-01-01", 87, 'M');
		
		dao.create(pg);
		
		Person fetched = dao.read(pg.getId());
		assertEquals(fetched.getFirstName(), pg.getFirstName());
		
		verifyDelete(fetched);
	}
	
	@Test
	public void testUpdateAndDelete() {
		Person pg = create("Clinton Ross", "Arnall", "1970-01-01", 87, 'M');
		
		dao.create(pg);
		Person fetched = dao.read(pg.getId());
		assertEquals(fetched.getFirstName(), pg.getFirstName());
		
		fetched.setEmail("me@b.com");
		dao.update(fetched);
		fetched = dao.read(fetched.getId());
		assertEquals("me@b.com", fetched.getEmail());
		
		verifyDelete(fetched);
	}

	public Person create(String first, String last, String dob, int kg, char sex) {
		Person p = new Person();
		p.setFirstName(first);
		p.setLastName(last);
		p.setDob(java.sql.Date.valueOf(dob));
		p.setWeightKg(kg);
		p.setSex(sex);
		return p;
	}
	
	public void verifyDelete(Person man) {
		dao.delete(man);
		Person fetched = dao.read(man.getId());
		assertNull(fetched);
	}
}
