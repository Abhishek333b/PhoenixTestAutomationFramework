package com.api.retry;

import org.apache.logging.log4j.Logger;

import org.apache.logging.log4j.LogManager;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class RetryAnalyzer implements IRetryAnalyzer {

	
	private static final Logger LOGGER = LogManager.getLogger(RetryAnalyzer.class);
	private static final int MAX_ATTEMPTS =2;
	private int count =1;
	@Override
	public boolean retry(ITestResult result) {
		LOGGER.info("checking if the{} test can be reexecuted",result.getName());
		
		if(count<=MAX_ATTEMPTS) {
			LOGGER.warn("executing the {} test,current attempt: {}/{},REASON {}",result.getName(),count,MAX_ATTEMPTS,
					result.getThrowable().getMessage());
			count++;
			return  true;
		}
		return false;
	}
	

}
