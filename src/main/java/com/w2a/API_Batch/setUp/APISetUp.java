package com.w2a.API_Batch.setUp;

import static io.restassured.RestAssured.given;

import org.aeonbits.owner.ConfigFactory;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.w2a.API_Batch.testUtils.ConfigProperty;
import com.w2a.API_Batch.testUtils.ExcelReader;
import com.w2a.API_Batch.testUtils.ExtentManager;

import io.restassured.RestAssured;

import io.restassured.specification.RequestSpecification;
import java.io.IOException;
import java.lang.reflect.Method;

public class APISetUp {

	public static ConfigProperty configProperty;

	public static ExcelReader excel = new ExcelReader("./src/test/resources/testData/testdata.xlsx");
	public static ExtentReports extentReports;
	public static ThreadLocal<ExtentTest> classLevelLog = new ThreadLocal<ExtentTest>();
	public static ThreadLocal<ExtentTest> testLevelLog = new ThreadLocal<ExtentTest>();

	@BeforeSuite
	public void beforeSuit() throws IOException {

		configProperty = ConfigFactory.create(ConfigProperty.class);
		RestAssured.baseURI = configProperty.getBaseURI();
		RestAssured.basePath = configProperty.getBasePath();
		extentReports = ExtentManager
				.GetExtent(configProperty.getTestReportPath() + configProperty.getTestReportName());

	}

	@BeforeClass
	public void beforeClass() {

		ExtentTest classLevelTest = extentReports.createTest(getClass().getSimpleName());
		classLevelLog.set(classLevelTest);

	}

	@BeforeMethod
	public void beforeMethod(Method method) {

		ExtentTest test = classLevelLog.get().createNode(method.getName());
		testLevelLog.set(test);
		testLevelLog.get().info("Test case:-" + method.getName() + " execution started");
		// System.out.println("Test case :- " + method.getName() + " Execusion
		// started");

	}

	@AfterMethod
	public void afterMethod(ITestResult result) {
		if (result.getStatus() == ITestResult.SUCCESS) {
			testLevelLog.get().pass("Test Case Passed");
		} else if (result.getStatus() == ITestResult.FAILURE) {
			testLevelLog.get().fail("Test Case Failed");
		} else if (result.getStatus() == ITestResult.SKIP) {
			testLevelLog.get().skip("Test Case Skipped");
		}
		extentReports.flush();
	}

	@AfterSuite
	public void afterSuite() {
		configProperty = ConfigFactory.create(ConfigProperty.class);
		RestAssured.baseURI = configProperty.getBaseURI();
		RestAssured.basePath = configProperty.getBasePath();
	}

	public static RequestSpecification setRequestSpecification() {
		RequestSpecification spec = given().auth().basic(configProperty.GetSecretKey(), "");
		return spec;
	}
}
