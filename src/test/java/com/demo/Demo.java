package com.demo;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class Demo {
	
	public static Logger logger = LogManager.getLogger(Demo.class);

	public static void main(String[] args) {
		System.out.println("inside main method");
		logger.info("inside main method");
		int a=10;
		System.out.println("value of a is "+a);
		logger.info("value of a {} ",a);
		int b=20;
		System.out.println("value of a is "+b);
		logger.info("value of b {} ",b);
		int result = a+b;
		System.out.println("result of addition "+result);
		logger.info("final result {} ",result);
		
		System.out.println("result is "+ result  );
		System.out.println("program ended");
		logger.info("Program ended {} ");
	}

}
