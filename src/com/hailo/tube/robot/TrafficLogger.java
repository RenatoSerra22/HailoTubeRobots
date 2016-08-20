package com.hailo.tube.robot;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TrafficLogger {
	
	private static TrafficLogger trafficLog = new TrafficLogger();
	private static List<Traffic> trafficList = Collections.synchronizedList(new ArrayList<Traffic>());
	
	private TrafficLogger() {
		
	}
	
	public static TrafficLogger getInstance() {
	      return trafficLog;
	}
	public static List<Traffic> getTraffic(){
		return trafficList;
	}
	
	public static void addTraffic(Traffic trafficPoint){
		synchronized (trafficPoint) {	
	     System.out.println("Traffic Added: "+trafficPoint);			
		trafficList.add(trafficPoint);
		}
	}

	@Override
	public String toString() {
		String trafficString="";
		for (Traffic trafficInstance: trafficList)
		{
			trafficString += trafficInstance.toString()+"\n";
		}
		return trafficString;
	}
	
}
