package com.w2a.API_Batch.testUtils;

import com.w2a.API_Batch.setUp.APISetUp;

import io.restassured.specification.RequestSpecification;

public class TestUtil extends APISetUp {

	public static RequestSpecification setFormParam(String arguments, RequestSpecification reqSpecs) {

		String[] listOfArgument = arguments.split(",");

		for (String singleArguments : listOfArgument) {
			String[] keyValue = singleArguments.split(":");
			{
			String key = keyValue[0];
			String value = keyValue[1];
			reqSpecs.formParam(key, value);
			}
		}
		return reqSpecs;

	}

}
