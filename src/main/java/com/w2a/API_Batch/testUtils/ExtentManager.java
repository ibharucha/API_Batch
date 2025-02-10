package com.w2a.API_Batch.testUtils;

import com.aventstack.extentreports.AnalysisStrategy;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.w2a.API_Batch.setUp.APISetUp;

import java.io.IOException;

public class ExtentManager extends APISetUp{

	private static ExtentReports extent;
	//private static ExtentTest test;
	private static ExtentSparkReporter htmlReporter;
	//private static String filePath = "./extentreport.html";


	public static ExtentReports GetExtent(String filePath) throws IOException {
		if (extent != null) {
			return extent;
		} else {
			extent = new ExtentReports();
			extent.attachReporter(getHtmlReporter(filePath));
			extent.setSystemInfo("Host Name", "ImranBharucha");
			
			extent.setAnalysisStrategy(AnalysisStrategy.CLASS);
			return extent;
		}
	}

	public static ExtentSparkReporter getHtmlReporter(String filePath) throws IOException {

		htmlReporter = new ExtentSparkReporter(filePath);
		/*htmlReporter.config().setChartVisibilityOnOpen(true);
		htmlReporter.config().setDocumentTitle(" Automation Report");
		htmlReporter.config().setReportName("Automation");
		htmlReporter.config().setTestViewChartLocation(ChartLocation.TOP);*/
		
		htmlReporter.loadXMLConfig("src\\test\\resources\\configXMLFile\\ReportsConfig.xml");

		return htmlReporter;
	}


}
