package com.hailo.tube.robot;

import java.util.Date;
import java.util.Random;

public class TrafficPoint implements Traffic  {

	private String robotId;
	private Date time;
	private double speed;
	private enum Condition {LIGHT,MODERATE,HEAVY;
	public static Condition getRandom() {
        return values()[(int) (Math.random() * values().length)];
    }}
	private Condition currentCondition;
	
	public TrafficPoint(String robotId, Date time, double speed) {
		this.robotId = robotId;
		this.time = time;
		this.speed = Math.round(speed*100.0)/100.0;
		this.currentCondition = Condition.getRandom();
	}

	public String getRobotId() {
		return robotId;
	}

	public void setRobotId(String robotId) {
		this.robotId = robotId;
	}

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	public double getSpeed() {
		return speed;
	}

	public void setSpeed(double speed) {
		this.speed = speed;
	}

	public Condition getCurrentCondition() {
		return currentCondition;
	}

	public void setCurrentCondition(Condition currentCondition) {
		this.currentCondition = currentCondition;
	}

	@Override
	public String toString() {
		return "TrafficPoint [robotId=" + robotId + ", time=" + time+ ", speed=" + speed + "m/s, currentCondition=" + currentCondition+ "]";
	}
		

	
}
