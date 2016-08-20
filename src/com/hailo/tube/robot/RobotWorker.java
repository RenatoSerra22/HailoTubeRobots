package com.hailo.tube.robot;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Random;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;



public class RobotWorker implements Runnable {
	public static final int MAX_QUEUE_SIZE = 10;
	public static final int NEAR_STATION_DISTANCE = 350;
	private BlockingQueue<RobotPoint> tasks;
	private String robotId;
	private RobotPoint lastPoint;
	private  boolean keepGoing = true;
	
	public RobotWorker(String robotId)
	{
		tasks = new LinkedBlockingQueue<RobotPoint>(MAX_QUEUE_SIZE);
		this.robotId = robotId;		
	}	
	
	public BlockingQueue getQueue(){
		return tasks;
	}	
	
	public String getRobotId(){
		return robotId;
	}

	public void shutDown() {
		this.keepGoing = false;
	}

	@Override
	public void run() {	
		while(keepGoing || tasks.size()!=0 ){
		RobotPoint currentPoint = getTask();
		if(currentPoint!=null){		
		List<Traffic> trafficPoints = findTraffic(currentPoint);
		for(Traffic traffic : trafficPoints){
			addTraffic(traffic);
		}
		}
		}
		System.out.println("Robot: "+robotId +" finished his work and is now shuting down...");
	}
	
	private void addTraffic(Traffic currentTraffic){
		TrafficPoint trafficPoint =(TrafficPoint) currentTraffic;
		System.out.println("Robot: "+ trafficPoint.getRobotId() +" reported traffic.");
		TrafficLogger.getInstance().addTraffic(trafficPoint);
	}
	
	private double getSpeed(RobotPoint currentPoint){
		double speed = 0;
		
		if(lastPoint!=null){			
			 double distance = currentPoint.getCoordinate().distance(lastPoint.getCoordinate());
			 double timeSpent = ((currentPoint.getTime().getTime() - lastPoint.getTime().getTime())/ 1000) % 60 ;
			 if(timeSpent==0)
				 timeSpent=1;			
			 speed = (distance)/(timeSpent);
			 
			 
          
		}else{
			//if found before it had distance between 2 points
			Random rand = new Random();
			speed = rand.nextInt((100 - 50) + 1) + 50;
		}
		return speed;
		
	}
	
	//take a point to process out of the queue
	private RobotPoint getTask(){
		RobotPoint current = null;
		try {
			current = tasks.take();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return current;
	}
	
	//find traffic points logic
	private List<Traffic> findTraffic(RobotPoint currentPoint){
		List<Traffic> trafficPoints = new ArrayList<Traffic>();
		TubePath tubes =  TubePath.getInstance();
		
		for(TubePoint tube : tubes.getTubePoints() ){
			if(tube.getCoordinate().distance(currentPoint.getCoordinate()) < NEAR_STATION_DISTANCE){
				System.out.println("Robot: "+robotId+" found "+tube.getDescription()+" close by and is checking traffic");
				trafficPoints.add(new TrafficPoint(robotId,currentPoint.getTime(),getSpeed(currentPoint)));
			}
		}
		this.lastPoint = currentPoint;
		return trafficPoints;
	}
	


}
