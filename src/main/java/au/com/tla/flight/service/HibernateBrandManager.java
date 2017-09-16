package au.com.tla.flight.service;

import java.util.List;

import org.springframework.stereotype.Component;

import au.com.tla.flight.dao.GenericDAO;
import au.com.tla.flight.model.Manufacturer;

@Component
public class HibernateBrandManager implements BrandManager {

	private GenericDAO<Manufacturer, String> dao;
	
	public void setManufacturerDAO(GenericDAO<Manufacturer, String> dao) {
		this.dao = dao;
	}
	
	public HibernateBrandManager() {
		this.dao = new GenericDAO<Manufacturer, String>(Manufacturer.class);
	}
	
	@Override
	public String createManufacturer(Manufacturer brand) {
		return dao.create(brand);
	}
	
	@Override
	public void save(Manufacturer brand) {
		dao.save(brand);
	}

	@Override
	public void deleteManufacturer(Manufacturer brand) {
		dao.delete(brand);
	}

	@Override
	public List<Manufacturer> getManufacturers() {
		return (List<Manufacturer>) dao.getSession().createCriteria(Manufacturer.class).list();
	}
	
	@Override
	public Manufacturer getById(String name) {
		return dao.read(name);
	}
	

}
