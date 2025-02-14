package com.w2a.API_Batch.listeners;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class RetryListener implements IRetryAnalyzer {
	
	int count=0;
	int maxRetry=3;
	
	@Override
	public boolean retry(ITestResult result) {
		if (count<=maxRetry) {
			count++;
			return true;
		}else {
			return false;
		}
		
	}
	
}
