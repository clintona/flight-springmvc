package au.com.tla.flight.model;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "MANUFACTURER")
public class Manufacturer {
	@Id()
	String name;
	String website;
	
	public Manufacturer() { 
		// use default prefix for website
		website = "http://";
	}
	
	public Manufacturer(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getWebsite() {
		return website;
	}
	
	public void setWebsite(String website) {
		this.website = website;
	}
	
	public String toString() {
		return this.name + " - " + this.website;
	}
}
