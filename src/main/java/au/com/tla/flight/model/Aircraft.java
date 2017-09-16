package au.com.tla.flight.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "AIRCRAFT")
public class Aircraft {
	@Id()
	private String model;
	@ManyToOne(cascade={CascadeType.PERSIST, CascadeType.MERGE})
	@JoinColumn(name="MANUFACTURER_NAME")
	private Manufacturer manufacturer;
	@Column(name="PASSENGER_CAPACITY")
	private int passengerCapacity = 1;
	@Column(name="LTF_RATING")
	private String ltfRating;
	
	
	public Aircraft() { }
	
	/** Convenience constructor. */
	public Aircraft(Manufacturer man, String model, String rating) {
		setManufacturer(man);
		setModel(model);
		setLTFRating(rating);
	}
	
	public Manufacturer getManufacturer() {
		return manufacturer;
	}
	
	public void setManufacturer(Manufacturer manufacturer) {
		this.manufacturer = manufacturer;
	}
	
	public String getModel() {
		return model;
	}
	
	public void setModel(String model) {
		this.model = model;
	}
	
	
	public int getPassengerCapacity() {
		return passengerCapacity;
	}
	
	public void setPassengerCapacity(int passengerCapacity) {
		this.passengerCapacity = passengerCapacity;
	}
	
	public String getLTFRating() {
		return ltfRating;
	}
	
	public void setLTFRating(String lTFRating) {
		this.ltfRating = lTFRating;
	}
}
