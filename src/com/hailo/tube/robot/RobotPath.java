package com.hailo.tube.robot;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.LinkedList;
import java.util.Locale;
import java.util.Queue;

public class RobotPath implements FileReader {
	
	private Queue<RobotPoint> robotPoints;
	private String robotId;
	private DateFormat format = new SimpleDateFormat("yyyy-mm-dd HH:mm:ss", Locale.ENGLISH);
	private static final String CSV_PATH = "src/Resources/";
	
	
	public RobotPath(String robotId) {
		this.robotPoints =  new LinkedList<RobotPoint>();
		this.robotId = robotId;
		loadFile(CSV_PATH+"/"+robotId+".csv");
	}
	
	public Queue getQueue(){
		return robotPoints;
	}
	public RobotPoint take(){
		return robotPoints.remove();
	}
	public String getRobotId(){
		return robotId;
	}
	
	
	public void loadFile(String filePath){
		  String csvFileToRead = filePath;  
		  BufferedReader br = null;  
		  String line = "";  
		  String splitBy = ",";  
		  
		  try {  
		  
		   br = new BufferedReader(new FileReader(csvFileToRead));  
		   while ((line = br.readLine()) != null) {  
		  
		    String[] fields = line.split(splitBy);  
		    robotPoints.add(new RobotPoint(	new Coordinate(Double.parseDouble(fields[1].replace("\"","")),Double.parseDouble(fields[2].replace("\"",""))),format.parse(fields[3].replace("\"",""))));
			    }  
		  
		  } catch (FileNotFoundException e) {  
		   e.printStackTrace();  
		  } catch (IOException e) {  
		   e.printStackTrace();  
		  } catch (NumberFormatException e) { 
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {  
		   if (br != null) {  
		    try {  
		     br.close();  
		    } catch (IOException e) {  
		     e.printStackTrace();  
		    }  
		   }  
		  }  
		 
	}
	
	
}
