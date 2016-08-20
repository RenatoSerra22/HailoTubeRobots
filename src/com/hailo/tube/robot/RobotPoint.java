package com.hailo.tube.robot;

import java.util.Date;

public class RobotPoint {
	
	private Coordinate coordinate;
	private Date time;
	
	public RobotPoint(Coordinate coordinate, Date time) {
		this.coordinate = coordinate;
		this.time = time;
	}

	public Coordinate getCoordinate() {
		return coordinate;
	}

	public void setCoordinate(Coordinate coordinate) {
		this.coordinate = coordinate;
	}

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	@Override
	public String toString() {
		return "RobotPoint [coordinate="+ coordinate + ", time=" + time + "]";
	}
	
	
	
}
