package au.com.tla.flight.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="LOCATION")
public class Location {
	@Id
	@Column(name="LOCATION_ID")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	private String name;
	private String locality;
	private String url;
	private String latitude;
	private String longitude;
	@Column(name="HEIGHT_AGL_METRES")
	private int heightAGL;
	@Column(name="HEIGHT_ASL_METRES")
	private int heightASL;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLocality() {
		return locality;
	}

	public void setLocality(String locality) {
		this.locality = locality;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getLatitude() {
		return latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	public String getLongitude() {
		return longitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

	public int getHeightAGL() {
		return heightAGL;
	}

	public void setHeightAGL(int heightAGL) {
		this.heightAGL = heightAGL;
	}

	public int getHeightASL() {
		return heightASL;
	}

	public void setHeightASL(int heightAMSL) {
		this.heightASL = heightAMSL;
	}
	
	public String toString() {
		StringBuilder buf = new StringBuilder(this.name);
		buf.append(", ").append(this.locality);
		if (heightASL > 0) {
			buf.append(" (").append(this.heightASL).append("m ASL)");
		}
		return buf.toString();
	}

}
