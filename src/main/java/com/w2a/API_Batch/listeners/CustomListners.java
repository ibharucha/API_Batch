package com.w2a.API_Batch.listeners;

import java.util.Arrays;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.Markup;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.w2a.API_Batch.setUp.APISetUp;

public class CustomListners extends APISetUp implements ITestListener {
	
	@Override
	public void onTestStart(ITestResult result) {
//		ExtentTest test = extentReports.createTest(getClass().getSimpleName());
		
		testLevelLog.get().info("<b>" + "Test Case:-" + result.getName() + " execution started" + "</b>");
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		ExtentTest test = classLevelLog.get().createNode(result.getName());
		testLevelLog.set(test);
		testLevelLog.get().pass("<b>"+"This test case is passed"+"</b>");
		extentReports.flush();
	}

	@Override
	public void onTestFailure(ITestResult result) {
		String excepionMessage = Arrays.toString(result.getThrowable().getStackTrace());
		testLevelLog.get().fail("<details>" + "<summary>" + "<b>" + "<font color=" + "red>" + "Exception Occured:Click to see"
						+ "</font>" + "</b >" + "</summary>" + excepionMessage.replaceAll(",", "<br>") + "</details>"
						+ " \n");

		String failureLog = "This test case got Failed";
		Markup m = MarkupHelper.createLabel(failureLog, ExtentColor.RED);
		testLevelLog.get().log(Status.FAIL, m);
		
		extentReports.flush();
	}

	@Override
	public void onTestSkipped(ITestResult result) {
//		ExtentTest test = extentReports.createTest(getClass().getSimpleName());
//		ExtentTest test = classLevelLog.get().createNode(result.getName());
//		testLevelLog.set(test);
		testLevelLog.get().skip(result.getName()+ " test case is skipped" );
		extentReports.flush();
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		
	}

	@Override
	public void onTestFailedWithTimeout(ITestResult result) {
		
	}

	@Override
	public void onStart(ITestContext context) {
		
	}

	@Override
	public void onFinish(ITestContext context) {
		
	}

}
