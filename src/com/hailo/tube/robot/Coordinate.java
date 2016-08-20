package com.hailo.tube.robot;

public class Coordinate {

	private double lat,lon;
    private static final double EARTH_RADIUS = 6371000; //meters
    
	public Coordinate(double lat, double lon) {
		this.lat = lat;
		this.lon = lon;
	}

	public double getLat() {
		return lat;
	}

	public void setLat(double lat) {
		this.lat = lat;
	}

	public double getLon() {
		return lon;
	}

	public void setLon(double lon) {
		this.lon = lon;
	}
	//calculate the distance between 2 coordinates
	public double distance(Coordinate coordinate){
	    double dLat = Math.toRadians(coordinate.lat - this.lat);
	    double dLng = Math.toRadians(coordinate.lon - this.lon);
	    double a = Math.sin(dLat/2) * Math.sin(dLat/2) +
	               Math.cos(Math.toRadians(this.lat)) * Math.cos(Math.toRadians(coordinate.lat)) *
	               Math.sin(dLng/2) * Math.sin(dLng/2);
	    double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a));
	    float dist = (float) (EARTH_RADIUS * c);
	    return dist;
	}

	@Override
	public String toString() {
		return "Coordinate [lat=" + lat + ", lon=" + lon + "]";
	}
	
}
