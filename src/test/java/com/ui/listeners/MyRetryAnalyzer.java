package com.ui.listeners;

import java.util.Properties;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

import com.constants.Env;
import com.utility.JSONUtility;
import com.utility.PropertiesUtil;

public class MyRetryAnalyzer implements IRetryAnalyzer{
	
	//private static final int MAX_RETRY = Integer.parseInt(PropertiesUtil.readProperty(Env.QA, "MAX_RETRY"));
	
	private static final int MAX_RETRY = JSONUtility.readJson(Env.QA).getMAX_RETRY();
	
	private static int currentRetry =1;
	
	@Override
	public boolean retry(ITestResult result) {
		if(currentRetry<= MAX_RETRY) {
			currentRetry++;
			return true;
		}
		
		return false;
	}
	
	

}
