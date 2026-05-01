package com.demo;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class Demo2 {
	
	public static Logger logger = LogManager.getLogger(Demo2.class);

	public static void main(String[] args) {
		System.out.println("inside main method");
		logger.info("inside main method");
		int a=10;
		System.out.println("value of a is "+a);
		logger.info("value of a {} ",a);
		int b=0;
		if(b==0) {
			
			logger.warn("value of b {}",b);
		}
		else {
			logger.info("value of b {}",b);
			
		}
		
		try {
		int result = a/b;
		logger.info("value of result {}",result);
		
		}
		catch (ArithmeticException e) {
			logger.error("operation can not happen",e);
		}
		
	}

}
