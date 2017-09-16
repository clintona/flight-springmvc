package au.com.tla.flight.model;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "Flight")
public class Flight {
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Id
	private int id;
	@Column(name="LAUNCH_DATETIME")
	private Date launchDateTime;
	@ManyToOne(cascade={CascadeType.ALL})
	@JoinColumn(name="LAUNCH_LOCATION_ID")
	private Location launchLocation;
	@Column(name="DURATION_MINS")
	private int duration; // minutes
	@Column(name="DISTANCE_KM")
	private int distance; // kilometres
	@Column(name="MAX_HEIGHT_METRES")
	private int maxHeight; // metres
	@Column(name="MAX_LIFT_MS")
	private float maxLift; // m/s
	@Column(name="MAX_SINK_MS")
	private float maxSink; // m/s
	@ManyToOne(cascade={CascadeType.MERGE, CascadeType.PERSIST})
	@JoinColumn(name = "AIRCRAFT_ID")
	private Aircraft aircraft;
	@ManyToOne(cascade={CascadeType.MERGE, CascadeType.PERSIST})
	@JoinColumn(name="LANDING_LOCATION_ID")
	private Location landingLocation;
	// JPA cascades seem more limited than org.hibernate.cascade
//	@ManyToOne(cascade={CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH, CascadeType.DETACH})
	@ManyToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="PILOT_ID")
	private Person pilot;
	private String comments;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getLaunchDateTime() {
		return launchDateTime;
	}

	public void setLaunchDateTime(Date launchDateTime) {
		this.launchDateTime = launchDateTime;
	}

	public Location getLaunchLocation() {
		return launchLocation;
	}

	public void setLaunchLocation(Location launchLocation) {
		this.launchLocation = launchLocation;
	}

	public int getDuration() {
		return duration;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}

	public int getDistance() {
		return distance;
	}

	public void setDistance(int distance) {
		this.distance = distance;
	}

	public int getMaxHeight() {
		return maxHeight;
	}

	public void setMaxHeight(int maxHeight) {
		this.maxHeight = maxHeight;
	}

	public float getMaxLift() {
		return maxLift;
	}

	public void setMaxLift(float maxLift) {
		this.maxLift = maxLift;
	}

	public float getMaxSink() {
		return maxSink;
	}

	public void setMaxSink(float maxSink) {
		this.maxSink = maxSink;
	}

	public Aircraft getAircraft() {
		return aircraft;
	}

	public void setAircraft(Aircraft aircraft) {
		this.aircraft = aircraft;
	}

	public Location getLandingLocation() {
		return landingLocation;
	}

	public void setLandingLocation(Location landingLocation) {
		this.landingLocation = landingLocation;
	}

	public Person getPilot() {
		return pilot;
	}

	public void setPilot(Person pilot) {
		this.pilot = pilot;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

}
