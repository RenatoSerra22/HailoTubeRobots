package com.hailo.tube.robot;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class RobotDispatcher{
	private List<RobotPath> robotList;
	private TubePath tubepath;
	private String [] robotNames;
	private List<RobotWorker> workers;
	private DateFormat format = new SimpleDateFormat("yyyy-mm-dd HH:mm:ss", Locale.ENGLISH);
    private Date lastHour;
    
	public RobotDispatcher(String robotNames[]){
		try {
			lastHour = format.parse("2011-03-22 08:10:00");
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		robotList = new ArrayList<RobotPath>();
		this.robotNames = robotNames;
		tubepath = TubePath.getInstance();
		workers = Collections.synchronizedList(new ArrayList<RobotWorker>());		
		System.out.println("***Loading Tube Stations..***");
		tubepath.loadFile("");
		Thread worker;
		for(String robotId: robotNames){
			System.out.println("***Loading robot: "+robotId+" points..***");
			robotList.add(new RobotPath(robotId));
			RobotWorker robotWorket= new RobotWorker(robotId);
			worker = new Thread(robotWorket);
			workers.add(robotWorket);
			worker.start();
		}
		System.out.println("***All data has been loaded***");
	}
	
	@SuppressWarnings("deprecation")
	public void startWork(){
		
		while(workers.size()!=0){
			for(int i=0; i <workers.size();i++){
				if(robotList.get(i).getQueue().size()==0){
					System.out.println("Robot: "+workers.get(i).getRobotId()+" has no more tasks and is requested to shutdown!");
					workers.get(i).shutDown();
						workers.remove(i);
						robotList.remove(i);
				}else if((lastHour.getTime()-((RobotPoint)robotList.get(i).getQueue().peek()).getTime().getTime())<=0){
					System.out.println("Robot: "+workers.get(i).getRobotId()+" its 8:10 and must be shutdown!");
					workers.get(i).shutDown();
					workers.remove(i);
					robotList.remove(i);
			  }
				else{					
				if(workers.get(i).getQueue().size()< RobotWorker.MAX_QUEUE_SIZE && robotList.get(i).getQueue().size()>0){
					RobotPoint currentPoint = robotList.get(i).take();
					System.out.println("Dispatcher: sending "+workers.get(i).getRobotId()+" to "+ currentPoint.getCoordinate() );
					workers.get(i).getQueue().add(currentPoint);				
				}
				}
			}
		}
		
	}	

}
