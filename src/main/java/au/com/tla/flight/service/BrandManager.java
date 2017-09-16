package au.com.tla.flight.service;

import java.util.List;

import au.com.tla.flight.model.Manufacturer;

public interface BrandManager {

	String createManufacturer(Manufacturer brand);
	
	void save(Manufacturer brand);
	
	void deleteManufacturer(Manufacturer brand);
	
	List<Manufacturer> getManufacturers();
	
	Manufacturer getById(String id);
}