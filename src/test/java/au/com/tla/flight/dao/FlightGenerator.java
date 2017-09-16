package au.com.tla.flight.dao;

import java.util.Date;

import au.com.tla.flight.model.Aircraft;
import au.com.tla.flight.model.Flight;
import au.com.tla.flight.model.Location;
import au.com.tla.flight.model.Manufacturer;
import au.com.tla.flight.model.Person;

/**
 * A Factory class to generate Flights for unit testing.
 * 
 * @author carnall
 *
 */
public class FlightGenerator {

	public static Flight makeFlight(int mins, int height, float lift, String location, Aircraft wing) {
		Location loc = new Location();
		loc.setName(location);
		loc.setLocality("VIC");
		
		Flight f = new Flight();
		f.setLaunchDateTime(new Date()); // fudged to now
		f.setDuration(mins);
		f.setMaxHeight(height);
		f.setMaxLift(lift);
		f.setMaxSink(-2.0f);
		f.setLaunchLocation(loc);
		f.setLandingLocation(loc);
		
		f.setAircraft(wing);
		
		f.setPilot(makePilotAmelia());

		return f;
	}
	
	public static Person makePilotAmelia() {
		Person pilot = new Person();
		pilot.setFirstName("Amelia");
		pilot.setLastName("Earheart");
		pilot.setSex('F');
		pilot.setDob(java.sql.Date.valueOf("1897-07-24"));
		return pilot;
	}
	
	public static Aircraft makeAircraft(String model, String rating, String vendor) {
		Aircraft pg = new Aircraft();
		pg.setModel(model);
		pg.setLTFRating(rating);
		pg.setManufacturer(new Manufacturer(vendor));
		return pg;
	}
}
