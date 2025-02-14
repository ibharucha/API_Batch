package com.w2a.API_Batch.testCases;

import java.util.Hashtable;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.w2a.API_Batch.API.CustomerAPI;
import com.w2a.API_Batch.setUp.APISetUp;
import com.w2a.API_Batch.testUtils.DataProviders;

import io.restassured.response.Response;

public class TestCustomerAPI extends APISetUp {
	
	@Test(dataProviderClass = DataProviders.class,dataProvider = "dp",priority = 1,enabled = true)
	public void validateCreateCustomerAPIWithValidData(Hashtable<String, String> data)
	{
		
		testLevelLog.get().assignAuthor("Imran");
		testLevelLog.get().assignCategory("Sanity");
		
		Response response = CustomerAPI.sendPostRequestWithValidDataWithArguments(data);
		Assert.assertEquals(response.statusCode(), Integer.parseInt(data.get("expectedStatusCode")));
		Assert.assertEquals(response.jsonPath().get("email"),data.get("expectedEmail"));
		Assert.assertEquals(response.getStatusCode(),200);
	}
	
	

//	@Test(dataProviderClass = DataProviders.class, dataProvider = "dp", priority = 0, enabled = true)
//	public void validateCreateCustomerAPIWithInValidAuthKey(Hashtable<String, String> data) {
//
//	}
//
//	@Test(dataProviderClass = DataProviders.class, dataProvider = "dp", priority = 3, enabled = true)
//	public void validateCreateCustomerAPIWithInValidEmail(Hashtable<String, String> data) {
//
//	}
//
//	@Test(dataProviderClass = DataProviders.class, dataProvider = "dp", priority = 2, enabled = true)
//	public void validateCreateCustomerAPIWithoutAuthKey(Hashtable<String, String> data) {
//
//	}
//
//	@Test(dataProviderClass = DataProviders.class, dataProvider = "dp", priority = 4, enabled = true)
//	public void validateCreateCustomerAPIWithInvalidField(Hashtable<String, String> data) {
//
//	}

}
