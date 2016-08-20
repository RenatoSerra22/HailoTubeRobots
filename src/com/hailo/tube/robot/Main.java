package com.hailo.tube.robot;

import java.text.DateFormat;
import java.util.Date;

public class Main {

	public static void main(String[] args) throws InterruptedException {
		String robots [] = {"5937","6043"};
		RobotDispatcher dispatcher = new RobotDispatcher(robots);
		dispatcher.startWork();
	}

}
