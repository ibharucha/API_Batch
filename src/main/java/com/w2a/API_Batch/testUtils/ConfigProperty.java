package com.w2a.API_Batch.testUtils;

import org.aeonbits.owner.Config;
import org.aeonbits.owner.Config.Sources;

@Sources({
		"file:C:/Users/techintrend.ca/eclipse-workspace/API_Batch/src/test/resources/propertyFile/config.properties" })
public interface ConfigProperty extends Config {

	@Key("baseURI")
	String getBaseURI();

	@Key("basePath")
	String getBasePath();

	@Key("secretKey")
	String GetSecretKey();
	
	@Key("testReportPath")
	String getTestReportPath();
	
	@Key("testReportName")
	String getTestReportName();
}
