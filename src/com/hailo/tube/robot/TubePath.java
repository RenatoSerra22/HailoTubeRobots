package com.hailo.tube.robot;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TubePath implements FileReader{
	
	private static List<TubePoint> tubePoints;
	private static TubePath tubePath;
	private static final String CSV_PATH = "src/Resources/tube.csv";
	
	private TubePath()
	{
		
	}
	//Singleton tube path instance creation
	public static TubePath getInstance(){
		if(tubePath==null){
			tubePath = new TubePath();
			tubePoints = Collections.synchronizedList(new ArrayList<TubePoint>());
		}
		return tubePath;
	}
	
	public List<TubePoint> getTubePoints(){
		return tubePoints;
	}
	//load data from the CSV file
	public void loadFile(String filePath){
		  String csvFileToRead = CSV_PATH;  
		  BufferedReader br = null;  
		  String line = "";  
		  String splitBy = ",";  
		  
		  try {  
		  
		   br = new BufferedReader(new FileReader(csvFileToRead));  
		   while ((line = br.readLine()) != null) {  
		  
		    String[] fields = line.split(splitBy);  
		    tubePoints.add(new TubePoint(fields[0].replace("\"",""),new Coordinate(Double.parseDouble(fields[1]),Double.parseDouble(fields[2]))));
		   }  
		  
		  } catch (FileNotFoundException e) {  
		   e.printStackTrace();  
		  } catch (IOException e) {  
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
	@Override
	public String toString() {
		String output ="";
		for (TubePoint tubePoint : tubePoints){
			output+= tubePoint+"\n";
		}
		return output;
	}
	
		
}
