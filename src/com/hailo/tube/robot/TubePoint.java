package com.hailo.tube.robot;

public class TubePoint {
	private String description;
	private Coordinate coordinate;
	
	public TubePoint(String description, Coordinate coordinate) {
		this.description = description;
		this.coordinate = coordinate;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Coordinate getCoordinate() {
		return coordinate;
	}

	public void setCoordinate(Coordinate coordinate) {
		this.coordinate = coordinate;
	}

	@Override
	public String toString() {
		return "TubePoint [description=" + description + ", coordinate="+ coordinate + "]";
	}	
	
	
}
